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
}
