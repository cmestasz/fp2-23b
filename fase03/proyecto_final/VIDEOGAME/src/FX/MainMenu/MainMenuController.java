package FX.MainMenu;

import Utils.*;
import java.io.*;
import javax.swing.JOptionPane;

import javafx.application.*;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainMenuController implements Operation {
    private final ObservableList<Resolution> RESOLUTIONS = FXCollections.observableArrayList();
    private final int CODE_LENGTH = 4;

    private String name;
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
        RESOLUTIONS.addAll(new Resolution(640, 480), new Resolution(1280, 720), new Resolution(1920, 1080));
        resolutionInput.setItems(RESOLUTIONS);
    }

    public void createMatch() {
        // nombre => codigo => operacion
        setConnection();
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(connectionFile));

            String code = "";
            for (int i = 0; i < CODE_LENGTH; i++)
                code += (char) ('A' + (int) (Math.random() * 26));

            createMatchCode.setText(code);
            out.writeChars(name);
            out.writeChar(0);
            out.writeChars(code);
            out.writeChar(0);
            out.writeInt(OPERATION_CREATE);

            out.close();

            startDataReceiver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void joinMatch() {
        setConnection();
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(connectionFile));
            out.writeChars(name);
            out.writeChar(0);
            out.writeChars(joinMatchCode.getText());
            out.writeChar(0);
            out.writeInt(OPERATION_JOIN);

            out.close();

            startDataReceiver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setResolution() {

    }

    public void startMatch() {
        dataReceiver.startGame();
    }

    public void setName() {
        name = nameInput.getText();
        playerName.setText(name);
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
                        String name = Utils.readString(in);
                        switch (response) {
                            case RESPONSE_HOST:
                                Platform.runLater(() -> {
                                    enemyName.setText(name);
                                });
                                break;
                            case RESPONSE_GUEST:
                                if (name.equals("")) {
                                    JOptionPane.showMessageDialog(null, "La partida no existe.");
                                } else {
                                    Platform.runLater(() -> {
                                        enemyName.setText(name);
                                    });
                                }
                                break;
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
}