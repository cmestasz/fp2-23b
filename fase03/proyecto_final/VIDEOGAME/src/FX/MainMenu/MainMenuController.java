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
import javafx.stage.Stage;

public class MainMenuController implements Operation {
    private final ObservableList<Resolution> RESOLUTIONS = FXCollections.observableArrayList();
    private final int CODE_LENGTH = 4;

    private String pName;
    private String eName;
    private Resolution resolution;
    private int id;
    private String path;
    private File connectionFile;
    private DataReceiver dataReceiver;
    private Stage stage;

    @FXML
    private Button createMatchButton;
    @FXML
    private Button joinMatchButton;
    @FXML
    private TextField createMatchCode;
    @FXML
    private TextField joinMatchCode;
    @FXML
    private TextField nameInput;
    @FXML
    private Label playerName;
    @FXML
    private Label enemyName;
    @FXML
    private ComboBox<Resolution> resolutionInput;
    @FXML
    private Button startButton;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        RESOLUTIONS.addAll(new Resolution(850, 480), new Resolution(1280, 720), new Resolution(1366, 768));
        resolutionInput.setItems(RESOLUTIONS);
        resolutionInput.setValue(RESOLUTIONS.get(0));
        resolution = resolutionInput.getValue();
    }

    public void createMatch() {
        if (checkName()) {
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

                out.close();

                startDataReceiver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void joinMatch() {
        if (checkName()) {
            setConnection();
            try {
                DataOutputStream out = new DataOutputStream(new FileOutputStream(connectionFile));
                out.writeInt(OPERATION_JOIN);
                out.writeChars(joinMatchCode.getText());
                out.writeChar(0);
                out.writeChars(pName);
                out.writeChar(0);

                out.close();

                startDataReceiver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setResolution() {
        resolution = resolutionInput.getValue();
    }

    public void startMatch() {
        createGameStage();
        if (checkName() && checkEnemy()) {
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

    public void setName() {
        pName = nameInput.getText();
        playerName.setText(pName);
    }

    private void setConnection() {
        if (connectionFile == null) {
            path = "connections/" + id + ".dat";
            connectionFile = new File(path);
            while (connectionFile.exists()) {
                id++;
                path = "connections/" + id + ".dat";
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
            JOptionPane.showMessageDialog(null, "Ingrese un nombre!");
        return nameSet;
    }

    private boolean checkEnemy() {
        boolean enemySet = eName != null;
        if (!enemySet)
            JOptionPane.showMessageDialog(null, "Crea o únete a una partida!");
        return enemySet;
    }

    private void createGameStage() {
        // dataReceiver.startGame();
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
                        String name;
                        switch (response) {
                            // Respuesta del anfitrión
                            case RESPONSE_HOST:
                                name = Utils.readString(in);
                                // Actualiza el nombre del oponente en la interfaz de usuario
                                Platform.runLater(() -> {
                                    eName = name;
                                    enemyName.setText(eName);
                                });
                                break;
                            // Respuesta del invitado
                            case RESPONSE_GUEST:
                                name = Utils.readString(in);
                                if (name.equals("")) {
                                    JOptionPane.showMessageDialog(null, "La partida no existe.");
                                } else {
                                    // Actualiza el nombre del oponente en la interfaz de usuario y desactiva el
                                    // botón de inicio
                                    Platform.runLater(() -> {
                                        eName = name;
                                        enemyName.setText(eName);
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
    }

    public class MainGame {
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
                controller.setStage(mainGame);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
