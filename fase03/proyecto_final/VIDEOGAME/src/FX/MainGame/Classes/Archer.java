package FX.MainGame.Classes;

public class Archer extends Soldier {
    private int arrows = 10;

    public Archer(String name, int team, int initialHealth, int attack, int defense, String type) {
        super(name, team, initialHealth, attack, defense, type);
    }

    public void shoot(Soldier other) {
        if (arrows > 0) {
            attack(other, 1);
            arrows--;
        }
    }
}
