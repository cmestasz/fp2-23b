package FX.MainMenu;

import Utils.Resolution;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainMenuController {
    private final ObservableList<Resolution> RESOLUTIONS = FXCollections.observableArrayList();
    private final int CODE_LENGTH = 4;

    private String name;
    private Resolution resolution;
    private int id;

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
        File connection = new File(id + ".dat");
        while (!connection.exists()) {
            id++;
            connection = new File(id + ".dat");
        }
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream("match.dat"));

            String code = "";
            for (int i = 0; i < CODE_LENGTH; i++)
                code += (char) ('A' + (int) (Math.random() * 26));

            createMatchCode.setText(code);
            out.writeChars(code);
            out.writeChar(0);
            out.writeChars(name);
            out.writeChar(0);

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void joinMatch() {
        try {
            DataInputStream in = new DataInputStream(new FileInputStream("match.dat"));
            String inputCode = joinMatchCode.getText();

            char c;
            String code = "";
            while ((c = in.readChar()) != 0)
                code += c;

            if (inputCode.equals(code)) {
                String name = "";
                while ((c = in.readChar()) != 0)
                    name += c;
                enemyName.setText(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setResolution() {

    }

    public void startMatch() {

    }

    public void setName() {
        name = nameInput.getText();
        playerName.setText(name);
    }

    private class DataReceiver extends Thread {
        private File matchFile = new File("match.dat");
        private long lastModified;

        public void run() {
            try {
                lastModified = matchFile.lastModified();
                DataInputStream in = new DataInputStream(new FileInputStream(matchFile));

                while (true) {
                    if (matchFile.lastModified() != lastModified) {

                        lastModified = matchFile.lastModified();
                    }

                    sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}