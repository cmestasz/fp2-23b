// Laboratorio Nro 4 - Actividad 1
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
        System.out.println("Nave con mayor numero de puntos: " + mostrarMayorPuntos(misNaves) + "\n");

        // leer un nombre
        // mostrar los datos de la nave con dicho nombre, mensaje de no encontrado en
        // caso contrario
        System.out.println("Ingrese el nombre a buscar:");
        String nombre = sc.nextLine();
        int pos = busquedaLinealNombre(misNaves, nombre);
        if (pos == -1)
            System.out.println("Nave no encontrada.\n");
        else
            System.out.println("Nave encontrada: " + misNaves[pos] + "\n");
        System.out.println("Naves ordenadas por puntos:");
        ordenarPorPuntosBurbuja(misNaves);
        mostrarNaves(misNaves);
        System.out.println("Naves ordenadas por nombre:");
        ordenarPorNombreBurbuja(misNaves);
        mostrarNaves(misNaves);

        // mostrar los datos de la nave con dicho nombre, mensaje de no encontrado en
        // caso contrario
        System.out.println("Ingrese el nombre a buscar:");
        nombre = sc.nextLine();
        pos = busquedaBinariaNombre(misNaves, nombre);
        if (pos == -1)
            System.out.println("Nave no encontrada.\n");
        else
            System.out.println("Nave encontrada: " + misNaves[pos] + "\n");
        System.out.println("Naves ordenadas por puntos:");
        ordenarPorPuntosSeleccion(misNaves);
        mostrarNaves(misNaves);
        System.out.println("Naves ordenadas por nombre:");
        ordenarPorNombreSeleccion(misNaves);
        mostrarNaves(misNaves);
        System.out.println("Naves ordenadas por puntos (Invertido):");
        ordenarPorPuntosInsercion(misNaves);
        mostrarNaves(misNaves);
        System.out.println("Naves ordenadas por nombre (Invertido):");
        ordenarPorNombreInsercion(misNaves);
        mostrarNaves(misNaves);
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

    // Metodo para buscar la primera nave con un nombre que se pidio por teclado
    public static int busquedaLinealNombre(Nave[] flota, String s) {
        for (int i = 0; i < flota.length; i++) {
            if (flota[i].getNombre().equals(s)) {
                return i;
            }
        }
        return -1;
    }

    // Metodo que ordena por numero de puntos de menor a mayor
    public static void ordenarPorPuntosBurbuja(Nave[] flota) {
        for (int i = 0; i < flota.length - 1; i++) {
            for (int j = 0; j < flota.length - i - 1; j++) {
                if (flota[j].getPuntos() > flota[j + 1].getPuntos())
                    intercambiar(flota, j, j + 1);
            }
        }
    }

    // Metodo que ordena por nombre de A a Z
    public static void ordenarPorNombreBurbuja(Nave[] flota) {
        for (int i = 0; i < flota.length - 1; i++) {
            for (int j = 0; j < flota.length - i - 1; j++) {
                if (flota[j].getNombre().compareTo(flota[j + 1].getNombre()) > 0)
                    intercambiar(flota, j, j + 1);
            }
        }
    }

    // Metodo para buscar la primera nave con un nombre que se pidio por teclado
    public static int busquedaBinariaNombre(Nave[] flota, String s) {
        int baja = 0, alta = flota.length - 1, media;
        while (baja <= alta) {
            media = (baja + alta) / 2;
            String nombre = flota[media].getNombre();
            if (nombre.equals(s))
                return media;
            if (nombre.compareTo(s) < 0)
                baja = media + 1;
            else
                alta = media - 1;
        }
        return -1;
    }

    // Metodo que ordena por numero de puntos de menor a mayor
    public static void ordenarPorPuntosSeleccion(Nave[] flota) {
        for (int i = 0; i < flota.length - 1; i++) {
            int idx = i;
            for (int j = i + 1; j < flota.length; j++) {
                if (flota[j].getPuntos() < flota[idx].getPuntos())
                    idx = j;
            }
            intercambiar(flota, i, idx);
        }
    }

    // Metodo que ordena por nombre de A a Z
    public static void ordenarPorNombreSeleccion(Nave[] flota) {
        for (int i = 0; i < flota.length - 1; i++) {
            int idx = i;
            for (int j = i + 1; j < flota.length; j++) {
                if (flota[j].getNombre().compareTo(flota[idx].getNombre()) < 0)
                    idx = j;
            }
            intercambiar(flota, i, idx);
        }
    }

    // Metodo que muestra las naves ordenadas por numero de puntos de mayor a menor
    public static void ordenarPorPuntosInsercion(Nave[] flota) {
        for (int i = 1; i < flota.length; i++) {
            int j = i;
            while (j - 1 >= 0 && flota[j - 1].getPuntos() < flota[j].getPuntos()) {
                intercambiar(flota, j - 1, j);
                j--;
            }
        }
    }

    // Metodo que muestra las naves ordenadas por nombre de Z a A
    public static void ordenarPorNombreInsercion(Nave[] flota) {
        for (int i = 1; i < flota.length; i++) {
            int j = i;
            while (j - 1 >= 0 && flota[j - 1].getNombre().compareTo(flota[j].getNombre()) < 0) {
                intercambiar(flota, j - 1, j);
                j--;
            }
        }
    }

    public static void intercambiar(Nave[] flota, int a, int b) {
        Nave t = flota[a];
        flota[a] = flota[b];
        flota[b] = t;
    }
}