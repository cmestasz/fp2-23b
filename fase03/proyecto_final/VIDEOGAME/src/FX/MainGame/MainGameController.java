package FX.MainGame;

import java.io.*;
import java.util.HashMap;

import javax.swing.JOptionPane;

import com.mysql.cj.util.Util;

import FX.MainGame.Classes.Soldier;
import FX.MainMenu.MainMenuController;
import Utils.BetterColor;
import Utils.MainGameOperation;
import Utils.Resolution;
import Utils.Tile;
import Utils.Utils;
import Utils.VideogameConstants;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainGameController implements MainGameOperation, VideogameConstants {
    private Stage stage;
    private Resolution resolution;
    private int width;
    private int height;
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
    private Tile[][] tiles = new Tile[SIZE][SIZE];

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
    private ScrollPane chatOutputPane;
    @FXML
    private VBox chatOutput;
    @FXML
    private TextField chatInput;

    public void init(MainMenuController menuController, Resolution resolution, Stage stage, Board board,
            int idConnection, String matchCode, String pName, String eName) {
        this.menuController = menuController;
        this.resolution = resolution;
        this.width = resolution.getWidth();
        this.height = resolution.getHeight();
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
        initChat();

        setConnection();
    }

    public void initialize() {

    }

    public void surrender() {

    }

    public void sendMessage() {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(connectionFile));
            String message = String.format("%s: %s%n", pName, chatInput.getText());
            printMessage(message, PLAYER_COLOR);
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

    private void initButtons() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String key = generateKey(i, j);
                double size = 1.0 * resolution.getHeight() / 10;
                HashMap<String, Soldier> army1 = board.getArmy1();
                HashMap<String, Soldier> army2 = board.getArmy2();
                Tile tile;

                if (army1.containsKey(key)) {
                    tile = new Tile(army1.get(key).getTypeFile(), size, i, j);
                    tile.setStyle(String.format("-fx-background-color: %s;", PLAYER_COLOR_TRANS.getRGBA()));
                } else if (army2.containsKey(key)) {
                    tile = new Tile(army2.get(key).getTypeFile(), size, i, j);
                    tile.setStyle(String.format("-fx-background-color: %s;", ENEMY_COLOR_TRANS.getRGBA()));
                } else {
                    tile = new Tile("tile", size, i, j);
                }
                tiles[i][j] = tile;
                
                tile.setOnMouseClicked(this::handleClick);
                uiBoard.add(tile, i, j);
            }
        }
    }

    private void initBackground() {
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

    private void initChat() {
        chatOutput.setPrefHeight(height * 0.4);
    }

    private void handleClick(MouseEvent event) {
        Tile tile = (Tile) event.getSource();
        System.out.println(tile);
        // currently selected this ^ and show actions menu 5% of width
        // link soldiers with tile i j
        // implement the actions here probably
        // then implement board updates in board, these can be sent to the server as a number (update type), and other args qwq
        // remember to also show something visual when a special action is done
    }

    private void printMessage(String message, BetterColor color) {
        Text messageText = new Text(message);
        messageText.setFill(color.getColor());
        messageText.setWrappingWidth(width - height);

        ObservableList<Node> children = chatOutput.getChildren();
        children.add(children.size() - 1, messageText);
        chatOutputPane.setVvalue(0.99);
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

    private String generateKey(int i, int j) {
        return i + "," + j;
    }

    private class DataReceiver extends Thread {
        private File matchFile = new File(path);
        private long lastModified = matchFile.lastModified();
        private boolean gameEnded;

        public void run() {
            try {
                while (!gameEnded) {
                    // Comprueba si el archivo de la partida ha sido modificado
                    if (matchFile.lastModified() != lastModified) {
                        DataInputStream in = new DataInputStream(new FileInputStream(matchFile));
                        int response = in.readInt();
                        switch (response) {
                            // Mensaje de chat
                            case RESPONSE_CHAT:
                                String message = Utils.readString(in);
                                Platform.runLater(() -> {
                                    printMessage(message, ENEMY_COLOR);
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
    }
}