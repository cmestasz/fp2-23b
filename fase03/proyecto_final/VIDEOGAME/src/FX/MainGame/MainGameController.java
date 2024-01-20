package FX.MainGame;

import FX.MainMenu.MainMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainGameController {
    private final int SIZE = 10;
    private final int TOP_BAR_PIXELS = 60;

    private int[][] board = new int[SIZE][SIZE];
    private Stage stage;
    private MainMenuController menuController;

    @FXML
    private GridPane uiBoard;

    public void setMenuController(MainMenuController controller) {
        this.menuController = controller;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        initButtons();
    }

    public void initialize() {
        
    }

    private void initButtons() {
        Image image = new Image("img/tile.png");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                ImageView imageView = new ImageView(image);
                double size = (stage.getHeight() - TOP_BAR_PIXELS) / 10;
                imageView.setFitWidth(size);
                imageView.setFitHeight(size);

                Pane pane = new Pane(imageView);
                pane.setOnMouseClicked(this::handleClick);

                uiBoard.add(pane, i, j);
            }
        }
    }

    private void handleClick(MouseEvent event) {
        Pane pane = (Pane) event.getSource();
        System.out.println(pane.getChildren().get(0));
    }

}