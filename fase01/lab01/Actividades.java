// Laboratorio Nro 1 - Actividad 3
// Autor: Christian Mestas

import java.util.Scanner;

public class Actividades {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] nombresSoldados = new String[5];
        for (int i = 0; i < nombresSoldados.length; i++) {
            nombresSoldados[i] = sc.nextLine();
        }
        for (int i = 0; i < nombresSoldados.length; i++) {
            System.out.println("Soldado " + (i + 1) + ": " + nombresSoldados[i]);
        }
    }
}
