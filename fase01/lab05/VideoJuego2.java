package fase01.lab05;

import java.util.Random;

public class VideoJuego2 {
    public static void main(String[] args) {
        Random r = new Random();
        int cantidad = r.nextInt(10) + 1;
        Soldado[][] tablero = new Soldado[10][10];
        Soldado[] soldados = new Soldado[cantidad];
        inicializarSoldados(tablero, soldados);
        imprimirTablero(tablero);
        System.out.printf("Soldado con mayor vida: %s%n", soldadoMayorVida(soldados));
        System.out.printf("Promedio de puntos de vida: %f%n", promedioPuntosVida(soldados));
        System.out.printf("Nivel de vida del ejercito: %d%n", sumaPuntosVida(soldados));
        Soldado[] soldados2 = new Soldado[soldados.length];
        Soldado[] soldados3 = new Soldado[soldados.length];
        System.arraycopy(soldados, 0, soldados2, 0, soldados.length);
        System.arraycopy(soldados, 0, soldados3, 0, soldados.length);
        System.out.println();
        ordenarSoldadosBurbuja(soldados2);
        imprimirSoldados(soldados2);
        System.out.println();
        ordenarSoldadosSeleccion(soldados3);
        imprimirSoldados(soldados3);
    }

    private static void inicializarSoldados(Soldado[][] tablero, Soldado[] soldados) {
        Random r = new Random();
        for (int i = 0; i < soldados.length; i++) {
            String nombre = "Soldado" + i;
            int vida = r.nextInt(5) + 1;
            int fila, columna;
            do {
                fila = r.nextInt(10);
                columna = r.nextInt(10);
            } while (tablero[fila][columna] != null);
            Soldado soldado = new Soldado(nombre, vida);
            tablero[fila][columna] = soldado;
            soldados[i] = soldado;
        }
    }

    private static void imprimirTablero(Soldado[][] soldados) {
        System.out.print(generarEncabezado(soldados));
        String subfila0 = generarSubfila0(soldados);
        for (int i = 0; i < soldados.length; i++) {
            System.out.print(subfila0);
            System.out.print(generarSubfila1(soldados, i));
        }
        System.out.print(subfila0);
    }

    public static String generarEncabezado(Soldado[][] soldados) {
        String encabezado = "\t";
        for (int i = 0; i < soldados.length; i++)
            encabezado += ("  " + intToChar(i + 1) + " ");
        encabezado += " \n";
        return encabezado;
    }

    public static String generarSubfila0(Soldado[][] soldados) {
        String fila = "\t";
        for (int i = 0; i < soldados[0].length; i++)
            fila += "----";
        fila += "-\n";
        return fila;
    }

    public static String generarSubfila1(Soldado[][] soldados, int f) {
        String fila = (f + 1) + "\t";
        for (int i = 0; i < soldados[f].length; i++) {
            fila += "| ";
            Soldado soldado = soldados[f][i];
            if (soldado != null)
                fila += soldado.getNombre().charAt(soldado.getNombre().length() - 1);
            else
                fila += " ";
            fila += " ";
        }
        fila += "|\n";
        return fila;
    }

    private static Soldado soldadoMayorVida(Soldado[] soldados) {
        return null;
    }

    private static double promedioPuntosVida(Soldado[] soldados) {
        return 0;
    }

    private static int sumaPuntosVida(Soldado[] soldados) {
        return 0;
    }

    private static void ordenarSoldadosBurbuja(Soldado[] soldados) {
    }

    private static void ordenarSoldadosSeleccion(Soldado[] soldados) {
    }

    private static void imprimirSoldados(Soldado[] soldados) {
    }

    public static int charToInt(char c) {
        return c - 'A' + 1;
    }

    public static char intToChar(int n) {
        return (char) (n + 'A' - 1);
    }
}
