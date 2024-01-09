import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Mapa {
    private HashMap<String, Soldado> soldados;
    private ArrayList<Soldado> listaSoldados1;
    private ArrayList<Soldado> listaSoldados2;
    private String tipo;
    private final String[] CLASES = { "CABALLERO", "ARQUERO", "ESPADACHIN", "LANCERO" };
    private final String[] TIPOS = { "BOSQUE", "CAMPO ABIERTO", "MONTANA", "DESIERTO", "PLAYA" };
    private final String[] REINOS = { "INGLATERRA", "FRANCIA", "CASTILLA-ARAGON", "MOROS", "SACRO IMPERIO" };
    private final Random RANDOM = new Random();

    public Mapa() {
        tipo = TIPOS[RANDOM.nextInt(TIPOS.length)];
        inicializarSoldados(soldados, listaSoldados1, 1);
        inicializarSoldados(soldados, listaSoldados2, 2);
    }

    private void inicializarSoldados(HashMap<String, Soldado> mapaSoldados, ArrayList<Soldado> listaSoldados,
            int equipo) {
        int cantidad = RANDOM.nextInt(10) + 1;
        for (int i = 0; i < cantidad; i++) {
            String clase = CLASES[RANDOM.nextInt(CLASES.length)];
            int vida, ataque, defensa, fila, columna;
            do {
                fila = RANDOM.nextInt(10);
                columna = RANDOM.nextInt(10);
            } while (mapaSoldados.containsKey(generarLlave(fila, columna)));
            String nombre = clase + i + "X" + equipo;
            Soldado soldado = null;
            switch (clase) {
                case "CABALLERO":
                    ataque = 13;
                    defensa = 7;
                    vida = RANDOM.nextInt(3) + 10;
                    soldado = new Caballero(nombre, equipo, vida, ataque, defensa, clase);
                    break;
                case "ARQUERO":
                    ataque = 7;
                    defensa = 3;
                    vida = RANDOM.nextInt(3) + 3;
                    soldado = new Arquero(nombre, equipo, vida, ataque, defensa, clase);
                    break;
                case "ESPADACHIN":
                    ataque = 10;
                    defensa = 8;
                    vida = RANDOM.nextInt(3) + 8;
                    soldado = new Espadachin(nombre, equipo, vida, ataque, defensa, clase);
                    break;
                case "LANCERO":
                    ataque = 5;
                    defensa = 10;
                    vida = RANDOM.nextInt(4) + 5;
                    soldado = new Lancero(nombre, equipo, vida, ataque, defensa, clase);
                    break;
            }
            mapaSoldados.put(generarLlave(fila, columna), soldado);
            listaSoldados.add(soldado);
        }
    }

    private String generarLlave(int fila, int columna) {
        return fila + "," + columna;
    }
}