package FX.MainGame;

import FX.MainMenu.MainMenuController;
import Utils.Resolution;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainGameController {
    private final int SIZE = 10;

    private Stage stage;
    private Resolution resolution;
    private MainMenuController menuController;
    private BoardGUI board;

    @FXML
    private GridPane uiBoard;
    @FXML
    private Rectangle boardBackground;
    @FXML
    private Rectangle dataBackground;

    public void setMenuController(MainMenuController controller) {
        this.menuController = controller;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
        initButtons();
        initBackground();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        board = new BoardGUI();
        
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
        boardBackground.setFill(board.getBackground());

        dataBackground.setWidth(width - height);
        dataBackground.setHeight(height);
        
    }

    private void handleClick(MouseEvent event) {
        Pane pane = (Pane) event.getSource();
        System.out.println(pane.getChildren().get(0));
    }

}