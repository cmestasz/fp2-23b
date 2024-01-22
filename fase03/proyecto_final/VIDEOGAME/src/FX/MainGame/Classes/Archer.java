package FX.MainGame.Classes;

import java.io.Serializable;

public class Archer extends Soldier implements Serializable {
    private static final int HEALTH = 5;
    private static final int ATTACK = 7;
    private static final int DEFENSE = 3;
    private int arrows = 10;

    public Archer(String name, int team, String type) {
        super(name, team, HEALTH, ATTACK, DEFENSE, type);
    }

    public void shoot(Soldier other) {
        if (arrows > 0) {
            attack(other, 1);
            arrows--;
        }
    }
}
