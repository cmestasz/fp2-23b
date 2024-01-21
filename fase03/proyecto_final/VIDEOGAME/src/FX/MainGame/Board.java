package FX.MainGame;

import java.util.HashMap;
import java.util.Random;
import FX.MainGame.Classes.*;

public class Board {
    private HashMap<String, Soldier> soldiersMap = new HashMap<String, Soldier>();
    private String kingdom1;
    private String kingdom2;
    private String terrain;
    private final String[] CLASSES = { "CABALLERO", "ARQUERO", "ESPADACHIN", "LANCERO" };
    private final Random RANDOM = new Random();

    public Board(String kingdom1, String kingdom2, String terrain) {
        this.kingdom1 = kingdom1;
        this.kingdom2 = kingdom2;
        this.terrain = terrain;
    }


}
