package fase03.lab22;

import java.util.Scanner;

public class Videojuego {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String respuesta = "S";
        while (respuesta.equalsIgnoreCase("S")) {
            new Mapa();
            System.out.print("Desea jugar de nuevo? (S/N): ");
            respuesta = sc.nextLine();
        }
    }
}
