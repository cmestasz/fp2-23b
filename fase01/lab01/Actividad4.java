// Laboratorio Nro 1 - Actividad 4
// Autor: Christian Mestas

package fase01.lab01;

import java.util.Scanner;

public class Actividad4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] nombresSoldados = new String[5];
        int[] vidasSoldados = new int[5];
        for (int i = 0; i < nombresSoldados.length; i++) {
            nombresSoldados[i] = sc.nextLine();
            vidasSoldados[i] = sc.nextInt();
            sc.nextLine();
        }
        for (int i = 0; i < nombresSoldados.length; i++) {
            System.out.println("Soldado " + (i + 1) + ": " + nombresSoldados[i] + ". Vida: " + vidasSoldados[i]);
        }
    }
}