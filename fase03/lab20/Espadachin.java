public class Espadachin extends Soldado {
    private double longitudEspada;

    public Espadachin(String nombre, int equipo, int vidaInicial, int ataque, int defensa, String clase) {
        super(nombre, equipo, vidaInicial, ataque, defensa, clase);
    }

    public void generarMuroEscudos() {
        System.out.println(getNombre() + " genera un muro de escudos!");
    }
    
}
