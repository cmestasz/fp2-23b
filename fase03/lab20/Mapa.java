import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Mapa {
    private HashMap<String, Soldado> soldados = new HashMap<String, Soldado>();
    private ArrayList<Soldado> listaSoldados1 = new ArrayList<Soldado>();
    private ArrayList<Soldado> listaSoldados2 = new ArrayList<Soldado>();
    private String reino1;
    private String reino2;
    private String terreno;
    private final String[] CLASES = { "CABALLERO", "ARQUERO", "ESPADACHIN", "LANCERO" };
    private final String[] TIPOS = { "BOSQUE", "CAMPO ABIERTO", "MONTANA", "DESIERTO", "PLAYA" };
    private final String[] REINOS = { "INGLATERRA", "FRANCIA", "CASTILLA-ARAGON", "MOROS", "SACRO IMPERIO" };
    private final Random RANDOM = new Random();

    public Mapa() {
        inicializarSoldados(soldados, listaSoldados1, 1);
        inicializarSoldados(soldados, listaSoldados2, 2);
        imprimirTablero(soldados);
        terreno = TIPOS[RANDOM.nextInt(TIPOS.length)];
        reino1 = REINOS[RANDOM.nextInt(REINOS.length)];
        reino2 = REINOS[RANDOM.nextInt(REINOS.length)];
        System.out.println("El terreno elegido es: " + terreno);
        System.out.println("El reino 1 es: " + reino1);
        System.out.println("El reino 2 es: " + reino2);
        System.out.println();
        verificarVentaja(reino1);
        verificarVentaja(reino2);
        System.out.println();
        imprimirEstado();
        System.out.println();
        imprimirGanador();
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

    private void imprimirTablero(HashMap<String, Soldado> mapaSoldados) {
        System.out.print(generarEncabezado(mapaSoldados));
        String separacion = generarSeparacion(mapaSoldados);
        for (int i = 0; i < 10; i++) {
            System.out.print(separacion);
            System.out.print(generarFila(mapaSoldados, i));
        }
        System.out.print(separacion);
    }

    private String generarEncabezado(HashMap<String, Soldado> mapaSoldados) {
        String encabezado = "\t";
        for (int i = 0; i < 10; i++)
            encabezado += ("   " + intToChar(i + 1) + "  ");
        encabezado += " \n";
        return encabezado;
    }

    private String generarSeparacion(HashMap<String, Soldado> mapaSoldados) {
        String fila = "\t";
        for (int i = 0; i < 10; i++)
            fila += "-------";
        fila += "-\n";
        return fila;
    }

    private String generarFila(HashMap<String, Soldado> mapaSoldados, int f) {
        String fila = (f + 1) + "\t";
        for (int i = 0; i < 10; i++) {
            fila += "| ";
            Soldado soldado = mapaSoldados.get(generarLlave(f, i));
            if (soldado != null) {
                String nombre = soldado.getNombre();
                fila += nombre.charAt(0) + nombre.substring(nombre.length() - 3);
            } else {
                fila += "    ";
            }
            fila += " ";
        }
        fila += "|\n";
        return fila;
    }

    private void verificarVentaja(String reino) {
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
                    mejorarSoldados(reino);
                break;
        }
    }

    private void mejorarSoldados(String reino) {
        System.out.println(reino + " tiene ventaja en el terreno!");
        ArrayList<Soldado> soldados = reino.equals(reino1) ? listaSoldados1 : listaSoldados2;
        for (Soldado soldado : soldados)
            soldado.aumentarVida();
    }

    private void imprimirEstado() {
        System.out.printf(
                "Ejercito 1: %s%nCantidad total de soldados: %d%nEspadachines: %d%nArqueros: %d%nCaballeros: %d%nLanceros: %d%n",
                reino1, Soldado.getTotalSoldados1(), Espadachin.getTotalEspadachines1(), Arquero.getTotalArqueros1(),
                Caballero.getTotalCaballeros1(), Lancero.getTotalLanceros1());
        System.out.println();
        System.out.printf(
                "Ejercito 2: %s%nCantidad total de soldados: %d%nEspadachines: %d%nArqueros: %d%nCaballeros: %d%nLanceros: %d%n",
                reino2, Soldado.getTotalSoldados2(), Espadachin.getTotalEspadachines2(), Arquero.getTotalArqueros2(),
                Caballero.getTotalCaballeros2(), Lancero.getTotalLanceros2());
    }

    private void imprimirGanador() {
        int vida1 = vidaTotal(listaSoldados1);
        int vida2 = vidaTotal(listaSoldados2);
        double chance1 = 1.0 * vida1 / (vida1 + vida2);
        double chance2 = 1 - chance1;
        System.out.println(reino1 + ": " + vida1 + "\t\t" + chance1 * 100 + "% de probabilidad de victoria");
        System.out.println(reino2 + ": " + vida2 + "\t\t" + chance2 * 100 + "% de probabilidad de victoria");
        double aleatorio = RANDOM.nextDouble(1);
        if (aleatorio < chance1)
            System.out.println("Gana " + reino1);
        else
            System.out.println("Gana " + reino2);
        System.out.println("Aleatorio generado: " + aleatorio);
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