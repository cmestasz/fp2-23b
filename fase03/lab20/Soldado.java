public abstract class Soldado {
    private String nombre;
    private int equipo;
    private int vidaInicial;
    private int vidaActual;
    private int ataque;
    private int defensa;
    private String clase;

    public Soldado(String nombre, int equipo, int vidaInicial, int ataque, int defensa, String clase) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.vidaInicial = vidaInicial;
        this.vidaActual = vidaInicial;
        this.ataque = ataque;
        this.defensa = defensa;
        this.clase = clase;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEquipo() {
        return equipo;
    }

    public int getVidaInicial() {
        return vidaInicial;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public String getClase() {
        return clase;
    }

    public void aumentarVida() {
        vidaActual++;
    }
}
