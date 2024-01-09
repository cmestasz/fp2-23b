public class Arquero extends Soldado {
    private int flechas = 10;

    public Arquero(String nombre, int equipo, int vidaInicial, int ataque, int defensa, String clase) {
        super(nombre, equipo, vidaInicial, ataque, defensa, clase);
    }

    public void disparar(Soldado otro) {
        if (flechas > 0) {
            atacar(otro, 1);
            flechas--;
        }
    }

}
