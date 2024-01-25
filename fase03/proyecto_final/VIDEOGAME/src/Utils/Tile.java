package Utils;

import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import java.util.HashMap;

public class Tile extends Pane implements VideogameConstants {
    private HashMap<String, ImageView> images = new HashMap<String, ImageView>();
    private ImageView image;
    private String type;
    private int i;
    private int j;

    public Tile(String type, double size, int i, int j) {
        this.i = i;
        this.j = j;
        this.type = type;
        for (int n = 0; n < TYPE_FILES.length; n++)
            images.put(TYPE_FILES[n], generateImageView(size, TYPE_FILES[n]));
        setImage(type);
    }

    public void setImage(String type) {
        image = images.get(type);
        getChildren().removeAll();
        getChildren().add(image);
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public String getType() {
        return type;
    }

    public boolean isConnected(Tile other) {
        return Math.abs(other.getI() - i) <= 1 && Math.abs(other.getJ() - j) <= 1;
    }

    private ImageView generateImageView(double size, String type) {
        Image image = new Image(String.format("img/tile_%s.png", type));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        images.put(type, imageView);
        return imageView;
    }

    public String getKey() {
        return i + "," + j;
    }
    
    public String toString() {
        return "a " + type + "!: " + i + ", " + j;
    }
}
