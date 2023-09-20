// Laboratorio Nro 3 - Actividad 3
// Autor: Christian Mestas

import java.util.Random;

public class Actividad3 {
    public static void main(String[] args) {
        Soldado[] ejercito1 = inicializarEjercito();
        Soldado[] ejercito2 = inicializarEjercito();
        mostrarEjercito(ejercito1, 1);
        mostrarEjercito(ejercito2, 2);
        mostrarGanador(ejercito1, ejercito2);
    }

    public static Soldado[] inicializarEjercito() {
        Random random = new Random();
        Soldado[] ejercito = new Soldado[random.nextInt(5) + 1];
        for (int i = 0; i < ejercito.length; i++)
            ejercito[i] = new Soldado("Soldado" + i, 0);
        return ejercito;
    }

    public static void mostrarEjercito(Soldado[] ejercito, int numero) {
        System.out.println("Ejercito " + numero + ":");
        for (int i = 0; i < ejercito.length; i++)
            System.out.println("Soldado " + (i + 1) + ": " + ejercito[i].getNombre());
    }

    public static void mostrarGanador(Soldado[] ejercito1, Soldado[] ejercito2) {
        if (ejercito1.length > ejercito2.length)
            System.out.println("Gana el ejercito 1!");
        else if (ejercito1.length < ejercito2.length)
            System.out.println("Gana el ejercito 2!");
        else
            System.out.println("Hubo un empate!");
    }
}
