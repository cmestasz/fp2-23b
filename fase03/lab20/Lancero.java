public class Lancero extends Soldado {
    private int longitudLanza;

    public Lancero(String nombre, int equipo, int vidaInicial, int ataque, int defensa, String clase) {
        super(nombre, equipo, vidaInicial, ataque, defensa, clase);
    }

    public void schiltrom() {
        defender();
    }
    
}
