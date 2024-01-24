package FX.MainGame.Classes;

public class Knight extends Soldier {
    private static final int HEALTH = 12;
    private static final int ATTACK = 13;
    private static final int DEFENSE = 7;
    private boolean mounted = false;

    public Knight(String name, int team, String type, String typeFile) {
        super(name, team, HEALTH, ATTACK, DEFENSE, type, typeFile);
    }

    public void mount() {
        mounted = true;
        modifyAttack(1);
        modifyDefense(-1);
    }

    public void dismount() {
        mounted = false;
        modifyAttack(-1);
        modifyDefense(1);
    }

    // extra range
    public void charge(Soldier other) {
        attack(other);
    }

    public boolean isMounted() {
        return mounted;
    }
}
