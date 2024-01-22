package FX.MainGame.Classes;

import java.io.Serializable;

public class Swordsman extends Soldier {
    private static final int HEALTH = 10;
    private static final int ATTACK = 10;
    private static final int DEFENSE = 8;
    private double swordLength;

    public Swordsman(String name, int team, String type, String typeFile) {
        super(name, team, HEALTH, ATTACK, DEFENSE, type, typeFile);
    }

    public void generarMuroEscudos() {
        System.out.println(getName() + " genera un muro de escudos!");
    }
}
