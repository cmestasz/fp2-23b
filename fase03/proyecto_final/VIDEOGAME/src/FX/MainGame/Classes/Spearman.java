package FX.MainGame.Classes;

public class Spearman extends Soldier {
    private int spearLength;

    public Spearman(String name, int team, int initialHealth, int attack, int defense, String type) {
        super(name, team, initialHealth, attack, defense, type);
    }

    public void schiltrom() {
        defend();
    }
}
