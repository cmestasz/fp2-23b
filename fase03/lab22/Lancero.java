package fase03.lab22;

public class Lancero extends Soldado {
    private static int totalLanceros1;
    private static int totalLanceros2;
    private int longitudLanza;

    public Lancero(String nombre, int equipo, int vidaInicial, int ataque, int defensa, String clase) {
        super(nombre, equipo, vidaInicial, ataque, defensa, clase);
        if (equipo == 1)
            totalLanceros1++;
        else
            totalLanceros2++;
    }

    public void schiltrom() {
        defender();
    }
    
    public static int getTotalLanceros1() {
        return totalLanceros1;
    }

    public static int getTotalLanceros2() {
        return totalLanceros2;
    }
}
