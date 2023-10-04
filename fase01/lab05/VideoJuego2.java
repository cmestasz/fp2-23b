package fase01.lab05;

import java.util.Scanner;
import java.util.Random;

public class VideoJuego2 {
    public static void main(String[] args) {
        Soldado[][] soldados = new Soldado[10][10];
        inicializarSoldados(soldados);
        imprimirTablero(soldados);
        System.out.println("Soldado con mayor vida: " + soldadoMayorVida(soldados));
        System.out.println("Promedio de puntos de vida: " + promedioPuntosVida(soldados));
        System.out.println("Nivel de vida del ejercito: " + sumaPuntosVida(soldados));
        Soldado[][] soldados2 = new Soldado[10][10];
        Soldado[][] soldados3 = new Soldado[10][10];
        System.arraycopy(soldados, 0, soldados2, 0, soldados.length);
        System.arraycopy(soldados, 0, soldados3, 0, soldados.length);
        ordenarSoldadosBurbuja(soldados2);
        imprimirSoldados(soldados2);
        ordenarSoldadosSeleccion(soldados3);
        imprimirSoldados(soldados3);
    }

    private static void inicializarSoldados(Soldado[][] soldados) {
        Random r = new Random();
        int cantidad = r.nextInt(10) + 1;
        for (int i = 0; i < cantidad; i++) {
            String nombre = "Soldado" + i;
            int vida = r.nextInt(5) + 1;
            int fila, columna;
            do {
                fila = r.nextInt(10);
                columna = r.nextInt(10);
            } while (soldados[fila][columna] != null);
            soldados[fila][columna] = new Soldado(nombre, vida);
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

    private static Soldado soldadoMayorVida(Soldado[][] soldados) {
        return null;
    }

    private static Soldado promedioPuntosVida(Soldado[][] soldados) {
        return null;
    }

    private static Soldado sumaPuntosVida(Soldado[][] soldados) {
        return null;
    }

    private static void ordenarSoldadosBurbuja(Soldado[][] soldados2) {
    }

    private static void ordenarSoldadosSeleccion(Soldado[][] soldados3) {
    }

    private static void imprimirSoldados(Soldado[][] soldados3) {
    }

    public static int charToInt(char c) {
        return c - 'A' + 1;
    }

    public static char intToChar(int n) {
        return (char) (n + 'A' - 1);
    }
}
