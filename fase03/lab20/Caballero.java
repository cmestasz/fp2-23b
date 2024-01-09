public class Caballero extends Soldado {
    private String arma = "ESPADA";
    private boolean montado = false;

    public Caballero(String nombre, int equipo, int vidaInicial, int ataque, int defensa, String clase) {
        super(nombre, equipo, vidaInicial, ataque, defensa, clase);
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
    
}
