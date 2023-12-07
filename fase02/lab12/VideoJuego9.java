package fase02.lab12;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class VideoJuego9 {
    private static final int ESCALA = 10;
    private static Random random;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 3;
        do {
            System.out.println("1. Juego Rapido\n2. Juego Personalizado\n3. Salir");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();
            System.out.println();
            String continuar = "N";
            do {
                switch (opcion) {
                    case 1:
                        juegoRapido();
                        break;
                    case 2:
                        juegoPersonalizado();
                        break;
                }
                if (opcion != 3) {
                    System.out.print("\nVolver a jugar? (S/N): ");
                    continuar = sc.nextLine();
                }
            } while (continuar.equalsIgnoreCase("S"));
        } while (opcion != 3);
    }

    private static void juegoPersonalizado() {
    }

    private static void juegoRapido() {
    }
}