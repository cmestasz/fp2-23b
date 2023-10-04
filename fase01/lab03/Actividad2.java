// Laboratorio Nro 3 - Actividad 2
// Autor: Christian Mestas

package fase01.lab03;

import java.util.Scanner;

public class Actividad2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Soldado[] soldados = new Soldado[5];
        for (int i = 0; i < soldados.length; i++) {
            String nombre = sc.nextLine();
            int vida = sc.nextInt();
            sc.nextLine();
            soldados[i] = new Soldado(nombre, vida);
        }
        for (int i = 0; i < soldados.length; i++) {
            System.out.println("Soldado " + (i + 1) + ":");
            System.out.println(soldados[i]);
        }
    }
}
