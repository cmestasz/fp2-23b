// Laboratorio Nro 1 - Actividad 2
// Autor: Christian Mestas

import java.util.Scanner;

public class Actividades {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombreSoldado1 = sc.nextLine();
        int vidaSoldado1 = sc.nextInt();
        sc.nextLine();
        String nombreSoldado2 = sc.nextLine();
        int vidaSoldado2 = sc.nextInt();
        sc.nextLine();
        String nombreSoldado3 = sc.nextLine();
        int vidaSoldado3 = sc.nextInt();
        sc.nextLine();
        String nombreSoldado4 = sc.nextLine();
        int vidaSoldado4 = sc.nextInt();
        sc.nextLine();
        String nombreSoldado5 = sc.nextLine();
        int vidaSoldado5 = sc.nextInt();
        sc.nextLine();
        System.out.println("Soldado 1: " + nombreSoldado1 + ". Vida: " + vidaSoldado1);
        System.out.println("Soldado 2: " + nombreSoldado2 + ". Vida: " + vidaSoldado2);
        System.out.println("Soldado 3: " + nombreSoldado3 + ". Vida: " + vidaSoldado3);
        System.out.println("Soldado 4: " + nombreSoldado4 + ". Vida: " + vidaSoldado4);
        System.out.println("Soldado 5: " + nombreSoldado5 + ". Vida: " + vidaSoldado5);
    }
}
