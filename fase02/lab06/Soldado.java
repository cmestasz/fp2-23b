package fase02.lab06;

public class Soldado {
    private String nombre;
    private int vida;
    private int equipo;

    public Soldado(String nombre, int vida, int equipo) {
        setNombre(nombre);
        setVida(vida);
        setEquipo(equipo);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setEquipo(int equipo) {
        this.equipo = equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getEquipo() {
        return equipo;
    }

    public String toString() {
        return (nombre + " - " + vida);
    }
}
