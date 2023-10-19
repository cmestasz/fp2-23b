package fase02.lab08;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
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
        System.out.print(generarEncabezado());
        String separacion = generarSeparacion();
        for (int i = 0; i < 10; i++) {
            System.out.print(separacion);
            System.out.print(generarFila(soldados1, soldados2, i));
        }
        System.out.print(separacion);
    }

    public static String generarEncabezado() {
        String encabezado = "\t";
        for (int i = 0; i < 10; i++)
            encabezado += ("   " + intToChar(i + 1) + "  ");
        encabezado += " \n";
        return encabezado;
    }

    public static String generarSeparacion() {
        String fila = "\t";
        for (int i = 0; i < 10; i++)
            fila += "------";
        fila += "-\n";
        return fila;
    }

    public static String generarFila(HashMap<String, Soldado> soldados1, HashMap<String, Soldado> soldados2, int f) {
        String fila = (f + 1) + "\t";
        for (int i = 0; i < 10; i++) {
            fila += "| ";
            Soldado soldado = null;
            String llave = f + "," + i;
            if (soldados1.containsKey(llave))
                soldado = soldados1.get(llave);
            else if (soldados2.containsKey(llave))
                soldado = soldados2.get(llave);
            else
                fila += "   ";
            if (soldado != null)
                fila += soldado.getNombre().substring(soldado.getNombre().length() - 3);
            fila += " ";
        }
        fila += "|\n";
        return fila;
    }

    public static Soldado soldadoMayorVida(HashMap<String, Soldado> soldados) {
        String llave = null;
        for (Entry<String, Soldado> entrySet : soldados.entrySet()) {
            if (llave == null || entrySet.getValue().getVida() > soldados.get(llave).getVida())
                llave = entrySet.getKey();
        }
        return soldados.get(llave);
    }

    public static double promedioPuntosVida(HashMap<String, Soldado> soldados) {
        int suma = sumaPuntosVida(soldados);
        return 1.0 * suma / soldados.size();
    }

    public static void imprimirHashMap(HashMap<String, Soldado> soldados) {
        for (Entry<String, Soldado> entrySet : soldados.entrySet())
            System.out.println(entrySet.getValue());
    }

    public static void imprimirArrayList(ArrayList<Soldado> soldados) {
        for (Soldado soldado : soldados)
            System.out.println(soldado);
    }

    public static void copiarHashMap(HashMap<String, Soldado> original, HashMap<String, Soldado> copia) {
        for (Entry<String, Soldado> entrySet : original.entrySet())
            copia.put(entrySet.getKey(), entrySet.getValue());
    }

    public static ArrayList<Soldado> ordenarSoldadosBurbuja(HashMap<String, Soldado> soldados) {
        ArrayList<Soldado> arreglo = new ArrayList<Soldado>();
        for (Entry<String, Soldado> entrySet : soldados.entrySet())
            arreglo.add(entrySet.getValue());
        for (int i = 0; i < arreglo.size() - 1; i++) {
            for (int j = 0; j < arreglo.size() - i - 1; j++) {
                int vida1 = arreglo.get(j).getVida();
                int vida2 = arreglo.get(j + 1).getVida();
                if (vida1 < vida2)
                    intercambiar(arreglo, j, j + 1);
            }
        }
        return arreglo;
    }

    public static ArrayList<Soldado> ordenarSoldadosSeleccion(HashMap<String, Soldado> soldados) {
        ArrayList<Soldado> arreglo = new ArrayList<Soldado>();
        for (Entry<String, Soldado> entrySet : soldados.entrySet())
            arreglo.add(entrySet.getValue());
        for (int i = 0; i < arreglo.size() - 1; i++) {
            int idx = i;
            for (int j = i + 1; j < arreglo.size(); j++) {
                int vida1 = arreglo.get(j).getVida();
                int vida2 = arreglo.get(idx).getVida();
                if (vida1 > vida2)
                    idx = j;
            }
            intercambiar(arreglo, i, idx);
        }
        return arreglo;
    }

    public static void imprimirGanador(HashMap<String, Soldado> soldados1, HashMap<String, Soldado> soldados2) {
    }

    public static int sumaPuntosVida(HashMap<String, Soldado> soldados) {
        int suma = 0;
        for (Entry<String, Soldado> entrySet : soldados.entrySet())
            suma += entrySet.getValue().getVida();
        return suma;
    }

    public static void intercambiar(ArrayList<Soldado> soldados, int i, int j) {
        Soldado t = soldados.get(i);
        soldados.set(i, soldados.get(j));
        soldados.set(j, t);
    }

    public static char intToChar(int n) {
        return (char) (n + 'A' - 1);
    }
}
