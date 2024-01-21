package FX.MainGame.Classes;

public class Swordsman extends Soldier {
    private double swordLength;

    public Swordsman(String name, int team, int initialHealth, int attack, int defense, String type) {
        super(name, team, initialHealth, attack, defense, type);
    }

    public void generarMuroEscudos() {
        System.out.println(getName() + " genera un muro de escudos!");
    }
}
