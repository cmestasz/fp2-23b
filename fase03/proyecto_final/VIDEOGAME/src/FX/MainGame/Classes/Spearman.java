package FX.MainGame.Classes;

import java.io.Serializable;

public class Spearman extends Soldier {
    private static final int HEALTH = 8;
    private static final int ATTACK = 5;
    private static final int DEFENSE = 10;
    private int spearLength;

    public Spearman(String name, int team, String type, String typeFile) {
        super(name, team, HEALTH, ATTACK, DEFENSE, type, typeFile);
    }

    public void schiltrom() {
        defend();
    }
}
