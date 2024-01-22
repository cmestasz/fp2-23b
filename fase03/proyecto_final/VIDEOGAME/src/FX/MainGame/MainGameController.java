package FX.MainGame;

import java.io.*;
import javax.swing.JOptionPane;

import com.mysql.cj.util.Util;

import FX.MainMenu.MainMenuController;
import Utils.MainGameOperation;
import Utils.Resolution;
import Utils.Utils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainGameController implements MainGameOperation {
    private final int SIZE = 10;

    private Stage stage;
    private Resolution resolution;
    private MainMenuController menuController;
    private Board board;
    private String kingdomPlayer;
    private String kingdomEnemy;
    private File connectionFile;
    private String path;
    private int idConnection;
    private DataReceiver dataReceiver;
    private String matchCode;
    private String pName;
    private String eName;

    @FXML
    private GridPane uiBoard;
    @FXML
    private Rectangle boardBackground;
    @FXML
    private Rectangle dataBackground;
    @FXML
    private TextArea playerData;
    @FXML
    private TextArea enemyData;
    @FXML
    private TextArea chatOutput;
    @FXML
    private TextField chatInput;

    public void init(MainMenuController menuController, Resolution resolution, Stage stage, Board board,
            int idConnection, String matchCode, String pName, String eName) {
        this.menuController = menuController;
        this.resolution = resolution;
        this.stage = stage;
        this.board = board;
        this.idConnection = idConnection;
        this.kingdomPlayer = board.getKingdomPlayer();
        this.kingdomEnemy = board.getKingdomEnemy();
        this.matchCode = matchCode;
        this.pName = pName;
        this.eName = eName;
        initButtons();
        initBackground();
        initDataFields();

        setConnection();
    }

    public void initialize() {

    }

    private void initButtons() {
        Image image = new Image("img/tile.png");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                ImageView imageView = new ImageView(image);
                double size = 1.0 * resolution.getHeight() / 10;
                imageView.setFitWidth(size);
                imageView.setFitHeight(size);

                Pane pane = new Pane(imageView);
                pane.setOnMouseClicked(this::handleClick);

                uiBoard.add(pane, i, j);
            }
        }
    }

    private void initBackground() {
        int width = resolution.getWidth();
        int height = resolution.getHeight();
        boardBackground.setWidth(width);
        boardBackground.setHeight(height);
        boardBackground.setFill(board.getBackground().getColor());

        dataBackground.setWidth(width - height);
        dataBackground.setHeight(height);

    }

    private void initDataFields() {
        playerData.setText(kingdomPlayer);
        enemyData.setText(kingdomEnemy);
    }

    private void handleClick(MouseEvent event) {
        Pane pane = (Pane) event.getSource();
        System.out.println(pane.getChildren().get(0));
    }

    public void surrender() {

    }

    public void sendMessage() {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(connectionFile));

            String message = chatInput.getText();
            chatOutput.appendText(String.format("%s: %s%n", pName, message));
            out.writeInt(OPERATION_CHAT);
            out.writeChars(matchCode);
            out.writeChar(0);
            out.writeChars(message);
            out.writeChar(0);
            out.close();

            chatInput.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setConnection() {
        path = "connections/" + idConnection + ".dat";
        connectionFile = new File(path);
        try {
            connectionFile.createNewFile();
            dataReceiver = new DataReceiver();
            dataReceiver.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class DataReceiver extends Thread {
        private File matchFile = new File(path);
        private long lastModified = matchFile.lastModified();
        private boolean gameStarted;

        public void run() {
            try {
                while (!gameStarted) {
                    // Comprueba si el archivo de la partida ha sido modificado
                    if (matchFile.lastModified() != lastModified) {
                        DataInputStream in = new DataInputStream(new FileInputStream(matchFile));
                        int response = in.readInt();
                        switch (response) {
                            // Mensaje de chat
                            case RESPONSE_CHAT:
                                String message = Utils.readString(in);
                                Platform.runLater(() -> {
                                    chatOutput.appendText(String.format("%s: %s%n", eName, message));
                                });
                                break;
                            /*
                             * // Respuesta del anfitrión
                             * case RESPONSE_HOST:
                             * name = Utils.readString(in);
                             * kingdom = Utils.readString(in);
                             * // Actualiza el nombre del oponente en la interfaz de usuario
                             * Platform.runLater(() -> {
                             * setEnemy(name, kingdom);
                             * });
                             * break;
                             * // Respuesta del invitado
                             * case RESPONSE_GUEST:
                             * name = Utils.readString(in);
                             * kingdom = Utils.readString(in);
                             * if (name.equals("")) {
                             * JOptionPane.showMessageDialog(null, "La partida no existe.");
                             * } else {
                             * // Actualiza el nombre del oponente en la interfaz de usuario y desactiva el
                             * // botón de inicio
                             * Platform.runLater(() -> {
                             * setEnemy(name, kingdom);
                             * startButton.setDisable(true);
                             * });
                             * }
                             * break;
                             * // Respuesta de inicio de la partida
                             * case RESPONSE_START:
                             * File objFile = new File("connections/" + idConnection + ".obj");
                             * ObjectInputStream inObj = new ObjectInputStream(new
                             * FileInputStream(objFile));
                             * board = (Board) inObj.readObject();
                             * inObj.close();
                             * objFile.delete();
                             * // Inicia el juego principal
                             * Platform.runLater(() -> {
                             * createGameStage();
                             * });
                             */
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