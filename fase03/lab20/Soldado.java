public abstract class Soldado {
    private String nombre;
    private String reino;
    private int vidaInicial;
    private int vidaActual;
    private int ataque;
    private int defensa;
    private String clase;

    public Soldado(String nombre, String reino, int vidaInicial, int ataque, int defensa, String clase) {
        this.nombre = nombre;
        this.reino = reino;
        this.vidaInicial = vidaInicial;
        this.vidaActual = vidaInicial;
        this.ataque = ataque;
        this.defensa = defensa;
        this.clase = clase;
    }
}
