package fase03.lab22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Mapa {
    private MapaGUI gui = new MapaGUI("Videojuego");

    private HashMap<String, Soldado> soldados = new HashMap<String, Soldado>();
    private ArrayList<Soldado> listaSoldados1 = new ArrayList<Soldado>();
    private ArrayList<Soldado> listaSoldados2 = new ArrayList<Soldado>();
    private String reino1;
    private String reino2;
    private String terreno;
    private final String[] CLASES = { "CABALLERO", "ARQUERO", "ESPADACHIN", "LANCERO" };
    private final Random RANDOM = new Random();

    public Mapa(String reino1, String reino2, String terreno) {
        this.reino1 = reino1;
        this.reino2 = reino2;
        this.terreno = terreno;
        inicializarSoldados(soldados, listaSoldados1, 1);
        inicializarSoldados(soldados, listaSoldados2, 2);
        mostrarVentana();
    }

    private void mostrarVentana() {
        String desc1 = "";
        desc1 += "El terreno elegido es: " + terreno + "\n";
        desc1 += "El reino 1 es: " + reino1 + "\n";
        desc1 += "El reino 2 es: " + reino2 + "\n";
        desc1 += verificarVentaja(reino1);
        desc1 += verificarVentaja(reino2);
        String desc2 = obtenerEstado();
        String desc3 = obtenerGanador();
        gui.mostrarVentana(soldados, desc1, desc2, desc3);
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

    private String verificarVentaja(String reino) {
        switch (reino) {
            case "INGLATERRA":
                if (terreno.equals("BOSQUE"))
                    mejorarSoldados(reino);
                break;
            case "FRANCIA":
                if (terreno.equals("CAMPO ABIERTO"))
                    mejorarSoldados(reino);
                break;
            case "CASTILLA-ARAGON":
                if (terreno.equals("MONTANA"))
                    mejorarSoldados(reino);
                break;
            case "MOROS":
                if (terreno.equals("DESIERTO"))
                    mejorarSoldados(reino);
                break;
            case "SACRO IMPERIO":
                if (terreno.equals("BOSQUE") || terreno.equals("PLAYA") || terreno.equals("CAMPO ABIERTO"))
                    return mejorarSoldados(reino);
                break;
        }
        return "";
    }

    private String mejorarSoldados(String reino) {
        ArrayList<Soldado> soldados = reino.equals(reino1) ? listaSoldados1 : listaSoldados2;
        for (Soldado soldado : soldados)
            soldado.aumentarVida();
        return reino + " tiene ventaja en el terreno!\n";
    }

    private String obtenerEstado() {
        String descripcion = "";
        descripcion += String.format(
                "Ejercito 1: %s%nCantidad total de soldados: %d%nEspadachines: %d%nArqueros: %d%nCaballeros: %d%nLanceros: %d%n%n",
                reino1, Soldado.getTotalSoldados1(), Espadachin.getTotalEspadachines1(), Arquero.getTotalArqueros1(),
                Caballero.getTotalCaballeros1(), Lancero.getTotalLanceros1());
        descripcion += String.format(
                "Ejercito 2: %s%nCantidad total de soldados: %d%nEspadachines: %d%nArqueros: %d%nCaballeros: %d%nLanceros: %d%n",
                reino2, Soldado.getTotalSoldados2(), Espadachin.getTotalEspadachines2(), Arquero.getTotalArqueros2(),
                Caballero.getTotalCaballeros2(), Lancero.getTotalLanceros2());
        return descripcion;
    }

    private String obtenerGanador() {
        String descripcion = "";
        int vida1 = vidaTotal(listaSoldados1);
        int vida2 = vidaTotal(listaSoldados2);
        double chance1 = 1.0 * vida1 / (vida1 + vida2);
        double chance2 = 1 - chance1;
        descripcion += String.format("%s: %d%n%.5f%% de probabilidad de victoria%n%n", reino1, vida1, chance1);
        descripcion += String.format("%s: %d%n%.5f%% de probabilidad de victoria%n%n", reino2, vida2, chance2);
        double aleatorio = RANDOM.nextDouble(1);
        if (aleatorio < chance1)
            descripcion += ("Gana " + reino1 + "\n");
        else
            descripcion += ("Gana " + reino2 + "\n");
        descripcion += ("Aleatorio generado: " + aleatorio + "\n");
        return descripcion;
    }

    private int vidaTotal(ArrayList<Soldado> soldados) {
        int suma = 0;
        for (Soldado soldado : soldados)
            suma += soldado.getVidaActual();
        return suma;
    }

    private String generarLlave(int fila, int columna) {
        return fila + "," + columna;
    }

    public static char intToChar(int n) {
        return (char) (n + 'A' - 1);
    }

    public static int charToInt(char c) {
        return (int) (c - 'A' + 1);
    }
}