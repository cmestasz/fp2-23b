package fase01.lab05;

public class Soldado {
    private String nombre;
    private int vida;

    public Soldado(String nombre, int vida) {
        setNombre(nombre);
        setVida(vida);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public String toString() {
        return ("Nombre: " + nombre + ". Vida: " + vida);
    }
}
