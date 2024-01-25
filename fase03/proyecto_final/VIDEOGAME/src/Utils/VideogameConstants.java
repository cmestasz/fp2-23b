package Utils;

public interface VideogameConstants {
    String[] TERRAINS = { "BOSQUE", "CAMPO ABIERTO", "MONTAÑA", "DESIERTO", "PLAYA" };
    String[] TYPES = { "CABALLERO", "ARQUERO", "ESPADACHIN", "LANCERO" };
    String[] TYPE_FILES = { "knight", "archer", "swordsman", "spearman", "tile" };
    String[] ACTIONS = { "DESMONTAR", "MONTAR", "MOVER", "ATACAR", "SCHILTROM", "DISPARAR", "DANZA DE ESPADAS" };
    String[] ACTION_FILES = { "dismount", "mount", "move", "attack" ,"schiltrom", "shoot", "sworddance" };
    BetterColor[] BACKGROUNDS = {
            new BetterColor(0.13, 0.55, 0.13, 1),
            new BetterColor(0.31, 1, 0, 1),
            new BetterColor(0.69, 0.46, 0.29, 1),
            new BetterColor(0.93, 0.7, 0.19, 1),
            new BetterColor(0.91, 0.85, 0.58, 1)
    };

    BetterColor PLAYER_COLOR = new BetterColor(0.27, 0.51, 1, 1);
    BetterColor ENEMY_COLOR = new BetterColor(1, 0.27, 0.27, 1);
    BetterColor PLAYER_COLOR_TRANS = new BetterColor(0.27, 0.51, 1, 0.1);
    BetterColor ENEMY_COLOR_TRANS = new BetterColor(1, 0.27, 0.27, 0.1);
    BetterColor BACKGROUND_COLOR = new BetterColor(0.1, 0.1, 0.1, 1);
    int TOTAL_SOLDIERS = 10;
    int SIZE = 10;
}
