package FX.MainGame;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    private ImageView test;
    @FXML
    private VBox parent;

    public void initialize() {
        Image image = new Image("img/barrack.png");
        test.setImage(image);
        

    }
}