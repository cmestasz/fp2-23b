package FX.MainMenu;

import Utils.*;
import java.io.*;
import javax.swing.JOptionPane;

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

            dataReceiver.startGame();
            new MainGame();
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
            JOptionPane.showMessageDialog(null, "Crea o unete a una partida!");
        return enemySet;
    }

    private class DataReceiver extends Thread {
        private File matchFile = new File(path);
        private long lastModified;
        private boolean gameStarted;

        public void run() {
            try {
                while (!gameStarted) {
                    if (matchFile.lastModified() != lastModified) {
                        DataInputStream in = new DataInputStream(new FileInputStream(matchFile));
                        int response = in.readInt();
                        String name;
                        switch (response) {
                            case RESPONSE_HOST:
                                name = Utils.readString(in);
                                Platform.runLater(() -> {
                                    eName = name;
                                    enemyName.setText(eName);
                                });
                                break;
                            case RESPONSE_GUEST:
                                name = Utils.readString(in);
                                if (name.equals("")) {
                                    JOptionPane.showMessageDialog(null, "La partida no existe.");
                                } else {
                                    Platform.runLater(() -> {
                                        eName = name;
                                        enemyName.setText(eName);
                                        startButton.setDisable(true);
                                    });
                                }
                                break;
                            case RESPONSE_START:
                                Platform.runLater(() -> {
                                    dataReceiver.startGame();
                                    new MainGame();
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

    private class MainGame {
        public MainGame() {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../MainGame/Main.fxml"));
                Stage mainGame = new Stage();
                mainGame.setTitle("Main Game");
                mainGame.setScene(new Scene(root, resolution.getWidth(), resolution.getHeight()));
                mainGame.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}