package fase02.lab12;

public class Soldado {
    private String nombre;
    private int equipo;
    private int ataque;
    private int defensa;
    private int vidaInicial;
    private int vidaActual;
    private int velocidad;
    private String actitud;
    private boolean vive;

    public Soldado(String nombreN, int equipoN, int ataqueN, int defensaN, int vidaInicialN) {
        nombre = nombreN;
        equipo = equipoN;
        ataque = ataqueN;
        defensa = defensaN;
        vidaInicial = vidaInicialN;
        vidaActual = vidaInicial;
        actitud = "defensiva";
        vive = true;
    }

    public Soldado(String nombreN, int equipoN, int ataqueN, int defensaN) {
        this(nombreN, equipoN, ataqueN, defensaN, 5);
    }

    public Soldado(String nombreN, int equipoN, int vidaInicialN) {
        this(nombreN, equipoN, 5, 5, vidaInicialN);
    }

    public Soldado() {

    }

    public void setVidaActual(int vidaActualN) {
        vidaActual = vidaActualN;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEquipo() {
        return equipo;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public Soldado getCopia() {
        return new Soldado(nombre, equipo, ataque, defensa, vidaInicial);
    }

    public boolean vive() {
        return vive;
    }

    public void atacar() {
        actitud = "ofensiva";
        avanzar();
    }

    public void defender() {
        actitud = "defensiva";
        parar();
    }

    public void huir() {
        actitud = "fuga";
        velocidad = 2;
    }

    public void avanzar() {
        velocidad = 1;
    }

    public void parar() {
        velocidad = 0;
    }

    public void retroceder() {
        if (velocidad > 0)
            defender();
        else
            velocidad = -1;
    }

    public void serAtacado(int d) {
        vidaActual -= d;
        if (vidaActual <= 0)
            morir();
    }

    public void morir() {
        vive = false;
    }

    public void mejorar() {
        vidaActual++;
    }

    public Soldado sumar(Soldado otro) {
        Soldado nuevo = new Soldado();
        nuevo.setVidaActual(vidaActual + otro.getVidaActual());
        nuevo.setAtaque(ataque + otro.getAtaque());
        nuevo.setDefensa(defensa + otro.getDefensa());
        nuevo.setVelocidad(velocidad + otro.getVelocidad());
        return nuevo;
    }

    public String toString() {
        return nombre + ": Vida: " + vidaActual + ". At: " + ataque + ". Df: " + defensa + ". Vl: " + velocidad;
    }
}
