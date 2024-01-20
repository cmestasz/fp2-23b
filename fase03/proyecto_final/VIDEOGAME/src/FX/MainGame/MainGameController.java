package FX.MainGame;

import FX.MainMenu.MainMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainGameController {

    private Stage stage;
    private MainMenuController controller;

    @FXML
    private ImageView test;
    @FXML
    private VBox parent;

    public void initialize() {
        Image image = new Image("img/barrack.png");
        test.setImage(image);
        

    }

    public void setController(MainMenuController controller) {
        this.controller = controller;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}