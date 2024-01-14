package fase03.lab22;

import java.util.HashMap;
import java.util.Random;

public class MapaSuperior {
    private MapaSuperiorGUI gui;

    private Videojuego videojuego;
    private HashMap<String, String> batallas = new HashMap<String, String>();
    private final String[] TIPOS = { "BOSQUE", "CAMPO ABIERTO", "MONTANA", "DESIERTO", "PLAYA" };
    private final String[] REINOS = { "INGLATERRA", "FRANCIA", "CASTILLA-ARAGON", "MOROS", "SACRO IMPERIO" };
    private final Random RANDOM = new Random();

    public MapaSuperior(Videojuego videojuego) {
        this.videojuego = videojuego;
        inicializarBatallas(batallas);
        String reino1 = REINOS[RANDOM.nextInt(REINOS.length)];
        String reino2 = REINOS[RANDOM.nextInt(REINOS.length)];
        String terreno = TIPOS[RANDOM.nextInt(TIPOS.length)];
        gui = new MapaSuperiorGUI("Videojuego", reino1, reino2, terreno, batallas, this);
        gui.mostrarVentana();
    }

    private void inicializarBatallas(HashMap<String, String> batallas) {
        int cantidad = RANDOM.nextInt(10) + 1;
        for (int i = 0; i < cantidad; i++) {
            int fila, columna;
            do {
                fila = RANDOM.nextInt(10);
                columna = RANDOM.nextInt(10);
            } while (batallas.containsKey(generarLlave(fila, columna)));
            String nombre = "#" + i;
            
            batallas.put(generarLlave(fila, columna), nombre);
        }
    }

    public void terminarGuerra() {
        videojuego.continuar();
    }

    private String generarLlave(int fila, int columna) {
        return fila + "," + columna;
    }
}
