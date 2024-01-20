package Utils;

import java.io.*;
import java.sql.*;

import javax.swing.JOptionPane;

public class DBConnector {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = readLogin();
            if (connection == null) {
                String user = "", password = "";
                while (connection == null) {
                    user = JOptionPane.showInputDialog(null, "Ingrese usuario", "Primera conexión",
                            JOptionPane.QUESTION_MESSAGE);
                    password = JOptionPane.showInputDialog(null, "Ingrese clave", "Primera conexión",
                            JOptionPane.QUESTION_MESSAGE);
                    // usuario = "query";
                    // clave = "123456789";
                    connection = login(user, password);
                }
                writeLogin(user, password);
            }

            // connection.prepareStatement("CREATE DATABASE test").execute();
            // ResultSet resultados = conexion.prepareStatement("SELECT * FROM alumnos WHERE
            // nombre='Juan'")
            // .executeQuery();

            System.out.println("Conexion exitosa");
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection readLogin() throws Exception {
        File dblogin = new File("data/dblogin.dat");
        if (dblogin.exists()) {
            DataInputStream in = new DataInputStream(new FileInputStream(dblogin));
            String user = Utils.readString(in);
            String password = Utils.readString(in);
            return login(user, password);
        }
        return null;
    }

    private static Connection login(String user, String password) {
        String url = "jdbc:mysql://localhost:3306/";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Usuario o clave incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private static void writeLogin(String user, String password) throws Exception {
        DataOutputStream out = new DataOutputStream(new FileOutputStream("data/dblogin.dat"));
        out.writeChars(user);
        out.writeChar(0);
        out.writeChars(password);
        out.writeChar(0);
        out.close();
    }
}
