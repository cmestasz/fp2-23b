package FX.MainMenu;

import Utils.*;
import java.io.*;
import javax.swing.JOptionPane;

import FX.MainGame.MainGameController;
import javafx.application.*;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenuController implements Operation {
    private final ObservableList<Resolution> RESOLUTIONS = FXCollections.observableArrayList();
    private final ObservableList<String> KINGDOMS = FXCollections.observableArrayList();
    private final int CODE_LENGTH = 6;

    private String pName;
    private String eName;
    private String pKingdom;
    private String eKingdom;
    private Resolution resolution;
    private double volume;
    private int idConnection;
    private int idPlayer;
    private String path;
    private File connectionFile;
    private DataReceiver dataReceiver;
    private Stage stage;
    private DBConnector dbConnector;

    @FXML
    private TextField nameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private Pane settingsPane;
    @FXML
    private ComboBox<Resolution> resolutionInput;
    @FXML
    private Slider volumeInput;
    @FXML
    private ComboBox<String> kingdomInput;
    @FXML
    private TextField createMatchCode;
    @FXML
    private TextField joinMatchCode;
    @FXML
    private Label playerName;
    @FXML
    private Label enemyName;
    @FXML
    private Label playerKingdom;
    @FXML
    private Label enemyKingdom;
    @FXML
    private Button startButton;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        RESOLUTIONS.addAll(new Resolution(850, 480), new Resolution(1280, 720), new Resolution(1366, 768),
                new Resolution(1920, 1080));
        resolutionInput.setItems(RESOLUTIONS);
        resolutionInput.setValue(RESOLUTIONS.get(0));
        resolution = resolutionInput.getValue();

        KINGDOMS.addAll("INGLATERRA", "FRANCIA", "CASTILLA-ARAGÓN", "MOROS", "SACRO IMPERIO");
        kingdomInput.setItems(KINGDOMS);

        dbConnector = new DBConnector();
    }

    public void setKingdom() {
        pKingdom = kingdomInput.getValue();
        playerKingdom.setText(pKingdom);
    }

    public void toggleSettings() {
        settingsPane.setVisible(!settingsPane.isVisible());
    }

    public void setResolution() {
        resolution = resolutionInput.getValue();
    }

    public void setVolume() {
        volume = volumeInput.getValue();
    }

    public void createMatch() {
        if (checkName() && checkKingdom()) {
            setConnection();
            try {
                DataOutputStream out = new DataOutputStream(new FileOutputStream(connectionFile));
                String code = "";
                for (int i = 0; i < CODE_LENGTH; i++)
                    code += (char) ('A' + (int) (Math.random() * 26));

                createMatchCode.setText(code);
                out.writeInt(OPERATION_CREATE);
                out.writeChars(code);
                out.writeChar(0);
                out.writeChars(pName);
                out.writeChar(0);
                out.writeChars(pKingdom);
                out.writeChar(0);

                out.close();

                startDataReceiver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void joinMatch() {
        if (checkName() && checkKingdom()) {
            setConnection();
            try {
                DataOutputStream out = new DataOutputStream(new FileOutputStream(connectionFile));
                out.writeInt(OPERATION_JOIN);
                out.writeChars(joinMatchCode.getText());
                out.writeChar(0);
                out.writeChars(pName);
                out.writeChar(0);
                out.writeChars(pKingdom);
                out.writeChar(0);

                out.close();

                startDataReceiver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void startMatch() {
        // createGameStage();
        if (checkName() && checkEnemy() && checkKingdom()) {
            try {
                DataOutputStream out = new DataOutputStream(new FileOutputStream(connectionFile));
                out.writeInt(OPERATION_START);
                out.writeChars(createMatchCode.getText());
                out.writeChar(0);

                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            createGameStage();
        }
    }

    public void login() {
        String name = nameInput.getText();
        idPlayer = dbConnector.loginPlayer(name, passwordInput.getText());
        if (idPlayer == -1) {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
        } else {
            JOptionPane.showMessageDialog(null, "Acceso correcto.");
            pName = name;
            playerName.setText(pName);
            nameInput.setText("");
            passwordInput.setText("");
        }
    }

    public void register() {
        pName = nameInput.getText();
        dbConnector.registerPlayer(pName, passwordInput.getText());
        JOptionPane.showMessageDialog(null, "Usuario creado correctamente.");
        login();
    }

    public void getStatistics() {
        int[] status = dbConnector.getWinsLoses(idPlayer);
        JOptionPane.showMessageDialog(null, "W: " + status[0] + " | L: " + status[1]);
    }

    private void setConnection() {
        if (connectionFile == null) {
            path = "connections/" + idConnection + ".dat";
            connectionFile = new File(path);
            while (connectionFile.exists()) {
                idConnection++;
                path = "connections/" + idConnection + ".dat";
                connectionFile = new File(path);
            }
        }
    }

    private void startDataReceiver() {
        if (dataReceiver == null) {
            dataReceiver = new DataReceiver();
            dataReceiver.start();
        }
    }

    private boolean checkName() {
        boolean nameSet = pName != null;
        if (!nameSet)
            JOptionPane.showMessageDialog(null, "Crea o accede a tu cuenta!");
        return nameSet;
    }

    private boolean checkEnemy() {
        boolean enemySet = eName != null;
        if (!enemySet)
            JOptionPane.showMessageDialog(null, "Crea o únete a una partida!");
        return enemySet;
    }

    private boolean checkKingdom() {
        boolean kingdomSet = pKingdom != null;
        if (!kingdomSet)
            JOptionPane.showMessageDialog(null, "Escoge un reino!");
        return kingdomSet;
    }

    private void createGameStage() {
        dataReceiver.startGame();
        stage.hide();
        new MainGame(this);
    }

    // Clase interna para el receptor de datos en un hilo separado
    private class DataReceiver extends Thread {
        private File matchFile = new File(path);
        private long lastModified;
        private boolean gameStarted;

        public void run() {
            try {
                while (!gameStarted) {
                    // Comprueba si el archivo de la partida ha sido modificado
                    if (matchFile.lastModified() != lastModified) {
                        DataInputStream in = new DataInputStream(new FileInputStream(matchFile));
                        int response = in.readInt();
                        String name, kingdom;
                        switch (response) {
                            // Respuesta del anfitrión
                            case RESPONSE_HOST:
                                name = Utils.readString(in);
                                kingdom = Utils.readString(in);
                                // Actualiza el nombre del oponente en la interfaz de usuario
                                Platform.runLater(() -> {
                                    setEnemy(name, kingdom);
                                });
                                break;
                            // Respuesta del invitado
                            case RESPONSE_GUEST:
                                name = Utils.readString(in);
                                kingdom = Utils.readString(in);
                                if (name.equals("")) {
                                    JOptionPane.showMessageDialog(null, "La partida no existe.");
                                } else {
                                    // Actualiza el nombre del oponente en la interfaz de usuario y desactiva el
                                    // botón de inicio
                                    Platform.runLater(() -> {
                                        setEnemy(name, kingdom);
                                        startButton.setDisable(true);
                                    });
                                }
                                break;
                            // Respuesta de inicio de la partida
                            case RESPONSE_START:
                                // Inicia el juego principal
                                Platform.runLater(() -> {
                                    createGameStage();
                                });
                        }
                        lastModified = matchFile.lastModified();
                        in.close();
                    }

                    sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void startGame() {
            gameStarted = true;
        }

        private void setEnemy(String name, String kingdom) {
            eName = name;
            eKingdom = kingdom;
            enemyName.setText(eName);
            enemyKingdom.setText(eKingdom);
        }
    }

    private class MainGame {
        public MainGame(MainMenuController mainMenuController) {
            try {
                // Carga el archivo FXML del juego principal y configura la escena
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainGame/MainGame.fxml"));
                Parent root = loader.load();

                Stage mainGame = new Stage();
                mainGame.setTitle("Main Game");
                mainGame.setScene(new Scene(root, resolution.getWidth(), resolution.getHeight()));
                mainGame.setResizable(false);
                mainGame.show();

                MainGameController controller = loader.getController();
                controller.setMenuController(mainMenuController);
                controller.setResolution(resolution);
                controller.setStage(mainGame);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
