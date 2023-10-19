package fase02.lab08;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class VideoJuego5 {
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
        HashMap<String, Soldado> soldados1 = new HashMap<String, Soldado>();
        HashMap<String, Soldado> soldados2 = new HashMap<String, Soldado>();
        inicializarSoldados(soldados1, 1);
        inicializarSoldados(soldados2, 2);
        imprimirTablero(soldados1, soldados2);
        System.out.printf("Soldado con mayor vida del ejercito 1: %s%n", soldadoMayorVida(soldados1));
        System.out.printf("Promedio de puntos de vida del ejercito 1: %f%n", promedioPuntosVida(soldados1));
        System.out.println("\nSoldados:");
        imprimirHashMap(soldados1);
        HashMap<String, Soldado> soldados1a = new HashMap<String, Soldado>();
        HashMap<String, Soldado> soldados1b = new HashMap<String, Soldado>();
        copiarHashMap(soldados1, soldados1a);
        copiarHashMap(soldados1, soldados1b);
        System.out.println("\nSoldados ordenados por burbuja");
        ArrayList<Soldado> soldados1aO = ordenarSoldadosBurbuja(soldados1a);
        imprimirArrayList(soldados1aO);
        System.out.println("\nSoldados ordenados por seleccion");
        ArrayList<Soldado> soldados1bO = ordenarSoldadosSeleccion(soldados1b);
        imprimirArrayList(soldados1bO);
        System.out.println();
        System.out.printf("Soldado con mayor vida del ejercito 2: %s%n", soldadoMayorVida(soldados2));
        System.out.printf("Promedio de puntos de vida del ejercito 2: %f%n", promedioPuntosVida(soldados2));
        System.out.println("\nSoldados:");
        imprimirHashMap(soldados2);
        HashMap<String, Soldado> soldados2a = new HashMap<String, Soldado>();
        HashMap<String, Soldado> soldados2b = new HashMap<String, Soldado>();
        copiarHashMap(soldados2, soldados2a);
        copiarHashMap(soldados2, soldados2b);
        System.out.println("\nSoldados ordenados por burbuja");
        ArrayList<Soldado> soldados2aO = ordenarSoldadosBurbuja(soldados2a);
        imprimirArrayList(soldados2aO);
        System.out.println("\nSoldados ordenados por seleccion");
        ArrayList<Soldado> soldados2bO = ordenarSoldadosSeleccion(soldados2b);
        imprimirArrayList(soldados2bO);
        System.out.println();
        imprimirGanador(soldados1, soldados2);
    }

    public static void inicializarSoldados(HashMap<String, Soldado> soldados,
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
            } while (soldados.containsKey(fila + "," + columna));
            Soldado soldado = new Soldado(nombre, vida, equipo);
            soldados.put(fila + "," + columna, soldado);
        }
    }

    public static void imprimirTablero(HashMap<String, Soldado> soldados1, HashMap<String, Soldado> soldados2) {
    }

    public static String generarEncabezado() {
    }

    public static String generarSeparacion() {
    }

    public static String generarFila(HashMap<String, Soldado> soldados1, HashMap<String, Soldado> soldados2, int f) {
    }

    public static Soldado soldadoMayorVida(HashMap<String, Soldado> soldados) {
    }

    public static double promedioPuntosVida(HashMap<String, Soldado> soldados) {
    }

    public static void imprimirHashMap(HashMap<String, Soldado> soldados) {
    }

    public static void imprimirArrayList(ArrayList<Soldado> soldados) {
    }

    public static void copiarHashMap(HashMap<String, Soldado> original, HashMap<String, Soldado> copia) {
    }

    public static ArrayList<Soldado> ordenarSoldadosBurbuja(HashMap<String, Soldado> soldados) {
    }

    public static ArrayList<Soldado> ordenarSoldadosSeleccion(HashMap<String, Soldado> soldados) {
    }

    public static void imprimirGanador(HashMap<String, Soldado> soldados1, HashMap<String, Soldado> soldados2) {
    }

    public static int sumaPuntosVida(HashMap<String, Soldado> soldados) {
    }

    public static void intercambiar(ArrayList<Soldado> soldados, int i, int j) {
    }

    public static char intToChar(int n) {
    }
}
