// Laboratorio Nro 3 - Actividad 1
// Autor: Christian Mestas
// Colaboro: Marco Aedo, clases DemoBatalla y Nave

import java.util.*;

public class DemoBatalla {
    public static void main(String[] args) {
        Nave[] misNaves = new Nave[3];
        Scanner sc = new Scanner(System.in);
        String nomb, col;
        int fil, punt;
        boolean est;
        for (int i = 0; i < misNaves.length; i++) {
            System.out.println("\nNave " + (i + 1));
            System.out.print("Nombre: ");
            nomb = sc.next();
            System.out.println("Fila ");
            fil = sc.nextInt();
            System.out.print("Columna: ");
            col = sc.next();
            System.out.print("Estado: ");
            est = sc.nextBoolean();
            System.out.print("Puntos: ");
            punt = sc.nextInt();
            misNaves[i] = new Nave(); // Se crea un objeto Nave y se asigna su referencia a misNaves
            misNaves[i].setNombre(nomb);
            misNaves[i].setFila(fil);
            misNaves[i].setColumna(col);
            misNaves[i].setEstado(est);
            misNaves[i].setPuntos(punt);
        }
        System.out.println("\nNaves creadas:");
        mostrarNaves(misNaves);
        mostrarPorNombre(misNaves);
        mostrarPorPuntos(misNaves);
        System.out.println("Nave con mayor número de puntos: " + mostrarMayorPuntos(misNaves));
    }

    // Método para mostrar todas las naves
    public static void mostrarNaves(Nave[] flota) {
        for (Nave nave : flota)
            System.out.println(nave.toString());
        System.out.println();
    }

    // Método para mostrar todas las naves de un nombre que se pide por teclado
    public static void mostrarPorNombre(Nave[] flota) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre a buscar:");
        String nombre = sc.nextLine();
        System.out.println("Naves con el nombre " + nombre + ":");
        for (Nave nave : flota) {
            if (nave.getNombre().equals(nombre))
                System.out.println(nave.toString());
        }
        System.out.println();
    }

    // Método para mostrar todas las naves con un número de puntos inferior o igual
    // al número de puntos que se pide por teclado
    public static void mostrarPorPuntos(Nave[] flota) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de puntos maximos:");
        int puntos = sc.nextInt();
        sc.nextLine();
        System.out.println("Naves con " + puntos + " puntos como maximo:");
        for (Nave nave : flota) {
            if (nave.getPuntos() <= puntos)
                System.out.println(nave.toString());
        }
        System.out.println();
    }

    // Método que devuelve la Nave con mayor número de Puntos
    public static Nave mostrarMayorPuntos(Nave[] flota) {
        int maxIdx = 0;
        for (int i = 0; i < flota.length; i++) {
            if (flota[i].getPuntos() > flota[maxIdx].getPuntos())
                maxIdx = i;
        }
        return flota[maxIdx];
    }
    // Crear un método que devuelva un nuevo arreglo de objetos con todos los
    // objetos previamente ingresados
    // pero aleatoriamente desordenados
}