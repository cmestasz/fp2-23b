// Laboratorio Nro 2 - Actividad 1
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

        boolean[] letrasEncontradas = new boolean[palSecreta.length()];
        while (contador <= 6) {
            letra = ingreseLetra();
            if (letraEnPalabraSecreta(letra, palSecreta))
                mostrarBlancosActualizados(letra, palSecreta, letrasEncontradas);
            else
                System.out.println(figuras[contador]);
            contador = contador + 1;
        }
        //TODO: indicar si gano, perdio y cantidad de turnos
        
        System.out.println("\n");
    }

    public static String getPalabraSecreta(String[] lasPalabras) {
        String palSecreta;
        int ind;
        int indiceMayor = lasPalabras.length - 1;
        int indiceMenor = 0;
        ind = (int) ((Math.random() * (indiceMayor - indiceMenor + 1) + indiceMenor));
        return lasPalabras[ind];
    }

    public static void mostrarBlancos(String palabra) {
        for (int i = 0; i < palabra.length(); i++)
            System.out.print("_ ");
    }

    public static String ingreseLetra() {
        String laLetra;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese letra: "); 
        laLetra = sc.next();
        while (!esLetraValida(laLetra)) {
            System.out.println("Ingrese letra: ");
            laLetra = sc.next();
        }
        return laLetra;
    }

    public static boolean letraEnPalabraSecreta(String letra, String palSecreta) {
        char letraIngresada = letra.charAt(0);
        for (int i = 0; i < palSecreta.length(); i++) {
            if (palSecreta.charAt(i) == letraIngresada)
                return true;
        }
        return false;
    }

    public static void mostrarBlancosActualizados(String letra, String palSecreta, boolean[] letrasEncontradas) {
        char letraIngresada = letra.charAt(0);
        for (int i = 0; i < palSecreta.length(); i++) {
            char letraActual = palSecreta.charAt(i);
            if (letraActual == letraIngresada || letrasEncontradas[i]) {
                System.out.print(letraActual + " ");
                letrasEncontradas[i] = true;
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println("\n");
    }

    public static boolean esLetraValida(String letra) {
        if (letra.length() != 1)
            return false;
        char caracter = letra.charAt(0);
        return Character.isLetter(caracter);
    }
}
