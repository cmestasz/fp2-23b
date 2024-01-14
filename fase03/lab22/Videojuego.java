package fase03.lab22;

import javax.swing.JOptionPane;

public class Videojuego {
    public static void main(String[] args) {
        new Videojuego();
    }

    public Videojuego() {
        new MapaSuperior(this);
    }

    public void continuar() {
        int continuar = JOptionPane.showConfirmDialog(null, "Desea jugar de nuevo? (S/N): ");
        if (continuar == JOptionPane.YES_OPTION)
            new MapaSuperior(this);
    }
}
