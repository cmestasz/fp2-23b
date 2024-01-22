package FX.MainGame.Classes;

import java.io.Serializable;

public class Knight extends Soldier {
    private static final int HEALTH = 12;
    private static final int ATTACK = 13;
    private static final int DEFENSE = 7;
    private String weapon = "ESPADA";
    private boolean mounted = false;

    public Knight(String name, int team, String type, String typeFile) {
        super(name, team, HEALTH, ATTACK, DEFENSE, type, typeFile);
    }

    public void changeWeapon(String arma) {
        this.weapon = arma;
    }

    public void mount(Soldier other) {
        mounted = true;
        changeWeapon("LANZA");
        charge(other);
    }

    public void dismount() {
        mounted = false;
        changeWeapon("ESPADA");
        defend();
    }

    public void charge(Soldier other) {
        if (mounted)
            attack(other, 3);
        else
            attack(other, 2);
    }
}
