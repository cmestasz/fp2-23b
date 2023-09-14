// Laboratorio Nro 1 - Actividad 1
// Autor: Christian Mestas
// Colaboró: Marco Aedo, version del ahorcado al 70%

import java.util.Scanner;

public class Ahorcado {
    public static void main(String[] args) {
        String ahor1 = "  +---+  \n" +
                       "  |   |  \n" +
                       "      |  \n" +
                       "      |  \n" +
                       "      |  \n" +
                       "      |  \n" +
                       "========= ";
        String ahor2 = "  +---+  \n" +
                       "  |   |  \n" +
                       "  O   |  \n" +
                       "      |  \n" +
                       "      |  \n" +
                       "      |  \n" +
                       "=========";
        String ahor3 = "  +---+  \n" +
                       "  |   |  \n" +
                       "  O   |  \n" +
                       "  |   |  \n" +
                       "      |  \n" +
                       "      |  \n" +
                       "=========";
        String ahor4 = "  +---+  \n" +
                       "  |   |  \n" +
                       "  O   |  \n" +
                       " /|   |  \n" +
                       "      |  \n" +
                       "      |  \n" +
                       "=========";
        String ahor5 = "  +---+  \n" +
                       "  |   |  \n" +
                       "  O   |  \n" +
                       " /|\\ |  \n" +
                       "      |  \n" +
                       "      |  \n" +
                       "=========";
        String ahor6 = "  +---+  \n" +
                       "  |   |  \n" +
                       "  O   |  \n" +
                       " /|\\ |  \n" +
                       " /    |  \n" +
                       "      |  \n" +
                       "=========";
        String ahor7 = "  +---+  \n" +
                       "  |   |  \n" +
                       "  O   |  \n" +
                       " /|\\ |  \n" +
                       " / \\ |  \n" +
                       "      |  \n" +
                       "=========";
        
        String[] figuras = {ahor1, ahor2, ahor3, ahor4, ahor5, ahor6, ahor7};
        int contador = 1;
        String letra;
        String[] palabras = {"programacion", "java", "indentacion", "clases", "objetos", "desarrollador", "pruebas"};
        
        String palSecreta = getPalabraSecreta(palabras);
        System.out.println(figuras[0]);
        mostrarBlancos(palSecreta);
        System.out.println("\n");

        while (contador <= 6) {
            letra = ingreseLetra();
            if (letraEnPalabraSecreta(letra, palSecreta))
                mostrarBlancosActualizados(letra);
            else
                System.out.println(figuras[contador]);
            contador = contador + 1;
        }

        System.out.println("\n");
    }
}
