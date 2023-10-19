package fase02.lab07;

import java.util.ArrayList;

public class VideoJuego4 {
    public static void main(String[] args) {
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

    public static void inicializarSoldados(Soldado[][] tablero, ArrayList<Soldado> soldados, int equipo) {
    }

    public static void imprimirTablero(Soldado[][] tablero) {
    }

    public static String generarEncabezado(Soldado[][] tablero) {
    }

    public static String generarSeparacion(Soldado[][] tablero) {
    }

    public static String generarFila(Soldado[][] tablero, int f) {
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
    }
}
