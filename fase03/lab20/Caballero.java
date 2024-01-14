package fase03.lab20;

public class Caballero extends Soldado {
    private static int totalCaballeros1;
    private static int totalCaballeros2;
    private String arma = "ESPADA";
    private boolean montado = false;

    public Caballero(String nombre, int equipo, int vidaInicial, int ataque, int defensa, String clase) {
        super(nombre, equipo, vidaInicial, ataque, defensa, clase);
        if (equipo == 1)
            totalCaballeros1++;
        else
            totalCaballeros2++;
    }

    public void cambiarArma(String arma) {
        this.arma = arma;
    }

    public void montar(Soldado otro) {
        montado = true;
        cambiarArma("LANZA");
        embestir(otro);
    }

    public void desmontar() {
        montado = false;
        cambiarArma("ESPADA");
        defender();
    }

    public void embestir(Soldado otro) {
        if (montado)
            atacar(otro, 3);
        else
            atacar(otro, 2);
    }
    
    public static int getTotalCaballeros1() {
        return totalCaballeros1;
    }

    public static int getTotalCaballeros2() {
        return totalCaballeros2;
    }
}
