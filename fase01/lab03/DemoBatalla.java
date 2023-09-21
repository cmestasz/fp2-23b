// Laboratorio Nro 3 - Actividad 1
// Autor: Christian Mestas
// Colaboro: Marco Aedo, clases DemoBatalla y Nave

import java.util.*;

public class DemoBatalla {
    public static void main(String[] args) {
        Nave[] misNaves = new Nave[10];
        Scanner sc = new Scanner(System.in);
        String nomb, col;
        int fil, punt;
        boolean est;
        for (int i = 0; i < misNaves.length; i++) {
            System.out.println("Nave " + (i + 1));
            System.out.print("Nombre: ");
            nomb = sc.nextLine();
            System.out.print("Fila: ");
            fil = sc.nextInt();
            sc.nextLine();
            System.out.print("Columna: ");
            col = sc.nextLine();
            System.out.print("Estado: ");
            est = sc.nextBoolean();
            sc.nextLine();
            System.out.print("Puntos: ");
            punt = sc.nextInt();
            sc.nextLine();
            misNaves[i] = new Nave(); // Se crea un objeto Nave y se asigna su referencia a misNaves
            misNaves[i].setNombre(nomb);
            misNaves[i].setFila(fil);
            misNaves[i].setColumna(col);
            misNaves[i].setEstado(est);
            misNaves[i].setPuntos(punt);
            System.out.println();
        }
        System.out.println("Naves creadas:");
        mostrarNaves(misNaves);
        mostrarPorNombre(misNaves);
        mostrarPorPuntos(misNaves);
        System.out.println("Nave con mayor numero de puntos: " + mostrarMayorPuntos(misNaves));
        System.out.println();
        System.out.println("Naves desordenadas: ");
        mostrarNaves(desordenar(misNaves));
    }

    // Metodo para mostrar todas las naves
    public static void mostrarNaves(Nave[] flota) {
        for (Nave nave : flota)
            System.out.println(nave.toString());
        System.out.println();
    }

    // Metodo para mostrar todas las naves de un nombre que se pide por teclado
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

    // Metodo para mostrar todas las naves con un numero de puntos inferior o igual
    // al numero de puntos que se pide por teclado
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

    // Metodo que devuelve la Nave con mayor numero de Puntos
    public static Nave mostrarMayorPuntos(Nave[] flota) {
        int maxIdx = 0;
        for (int i = 0; i < flota.length; i++) {
            if (flota[i].getPuntos() > flota[maxIdx].getPuntos())
                maxIdx = i;
        }
        return flota[maxIdx];
    }

    // Crear un metodo que devuelva un nuevo arreglo de objetos con todos los
    // objetos previamente ingresados
    // pero aleatoriamente desordenados
    public static Nave[] desordenar(Nave[] flota) {
        Nave[] nuevaFlota = new Nave[flota.length];
        Random r = new Random();
        System.arraycopy(flota, 0, nuevaFlota, 0, flota.length);
        for (int idx = 0; idx < nuevaFlota.length; idx++) {
            int nIdx = r.nextInt(nuevaFlota.length);
            Nave t = nuevaFlota[idx];
            nuevaFlota[idx] = nuevaFlota[nIdx];
            nuevaFlota[nIdx] = t;
        }
        return nuevaFlota;
    }
}