public abstract class Soldado {
    private static int totalSoldados1;
    private static int totalSoldados2;
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
        if (equipo == 1)
            totalSoldados1++;
        else
            totalSoldados2++;
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

    public static int getTotalSoldados1() {
        return totalSoldados1;
    }

    public static int getTotalSoldados2() {
        return totalSoldados2;
    }

    public void aumentarVida() {
        vidaActual++;
    }

    public void atacar(Soldado otro, int instancias) {
        otro.herir(instancias * (Math.max(0, ataque - otro.getDefensa())));
    }

    public void herir(int vida) {
        vidaActual -= vida;
    }

    public void defender() {
        defensa++;
    }
}
