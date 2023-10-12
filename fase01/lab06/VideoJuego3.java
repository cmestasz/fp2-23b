package fase01.lab06;

import java.util.Random;
import java.util.ArrayList;

public class VideoJuego3 {
    public static void main(String[] args) {
        ArrayList<ArrayList<Soldado>> tablero = new ArrayList<ArrayList<Soldado>>();
        ArrayList<Soldado> soldados1 = new ArrayList<Soldado>();
        ArrayList<Soldado> soldados2 = new ArrayList<Soldado>();
        inicializarTablero(tablero, 10);
        inicializarSoldados(tablero, soldados1, 1);
        inicializarSoldados(tablero, soldados2, 2);
        imprimirTablero(tablero);
        System.out.printf("Soldado con mayor vida del ejercito 1: %s%n", soldadoMayorVida(soldados1));
        System.out.printf("Promedio de puntos de vida del ejercito 1: %f%n", promedioPuntosVida(soldados1));
        imprimirSoldados(soldados1);
        ArrayList<Soldado> soldados1a = new ArrayList<Soldado>();
        ArrayList<Soldado> soldados1b = new ArrayList<Soldado>();
        copiarArrayList(soldados1, soldados1a);
        copiarArrayList(soldados1, soldados1b);
        System.out.println();
        ordenarSoldadosBurbuja(soldados1a);
        imprimirSoldados(soldados1a);
        /*
         * ordenarSoldadosSeleccion(soldados1b);
         * imprimirSoldados(soldados1b);
         * System.out.println();
         * System.out.printf("Soldado con mayor vida del ejercito 2: %s%n",
         * soldadoMayorVida(soldados2));
         * System.out.printf("Promedio de puntos de vida del ejercito 2: %f%n",
         * promedioPuntosVida(soldados2));
         * imprimirSoldados(soldados2);
         * ArrayList<Soldado> soldados2a = new ArrayList<Soldado>();
         * ArrayList<Soldado> soldados2b = new ArrayList<Soldado>();
         * copiarArrayList(soldados2, soldados2a);
         * copiarArrayList(soldados2, soldados2b);
         * System.out.println();
         * ordenarSoldadosBurbuja(soldados2a);
         * imprimirSoldados(soldados2a);
         * ordenarSoldadosSeleccion(soldados2b);
         * imprimirSoldados(soldados2b);
         */
    }

    public static void inicializarTablero(ArrayList<ArrayList<Soldado>> tablero, int tam) {
        for (int i = 0; i < tam; i++) {
            tablero.add(new ArrayList<Soldado>());
            for (int j = 0; j < tam; j++) {
                tablero.get(i).add(null);
            }
        }
    }

    public static void inicializarSoldados(ArrayList<ArrayList<Soldado>> tablero, ArrayList<Soldado> soldados,
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
            } while (tablero.get(fila).get(columna) != null);
            Soldado soldado = new Soldado(nombre, vida, equipo);
            tablero.get(fila).set(columna, soldado);
            soldados.add(soldado);
        }
    }

    public static void imprimirTablero(ArrayList<ArrayList<Soldado>> tablero) {
        System.out.print(generarEncabezado(tablero));
        String separacion = generarSeparacion(tablero);
        for (int i = 0; i < tablero.size(); i++) {
            System.out.print(separacion);
            System.out.print(generarFila(tablero, i));
        }
        System.out.print(separacion);
    }

    public static String generarEncabezado(ArrayList<ArrayList<Soldado>> tablero) {
        String encabezado = "\t";
        for (int i = 0; i < tablero.size(); i++)
            encabezado += ("   " + intToChar(i + 1) + "  ");
        encabezado += " \n";
        return encabezado;
    }

    public static String generarSeparacion(ArrayList<ArrayList<Soldado>> tablero) {
        String fila = "\t";
        for (int i = 0; i < tablero.get(0).size(); i++)
            fila += "------";
        fila += "-\n";
        return fila;
    }

    public static String generarFila(ArrayList<ArrayList<Soldado>> tablero, int f) {
        String fila = (f + 1) + "\t";
        for (int i = 0; i < tablero.get(f).size(); i++) {
            fila += "| ";
            Soldado soldado = tablero.get(f).get(i);
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
        int idx = 0;
        for (int i = 1; i < soldados.size(); i++) {
            if (soldados.get(i).getVida() > soldados.get(idx).getVida())
                idx = i;
        }
        return soldados.get(idx);
    }

    public static double promedioPuntosVida(ArrayList<Soldado> soldados) {
        int suma = 0;
        for (int i = 0; i < soldados.size(); i++)
            suma += soldados.get(i).getVida();
        return 1.0 * suma / soldados.size();
    }

    public static void imprimirSoldados(ArrayList<Soldado> soldados) {
        for (Soldado soldado : soldados)
            System.out.println(soldado);
    }

    public static void copiarArrayList(ArrayList<Soldado> original, ArrayList<Soldado> copia) {
        for (Soldado soldado : original)
            copia.add(soldado);
    }

    public static void ordenarSoldadosBurbuja(ArrayList<Soldado> soldados) {
        for (int i = 0; i < soldados.size() - 1; i++) {
            for (int j = 0; j < soldados.size() - i - 1; j++) {
                int vida1 = soldados.get(j).getVida();
                int vida2 = soldados.get(j + 1).getVida();
                if (vida1 < vida2)
                    intercambiar(soldados, j, j + 1);
            }
        }
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
