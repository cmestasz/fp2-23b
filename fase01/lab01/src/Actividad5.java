// Laboratorio Nro 1 - Actividad 5
// Autor: Christian Mestas

import java.util.Random;

public class Actividad5 {
    public static void main(String[] args) {
        String[] ejercito1 = inicializarEjercito();
        String[] ejercito2 = inicializarEjercito();
        mostrarEjercito(ejercito1, 1);
        mostrarEjercito(ejercito2, 2);
        mostrarGanador(ejercito1, ejercito2);
    }

    public static String[] inicializarEjercito() {
        Random random = new Random();
        String[] ejercito = new String[random.nextInt(5) + 1];
        for (int i = 0; i < ejercito.length; i++) {
            ejercito[i] = "Soldado" + i;
        }
        return ejercito;
    }

    public static void mostrarEjercito(String[] ejercito, int numero) {
        System.out.println("Ejercito " + numero + ":");
        for (int i = 0; i < ejercito.length; i++) {
            System.out.println("Soldado " + (i + 1) + ": " + ejercito[i]);
        }
    }

    public static void mostrarGanador(String[] ejercito1, String[] ejercito2) {
        if (ejercito1.length > ejercito2.length)
            System.out.println("Gana el ejercito 1!");
        else if (ejercito1.length < ejercito2.length)
            System.out.println("Gana el ejercito 2!");
        else
            System.out.println("Hubo un empate!");
    }
}