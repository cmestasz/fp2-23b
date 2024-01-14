package fase03.lab22;

public class Espadachin extends Soldado {
    private static int totalEspadachines1;
    private static int totalEspadachines2;
    private double longitudEspada;

    public Espadachin(String nombre, int equipo, int vidaInicial, int ataque, int defensa, String clase) {
        super(nombre, equipo, vidaInicial, ataque, defensa, clase);
        if (equipo == 1)
            totalEspadachines1++;
        else
            totalEspadachines2++;
    }

    public void generarMuroEscudos() {
        System.out.println(getNombre() + " genera un muro de escudos!");
    }
    
    public static int getTotalEspadachines1() {
        return totalEspadachines1;
    }

    public static int getTotalEspadachines2() {
        return totalEspadachines2;
    }
}
