package FX;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;

public class Controller {

    @FXML
    private ImageView tile;

    public void initialize() {
        Image img = new Image("img/4.png");
        tile.setImage(img);
    }
}