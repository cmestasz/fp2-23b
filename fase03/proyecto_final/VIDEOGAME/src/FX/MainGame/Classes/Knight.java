package FX.MainGame.Classes;

public class Knight extends Soldier {
    private String weapon = "ESPADA";
    private boolean mounted = false;

    public Knight(String name, int team, int initialHealth, int attack, int defense, String type) {
        super(name, team, initialHealth, attack, defense, type);
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
