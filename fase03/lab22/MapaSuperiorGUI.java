package fase03.lab22;

import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapaSuperiorGUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int ROWS = 10;
    private static final int COLS = 10;

    private MapaSuperior mapaSuperior;
    private String reino1;
    private String reino2;
    private String terreno;
    private JButton[][] batallasMapa = new JButton[ROWS][COLS];
    private HashMap<String, String> batallas;

    public MapaSuperiorGUI(String name, String reino1, String reino2, String terreno, HashMap<String, String> batallas,
            MapaSuperior mapaSuperior) {
        super(name);
        this.reino1 = reino1;
        this.reino2 = reino2;
        this.terreno = terreno;
        this.batallas = batallas;
        this.mapaSuperior = mapaSuperior;
        setSize(WIDTH, HEIGHT);
        setLayout(new GridLayout(ROWS, COLS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void mostrarVentana() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                String llave = generarLlave(i, j);
                if (batallas.containsKey(llave)) {
                    batallasMapa[i][j] = new JButton(batallas.get(llave));
                    batallasMapa[i][j].addActionListener(new BotonListener(llave));
                } else {
                    batallasMapa[i][j] = new JButton("");
                }
                batallasMapa[i][j].setOpaque(true);
                batallasMapa[i][j].setBackground(((i + j) % 2 == 0) ? Color.white : Color.lightGray);
                batallasMapa[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                add(batallasMapa[i][j]);
            }
        }

        setVisible(true);
    }

    private String generarLlave(int fila, int columna) {
        return fila + "," + columna;
    }

    private class BotonListener implements ActionListener {
        private String llave;

        public BotonListener(String llave) {
            this.llave = llave;
        }

        public void actionPerformed(ActionEvent e) {
            new Mapa(reino1, reino2, terreno);
            batallas.remove(llave);
            JButton boton = (JButton) e.getSource();
            boton.setText("");
            boton.removeActionListener(this);

            if (batallas.size() == 0) {
                mapaSuperior.terminarGuerra();
            }
        }
    }
}