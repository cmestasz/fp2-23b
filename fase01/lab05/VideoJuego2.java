public class VideoJuego2 {
    public static void main(String[] args) {
        Soldado[][] soldados = new Soldado[10][10];
        inicializarSoldados(soldados);
        imprimirTablero(soldados);
        System.out.println("Soldado con mayor vida: " + soldadoMayorVida(soldados));
        System.out.println("Promedio de puntos de vida: " + promedioPuntosVida(soldados));
        System.out.println("Nivel de vida del ejercito: " + sumaPuntosVida(soldados));
        Soldado[][] soldados2 = new Soldado[10][10];
        Soldado[][] soldados3 = new Soldado[10][10];
        System.arraycopy(soldados, 0, soldados2, 0, soldados.length);
        System.arraycopy(soldados, 0, soldados3, 0, soldados.length);
        ordenarSoldadosBurbuja(soldados2);
        imprimirSoldados(soldados2);
        ordenarSoldadosSeleccion(soldados3);
        imprimirSoldados(soldados3);
    }

    private static void inicializarSoldados(Soldado[][] soldados) {
    }

    private static void imprimirTablero(Soldado[][] soldados) {
    }

    private static Soldado soldadoMayorVida(Soldado[][] soldados) {
        
    }

    private static Soldado promedioPuntosVida(Soldado[][] soldados) {
        
    }

    private static Soldado sumaPuntosVida(Soldado[][] soldados) {
        
    }

    private static void ordenarSoldadosBurbuja(Soldado[][] soldados2) {
    }

    private static void ordenarSoldadosSeleccion(Soldado[][] soldados3) {
    }

    private static void imprimirSoldados(Soldado[][] soldados3) {
    }

    public static int charToInt(char c) {
        return c - 'A' + 1;
    }
}
