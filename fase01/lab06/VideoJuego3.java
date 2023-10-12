package fase01.lab06;

import java.util.Random;
import java.util.ArrayList;

public class VideoJuego3 {
    public static void main(String[] args) {
        ArrayList<ArrayList<Soldado>> tablero = new ArrayList<ArrayList<Soldado>>();
        ArrayList<Soldado> soldados1 = new ArrayList<Soldado>();
        ArrayList<Soldado> soldados2 = new ArrayList<Soldado>();
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
        ordenarSoldadosSeleccion(soldados1b);
        imprimirSoldados(soldados1b);
        System.out.println();
        System.out.printf("Soldado con mayor vida del ejercito 2: %s%n", soldadoMayorVida(soldados2));
        System.out.printf("Promedio de puntos de vida del ejercito 2: %f%n", promedioPuntosVida(soldados2));
        imprimirSoldados(soldados2);
        ArrayList<Soldado> soldados2a = new ArrayList<Soldado>();
        ArrayList<Soldado> soldados2b = new ArrayList<Soldado>();
        copiarArrayList(soldados2, soldados2a);
        copiarArrayList(soldados2, soldados2b);
        System.out.println();
        ordenarSoldadosBurbuja(soldados2a);
        imprimirSoldados(soldados2a);
        ordenarSoldadosSeleccion(soldados2b);
        imprimirSoldados(soldados2b);
    }
}
