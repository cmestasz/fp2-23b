package fase02.lab07;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class VideoJuego4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ans = "s";
        while (ans.equalsIgnoreCase("s")) {
            simularBatalla();
            System.out.print("Desea simular otra batalla? (S/N): ");
            ans = sc.nextLine();
        }
    }

    public static void simularBatalla() {
        Soldado[][] tablero = new Soldado[10][10];
        ArrayList<Soldado> soldados1 = new ArrayList<Soldado>();
        ArrayList<Soldado> soldados2 = new ArrayList<Soldado>();
        inicializarSoldados(tablero, soldados1, 1);
        inicializarSoldados(tablero, soldados2, 2);
        imprimirTablero(tablero);
        System.out.printf("Soldado con mayor vida del ejercito 1: %s%n", soldadoMayorVida(soldados1));
        System.out.printf("Promedio de puntos de vida del ejercito 1: %f%n", promedioPuntosVida(soldados1));
        System.out.println("\nSoldados por orden de creacion:");
        imprimirSoldados(soldados1);
        ArrayList<Soldado> soldados1a = new ArrayList<Soldado>();
        ArrayList<Soldado> soldados1b = new ArrayList<Soldado>();
        copiarArrayList(soldados1, soldados1a);
        copiarArrayList(soldados1, soldados1b);
        System.out.println("\nSoldados ordenados por burbuja");
        ordenarSoldadosBurbuja(soldados1a);
        imprimirSoldados(soldados1a);
        System.out.println("\nSoldados ordenados por seleccion");
        ordenarSoldadosSeleccion(soldados1b);
        imprimirSoldados(soldados1b);
        System.out.println();
        System.out.printf("Soldado con mayor vida del ejercito 2: %s%n", soldadoMayorVida(soldados2));
        System.out.printf("Promedio de puntos de vida del ejercito 2: %f%n", promedioPuntosVida(soldados2));
        System.out.println("\nSoldados por orden de creacion:");
        imprimirSoldados(soldados2);
        ArrayList<Soldado> soldados2a = new ArrayList<Soldado>();
        ArrayList<Soldado> soldados2b = new ArrayList<Soldado>();
        copiarArrayList(soldados2, soldados2a);
        copiarArrayList(soldados2, soldados2b);
        System.out.println("\nSoldados ordenados por burbuja");
        ordenarSoldadosBurbuja(soldados2a);
        imprimirSoldados(soldados2a);
        System.out.println("\nSoldados ordenados por seleccion");
        ordenarSoldadosSeleccion(soldados2b);
        imprimirSoldados(soldados2b);
        System.out.println();
        imprimirGanador(soldados1, soldados2);
    }

    public static void inicializarSoldados(Soldado[][] tablero, ArrayList<Soldado> soldados,
            int equipo) {
        Random r = new Random();
        int cantidad = r.nextInt(10) + 1;
        for (int i = 0; i < cantidad; i++) {
            String nombre = "Soldado" + i + "X" + equipo;
            int vida = r.nextInt(5) + 1;
            int fila, columna;
            do {
                fila = r.nextInt(10);
                columna = r.nextInt(10);
            } while (tablero[fila][columna] != null);
            Soldado soldado = new Soldado(nombre, vida, equipo);
            tablero[fila][columna] = soldado;
            soldados.add(soldado);
        }
    }

    public static void imprimirTablero(Soldado[][] tablero) {
        System.out.print(generarEncabezado(tablero));
        String separacion = generarSeparacion(tablero);
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(separacion);
            System.out.print(generarFila(tablero, i));
        }
        System.out.print(separacion);
    }

    public static String generarEncabezado(Soldado[][] tablero) {
        String encabezado = "\t";
        for (int i = 0; i < tablero.length; i++)
            encabezado += ("   " + intToChar(i + 1) + "  ");
        encabezado += " \n";
        return encabezado;
    }

    public static String generarSeparacion(Soldado[][] tablero) {
        String fila = "\t";
        for (int i = 0; i < tablero[0].length; i++)
            fila += "------";
        fila += "-\n";
        return fila;
    }

    public static String generarFila(Soldado[][] tablero, int f) {
        String fila = (f + 1) + "\t";
        for (int i = 0; i < tablero[f].length; i++) {
            fila += "| ";
            Soldado soldado = tablero[f][i];
            if (soldado != null)
                fila += soldado.getNombre().substring(soldado.getNombre().length() - 3);
            else
                fila += "   ";
            fila += " ";
        }
        fila += "|\n";
        return fila;
    }

    public static Soldado soldadoMayorVida(ArrayList<Soldado> soldados) {
    }

    public static double promedioPuntosVida(ArrayList<Soldado> soldados) {
    }

    public static void imprimirSoldados(ArrayList<Soldado> soldados) {
    }

    public static void copiarArrayList(ArrayList<Soldado> original, ArrayList<Soldado> copia) {
    }

    public static void ordenarSoldadosBurbuja(ArrayList<Soldado> soldados) {
    }

    public static void ordenarSoldadosSeleccion(ArrayList<Soldado> soldados) {
    }

    public static void imprimirGanador(ArrayList<Soldado> soldados1, ArrayList<Soldado> soldados2) {
    }

    public static int sumaPuntosVida(ArrayList<Soldado> soldados) {
    }

    public static void intercambiar(ArrayList<Soldado> soldados, int i, int j) {
    }

    public static char intToChar(int n) {
        return (char) (n + 'A' - 1);
    }
}
