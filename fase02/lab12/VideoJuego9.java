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
        int semilla = new Random().nextInt(1000000);
        random = new Random(semilla);
        System.out.println(semilla);
        HashMap<String, Soldado> mapaSoldados = new HashMap<String, Soldado>();
        ArrayList<Soldado> listaSoldados1 = new ArrayList<Soldado>();
        ArrayList<Soldado> listaSoldados2 = new ArrayList<Soldado>();
        inicializarSoldados(mapaSoldados, listaSoldados1, 1);
        inicializarSoldados(mapaSoldados, listaSoldados2, 2);
        int opcion;
        do {
            imprimirTablero(mapaSoldados);
            System.out.println("\nEjercito 1:");
            imprimirSoldados(listaSoldados1);
            System.out.println("\nEjercito 2:");
            imprimirSoldados(listaSoldados2);
            System.out.print("\nEjercito a gestionar (1/2): ");
            int equipo = sc.nextInt();
            ArrayList<Soldado> seleccionado = equipo == 1 ? listaSoldados1 : listaSoldados2;
            System.out.println(
                    "1. Crear soldado\n2. Eliminar soldado\n3. Clonar soldado\n4. Modificar soldado\n5. Comparar soldados\n6. Intercambiar soldados\n7. Ver soldado\n8. Ver ejercito\n9. Sumar niveles\n10. Jugar\n11. Volver");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch (opcion) {
                case 1:
                    crearSoldado(mapaSoldados, seleccionado, equipo);
                    break;
                case 2:
                    eliminarSoldado(mapaSoldados, seleccionado, equipo);
                    break;
                case 3:
                    clonarSoldado(mapaSoldados, seleccionado);
                    break;
                case 4:
                    modificarSoldado(mapaSoldados, seleccionado);
                    break;
                case 5:
                    compararSoldados(mapaSoldados, seleccionado);
                    break;
                case 6:
                    intercambiarSoldados(mapaSoldados, seleccionado);
                    break;
                case 7:
                    verSoldado(seleccionado);
                    break;
                case 8:
                    imprimirSoldados(seleccionado);
                    break;
                case 9:
                    sumarNiveles(seleccionado);
                    break;
                case 10:
                    realizarCombates(mapaSoldados, listaSoldados1, listaSoldados2);
                    break;
                case 11:
                    return;
            }
            System.out.println();
        } while (opcion < 10);
    }

    private static void crearSoldado(HashMap<String, Soldado> mapaSoldados, ArrayList<Soldado> listaSoldados,
            int equipo) {
        if (listaSoldados.size() < 10) {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Vida: ");
            int vida = sc.nextInt();
            System.out.print("Ataque: ");
            int ataque = sc.nextInt();
            System.out.print("Defensa: ");
            int defensa = sc.nextInt();
            sc.nextLine();
            int filaI, columnaI;
            do {
                System.out.print("Fila: ");
                String fila = sc.nextLine();
                filaI = Integer.parseInt(fila) - 1;
                System.out.print("Columna: ");
                String columna = sc.nextLine();
                columnaI = charToInt(columna.charAt(0)) - 1;
            } while (!coordenadaValida(mapaSoldados, filaI, columnaI)
                    || seleccionOcupada(mapaSoldados, filaI, columnaI));
            Soldado soldado = new Soldado(nombre, equipo, ataque, defensa, vida);
            mapaSoldados.put(generarLlave(filaI, columnaI), soldado);
            listaSoldados.add(soldado);
        } else {
            System.out.println("Ejercito lleno!");
        }
    }

    private static void eliminarSoldado(HashMap<String, Soldado> mapaSoldados, ArrayList<Soldado> listaSoldados,
            int equipo) {
        if (listaSoldados.size() > 1) {
            int filaI, columnaI;
            do {
                System.out.print("Fila: ");
                String fila = sc.nextLine();
                filaI = Integer.parseInt(fila) - 1;
                System.out.print("Columna: ");
                String columna = sc.nextLine();
                columnaI = charToInt(columna.charAt(0)) - 1;
            } while (!coordenadaValida(mapaSoldados, filaI, columnaI) ||
                    !seleccionValida(mapaSoldados, filaI, columnaI, equipo));
            String llave = generarLlave(filaI, columnaI);
            Soldado soldado = mapaSoldados.get(llave);
            listaSoldados.remove(soldado);
            mapaSoldados.remove(llave);
        } else {
            System.out.println("No se puede tener un ejercito vacio!");
        }
    }

    private static void clonarSoldado(HashMap<String, Soldado> mapaSoldados, ArrayList<Soldado> listaSoldados) {
    }

    private static void modificarSoldado(HashMap<String, Soldado> mapaSoldados, ArrayList<Soldado> listaSoldados) {
    }

    private static void compararSoldados(HashMap<String, Soldado> mapaSoldados, ArrayList<Soldado> listaSoldados) {
    }

    private static void intercambiarSoldados(HashMap<String, Soldado> mapaSoldados, ArrayList<Soldado> listaSoldados) {
    }

    private static void verSoldado(ArrayList<Soldado> listaSoldados) {
    }

    private static void sumarNiveles(ArrayList<Soldado> listaSoldados) {
    }

    private static void juegoRapido() {
        int semilla = new Random().nextInt(1000000);
        random = new Random(semilla);
        System.out.println(semilla);
        HashMap<String, Soldado> mapaSoldados = new HashMap<String, Soldado>();
        ArrayList<Soldado> listaSoldados1 = new ArrayList<Soldado>();
        ArrayList<Soldado> listaSoldados2 = new ArrayList<Soldado>();
        System.out.println("Inicia la batalla!");
        inicializarSoldados(mapaSoldados, listaSoldados1, 1);
        inicializarSoldados(mapaSoldados, listaSoldados2, 2);
        imprimirTablero(mapaSoldados);
        System.out.printf("Soldado con mayor vida del ejercito 1: %s%n", soldadoMayorVida(listaSoldados1));
        System.out.printf("Promedio de puntos de vida del ejercito 1: %f%n", promedioPuntosVida(listaSoldados1));
        System.out.println("\nSoldados por orden de creacion:");
        imprimirSoldados(listaSoldados1);
        System.out.println("\nSoldados ordenados por burbuja");
        ordenarSoldadosBurbuja(listaSoldados1);
        imprimirSoldados(listaSoldados1);
        System.out.println();
        System.out.printf("Soldado con mayor vida del ejercito 2: %s%n", soldadoMayorVida(listaSoldados2));
        System.out.printf("Promedio de puntos de vida del ejercito 2: %f%n", promedioPuntosVida(listaSoldados2));
        System.out.println("\nSoldados por orden de creacion:");
        imprimirSoldados(listaSoldados2);
        System.out.println("\nSoldados ordenados por seleccion");
        ordenarSoldadosSeleccion(listaSoldados2);
        imprimirSoldados(listaSoldados2);
        System.out.println();
        realizarCombates(mapaSoldados, listaSoldados1, listaSoldados2);
    }

    public static void inicializarSoldados(HashMap<String, Soldado> mapaSoldados, ArrayList<Soldado> listaSoldados,
            int equipo) {
        int cantidad = random.nextInt(10) + 1;
        for (int i = 0; i < cantidad; i++) {
            String nombre = "Soldado" + i + "X" + equipo;
            int vida = random.nextInt(5) + 1;
            int ataque = random.nextInt(5) + 1;
            int defensa = random.nextInt(5) + 1;
            int fila, columna;
            do {
                fila = random.nextInt(ESCALA);
                columna = random.nextInt(ESCALA);
            } while (mapaSoldados.containsKey(generarLlave(fila, columna)));
            Soldado soldado = new Soldado(nombre, equipo, ataque, defensa, vida);
            mapaSoldados.put(generarLlave(fila, columna), soldado);
            listaSoldados.add(soldado);
        }
    }

    public static void imprimirTablero(HashMap<String, Soldado> mapaSoldados) {
        System.out.print(generarEncabezado(mapaSoldados));
        String separacion = generarSeparacion(mapaSoldados);
        for (int i = 0; i < ESCALA; i++) {
            System.out.print(separacion);
            System.out.print(generarFila(mapaSoldados, i));
        }
        System.out.print(separacion);
    }

    public static String generarEncabezado(HashMap<String, Soldado> mapaSoldados) {
        String encabezado = "\t";
        for (int i = 0; i < ESCALA; i++)
            encabezado += ("    " + intToChar(i + 1) + "   ");
        encabezado += " \n";
        return encabezado;
    }

    public static String generarSeparacion(HashMap<String, Soldado> mapaSoldados) {
        String fila = "\t";
        for (int i = 0; i < ESCALA; i++)
            fila += "--------";
        fila += "-\n";
        return fila;
    }

    public static String generarFila(HashMap<String, Soldado> mapaSoldados, int f) {
        String fila = (f + 1) + "\t";
        for (int i = 0; i < ESCALA; i++) {
            fila += "| ";
            Soldado soldado = mapaSoldados.get(generarLlave(f, i));
            if (soldado != null) {
                String nombre = soldado.getNombre();
                if (nombre.startsWith("Soldado"))
                    fila += "So" + nombre.charAt(nombre.length() - 3);
                else
                    fila += nombre.substring(0, 3);
                fila += "~" + soldado.getEquipo();
            } else {
                fila += "     ";
            }
            fila += " ";
        }
        fila += "|\n";
        return fila;
    }

    public static Soldado soldadoMayorVida(ArrayList<Soldado> soldados) {
        int idx = 0;
        for (int i = 1; i < soldados.size(); i++) {
            if (soldados.get(i).getVidaActual() > soldados.get(idx).getVidaActual())
                idx = i;
        }
        return soldados.get(idx);
    }

    public static double promedioPuntosVida(ArrayList<Soldado> soldados) {
        int suma = sumaPuntosVida(soldados);
        return 1.0 * suma / soldados.size();
    }

    public static void imprimirSoldados(ArrayList<Soldado> soldados) {
        for (Soldado soldado : soldados)
            System.out.println(soldado);
    }

    public static void ordenarSoldadosBurbuja(ArrayList<Soldado> soldados) {
        for (int i = 0; i < soldados.size() - 1; i++) {
            for (int j = 0; j < soldados.size() - i - 1; j++) {
                int vida1 = soldados.get(j).getVidaActual();
                int vida2 = soldados.get(j + 1).getVidaActual();
                if (vida1 < vida2)
                    intercambiar(soldados, j, j + 1);
            }
        }
    }

    public static void ordenarSoldadosSeleccion(ArrayList<Soldado> soldados) {
        for (int i = 0; i < soldados.size() - 1; i++) {
            int idx = i;
            for (int j = i + 1; j < soldados.size(); j++) {
                int vida1 = soldados.get(j).getVidaActual();
                int vida2 = soldados.get(idx).getVidaActual();
                if (vida1 > vida2)
                    idx = j;
            }
            intercambiar(soldados, i, idx);
        }
    }

    public static void realizarCombates(HashMap<String, Soldado> mapaSoldados, ArrayList<Soldado> listaSoldados1,
            ArrayList<Soldado> listaSoldados2) {
        int turno = 1;
        int ganador = -1;
        while (ganador == -1) {
            imprimirTablero(mapaSoldados);
            if (!realizarTurno(mapaSoldados, listaSoldados1, listaSoldados2, turno % 2))
                return;
            ganador = verificarGanador(listaSoldados1, listaSoldados2);
            turno++;
        }
        System.out.printf("\nHa ganado el ejercito %d!%n", ganador);
        System.out.println("Tablero final:");
        imprimirTablero(mapaSoldados);
    }

    public static boolean realizarTurno(HashMap<String, Soldado> mapaSoldados, ArrayList<Soldado> listaSoldados1,
            ArrayList<Soldado> listaSoldados2, int jugador) {
        int filaI, columnaI, filaF, columnaF;
        System.out.printf("Ejercito %d%n", jugador);
        do {
            System.out.print("Ingrese fila (vacio para cancelar el juego actual): ");
            String fila = sc.nextLine();
            if (fila.equals(""))
                return false;
            filaI = Integer.parseInt(fila) - 1;
            System.out.print("Ingrese columna: ");
            String columna = sc.nextLine();
            columnaI = charToInt(columna.charAt(0)) - 1;
        } while (!coordenadaValida(mapaSoldados, filaI, columnaI)
                || !seleccionValida(mapaSoldados, filaI, columnaI, jugador));
        do {
            System.out.print("Ingrese direccion (Punto cardinal): ");
            String direccion = sc.nextLine();
            filaF = filaI;
            columnaF = columnaI;
            if (direccion.contains("N"))
                filaF--;
            else if (direccion.contains("S"))
                filaF++;
            if (direccion.contains("E"))
                columnaF++;
            else if (direccion.contains("O"))
                columnaF--;
        } while (!coordenadaValida(mapaSoldados, filaF, columnaF)
                || !destinoValido(mapaSoldados, filaF, columnaF, jugador));
        String llaveI = generarLlave(filaI, columnaI);
        String llaveF = generarLlave(filaF, columnaF);
        if (mapaSoldados.get(llaveF) != null && mapaSoldados.get(llaveF).getEquipo() != jugador) {
            realizarPelea(mapaSoldados, listaSoldados1, listaSoldados2, llaveI, llaveF, jugador);
        } else {
            mapaSoldados.put(llaveF, mapaSoldados.get(llaveI));
            mapaSoldados.remove(llaveI);
        }
        return true;
    }

    public static void realizarPelea(HashMap<String, Soldado> mapaSoldados, ArrayList<Soldado> listaSoldados1,
            ArrayList<Soldado> listaSoldados2, String llaveI, String llaveF, int jugador) {
        Soldado soldadoAtaca = mapaSoldados.get(llaveI);
        Soldado soldadoDefiende = mapaSoldados.get(llaveF);
        System.out.printf("Ocurre una batalla entre %s y %s!%n", soldadoAtaca.getNombre(), soldadoDefiende.getNombre());
        int vidaAtaca = soldadoAtaca.getVidaActual();
        int vidaDefiende = soldadoDefiende.getVidaActual();
        double probabilidad = 1.0 * vidaAtaca / (vidaAtaca + vidaDefiende);
        System.out.printf("%s tiene %.3f%% probabilidades de vencer!%n", soldadoAtaca.getNombre(), probabilidad * 100);
        System.out.printf("%s tiene %.3f%% probabilidades de vencer!%n", soldadoDefiende.getNombre(),
                (1 - probabilidad) * 100);
        if (probabilidad >= Math.random()) {
            System.out.printf("Gana el soldado %s!%n", soldadoAtaca.getNombre());
            soldadoAtaca.mejorar();
            mapaSoldados.remove(llaveI);
            mapaSoldados.put(llaveF, soldadoAtaca);
            if (jugador == 1)
                listaSoldados2.remove(soldadoDefiende);
            else if (jugador == 2)
                listaSoldados1.remove(soldadoDefiende);
        } else {
            System.out.printf("Gana el soldado %s!%n", soldadoDefiende.getNombre());
            soldadoDefiende.mejorar();
            mapaSoldados.remove(llaveI);
            if (jugador == 1)
                listaSoldados1.remove(soldadoAtaca);
            else if (jugador == 2)
                listaSoldados2.remove(soldadoDefiende);
        }
    }

    public static boolean coordenadaValida(HashMap<String, Soldado> mapaSoldados, int fila, int columna) {
        if (fila >= 0 && fila < ESCALA && columna >= 0 && columna < ESCALA)
            return true;
        System.out.println("Coordenada no valida.");
        return false;
    }

    public static boolean seleccionValida(HashMap<String, Soldado> mapaSoldados, int fila, int columna, int equipo) {
        String llave = generarLlave(fila, columna);
        if (mapaSoldados.get(llave) != null && mapaSoldados.get(llave).getEquipo() == equipo)
            return true;
        System.out.println("Seleccion no valida.");
        return false;
    }

    public static boolean seleccionLlena(HashMap<String, Soldado> mapaSoldados, int fila, int columna) {
        String llave = generarLlave(fila, columna);
        if (mapaSoldados.get(llave) != null)
            return true;
        System.out.println("Seleccion vacia.");
        return false;
    }

    public static boolean seleccionOcupada(HashMap<String, Soldado> mapaSoldados, int fila, int columna) {
        String llave = generarLlave(fila, columna);
        if (mapaSoldados.get(llave) == null)
            return false;
        System.out.println("Seleccion ya ocupada.");
        return true;
    }

    public static boolean destinoValido(HashMap<String, Soldado> mapaSoldados, int fila, int columna, int equipo) {
        String llave = generarLlave(fila, columna);
        if (mapaSoldados.get(llave) == null || mapaSoldados.get(llave).getEquipo() != equipo)
            return true;
        System.out.println("Destino no valido.");
        return false;
    }

    public static int verificarGanador(ArrayList<Soldado> listaSoldados1, ArrayList<Soldado> listaSoldados2) {
        if (listaSoldados2.isEmpty())
            return 1;
        else if (listaSoldados1.isEmpty())
            return 2;
        return -1;
    }

    public static int sumaPuntosVida(ArrayList<Soldado> soldados) {
        int suma = 0;
        for (int i = 0; i < soldados.size(); i++)
            suma += soldados.get(i).getVidaActual();
        return suma;
    }

    public static void intercambiar(ArrayList<Soldado> soldados, int i, int j) {
        Soldado t = soldados.get(i);
        soldados.set(i, soldados.get(j));
        soldados.set(j, t);
    }

    public static String generarLlave(int fila, int columna) {
        return fila + "," + columna;
    }

    public static char intToChar(int n) {
        return (char) (n + 'A' - 1);
    }

    public static int charToInt(char c) {
        return (int) (c - 'A' + 1);
    }
}