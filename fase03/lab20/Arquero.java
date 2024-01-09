public class Arquero extends Soldado {
    private static int totalArqueros1;
    private static int totalArqueros2;
    private int flechas = 10;

    public Arquero(String nombre, int equipo, int vidaInicial, int ataque, int defensa, String clase) {
        super(nombre, equipo, vidaInicial, ataque, defensa, clase);
        if (equipo == 1)
            totalArqueros1++;
        else
            totalArqueros2++;
    }

    public void disparar(Soldado otro) {
        if (flechas > 0) {
            atacar(otro, 1);
            flechas--;
        }
    }

    public static int getTotalArqueros1() {
        return totalArqueros1;
    }

    public static int getTotalArqueros2() {
        return totalArqueros2;
    }
}
