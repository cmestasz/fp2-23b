package FX.MainGame;

import FX.MainGame.Classes.*;
import Utils.SerializableColor;
import java.io.Serializable;
import java.util.*;
import javafx.scene.paint.Color;

public class Board implements Serializable {
    private final String[] TERRAINS = { "BOSQUE", "CAMPO ABIERTO", "MONTAÑA", "DESIERTO", "PLAYA" };
    private final String[] TYPES = { "CABALLERO", "ARQUERO", "ESPADACHIN", "LANCERO" };
    private final SerializableColor[] BACKGROUNDS = {
        new SerializableColor(0.13, 0.55, 0.13, 1),
        new SerializableColor(0.31, 1, 0, 1),
        new SerializableColor(0.69, 0.46, 0.29, 1),
        new SerializableColor(0.93, 0.7, 0.19, 1),
        new SerializableColor(0.91, 0.85, 0.58, 1)
    };
    private final Random RANDOM = new Random();
    private final int TOTAL = 10;
    private final int SIZE = 10;
    
    private String terrain;
    private SerializableColor background;
    private HashMap<String, Soldier> army1 = new HashMap<String, Soldier>();
    private HashMap<String, Soldier> army2 = new HashMap<String, Soldier>();
    private String kingdom1;
    private String kingdom2;

    public Board(String kingdom1, String kingdom2) {
        int idxTerrain = RANDOM.nextInt(TERRAINS.length);
        terrain = TERRAINS[idxTerrain];
        background = BACKGROUNDS[idxTerrain];

        this.kingdom1 = kingdom1;
        this.kingdom2 = kingdom2;

        initSoldiers(army1, 1);
        initSoldiers(army2, 2);
    }

    public String getTerrain() {
        return terrain;
    }

    public String getKingdom1() {
        return kingdom1;
    }

    public String getKingdom2() {
        return kingdom2;
    }

    public SerializableColor getBackground() {
        return background;
    }

    private void initSoldiers(HashMap<String, Soldier> map, int team) {
        for (int i = 0; i < TOTAL; i++) {
            String type = TYPES[RANDOM.nextInt(TYPES.length)];
            int row, col;
            String key;
            do {
                row = RANDOM.nextInt(SIZE);
                col = RANDOM.nextInt(SIZE);
                key = generateKey(row, col);
            } while (army1.containsKey(key) || army2.containsKey(key));
            String name = type + i + "X" + team;
            Soldier soldier = null;
            switch (type) {
                case "CABALLERO":
                    soldier = new Knight(name, team, type);
                    break;
                case "ARQUERO":
                    soldier = new Archer(name, team, type);
                    break;
                case "ESPADACHIN":
                    soldier = new Swordsman(name, team, type);
                    break;
                case "LANCERO":
                    soldier = new Spearman(name, team, type);
                    break;
            }
            map.put(key, soldier);
        }
    }

    private String generateKey(int i, int j) {
        return i + "," + j;
    }

    public String toString() {
        return "a board!";
    }
}
