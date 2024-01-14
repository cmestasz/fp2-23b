package fase03.lab22;

import java.util.HashMap;
import javax.swing.*;
import java.awt.*;

public class MapaGUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 1000;
    private static final int ROWS = 10;
    private static final int COLS = 10;
    private JPanel map = new JPanel(new GridLayout(10, 10));
    private JPanel descripcion = new JPanel(new GridLayout(1, 3));
    private JLabel[][] soldadosMapa = new JLabel[ROWS][COLS];

    public MapaGUI(String name) {
        super(name);
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void mostrarVentana(HashMap<String, Soldado> soldados, String desc1, String desc2, String desc3) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                String llave = generarLlave(i, j);
                if (soldados.containsKey(llave))
                    soldadosMapa[i][j] = soldados.get(llave);
                else
                    soldadosMapa[i][j] = new JLabel("", SwingConstants.CENTER);
                soldadosMapa[i][j].setOpaque(true);
                soldadosMapa[i][j].setBackground(((i + j) % 2 == 0) ? Color.lightGray : Color.white);
                soldadosMapa[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                map.add(soldadosMapa[i][j]);
            }
        }

        descripcion.add(new JTextArea(desc1));
        descripcion.add(new JTextArea(desc2));
        descripcion.add(new JTextArea(desc3));

        map.setAlignmentY(CENTER_ALIGNMENT);
        add(BorderLayout.CENTER, map);
        add(BorderLayout.SOUTH, descripcion);

        setVisible(true);
    }

    private String generarLlave(int fila, int columna) {
        return fila + "," + columna;
    }
}
