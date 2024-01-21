package FX.MainGame;

import java.util.Random;

import javafx.scene.paint.Color;

public class BoardGUI {
    private final String[] TYPES = { "BOSQUE", "CAMPO ABIERTO", "MONTAÑA", "DESIERTO", "PLAYA" };
    private final Color[] BACKGROUNDS = {
            new Color(0.13, 0.55, 0.13, 1),
            new Color(0.31, 1, 0, 1),
            new Color(0.69, 0.46, 0.29, 1),
            new Color(0.93, 0.7, 0.19, 1),
            new Color(0.91, 0.85, 0.58, 1)
    };
    private final Random RANDOM = new Random();

    private String terrain;
    private Color background;

    public BoardGUI() {
        int idxTerrain = RANDOM.nextInt(TYPES.length);
        terrain = TYPES[idxTerrain];
        background = BACKGROUNDS[idxTerrain];
    }

    public Color getBackground() {
        return background;
    }
}
