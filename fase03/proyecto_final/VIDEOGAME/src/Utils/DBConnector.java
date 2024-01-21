package Utils;

import java.io.*;
import java.sql.*;

import javax.swing.JOptionPane;

public class DBConnector {
    private String user;
    private String password;
    private Connection connection;

    public static void main(String[] args) {
        new DBConnector();
    }

    public DBConnector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = readLogin();
            if (connection == null) {
                while (connection == null) {
                    user = JOptionPane.showInputDialog(null, "Ingrese usuario", "Primera conexión",
                            JOptionPane.QUESTION_MESSAGE);
                    password = JOptionPane.showInputDialog(null, "Ingrese clave", "Primera conexión",
                            JOptionPane.QUESTION_MESSAGE);
                    // usuario = "query";
                    // clave = "123456789";
                    connection = login("");
                }
                createDatabase();
                writeLogin();
            }

            // connection.prepareStatement("CREATE DATABASE test").execute();
            // ResultSet resultados = conexion.prepareStatement("SELECT * FROM alumnos WHERE
            // nombre='Juan'")
            // .executeQuery();

            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection readLogin() throws IOException {
        File dblogin = new File("data/dblogin.dat");
        if (dblogin.exists()) {
            DataInputStream in = new DataInputStream(new FileInputStream(dblogin));
            user = Utils.readString(in);
            password = Utils.readString(in);
            return login("videogame");
        }
        return null;
    }

    private Connection login(String database) {
        String url = "jdbc:mysql://localhost:3306/" + database;
        try {
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Usuario o clave incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void writeLogin() throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream("data/dblogin.dat"));
        out.writeChars(user);
        out.writeChar(0);
        out.writeChars(password);
        out.writeChar(0);
        out.close();
    }

    private void createDatabase() throws SQLException {
        connection.prepareStatement("CREATE DATABASE videogame;\n").execute();
        connection = login("videogame");
        connection.prepareStatement(
                "CREATE TABLE players (id int NOT NULL AUTO_INCREMENT, name varchar(30) NOT NULL, password varchar(30) NOT NULL, PRIMARY KEY (id))")
                .execute();
        connection.prepareStatement(
                "CREATE TABLE matches (id int NOT NULL AUTO_INCREMENT, winner_id int NOT NULL, loser_id int NOT NULL, PRIMARY KEY (id), INDEX winner_id (winner_id), INDEX loser_id (loser_id))")
                .execute();
        connection.prepareStatement(
                "ALTER TABLE matches ADD CONSTRAINT winner_id FOREIGN KEY (winner_id) REFERENCES players(id) ON DELETE RESTRICT ON UPDATE RESTRICT")
                .execute();
        connection.prepareStatement(
                "ALTER TABLE matches ADD CONSTRAINT loser_id FOREIGN KEY (loser_id) REFERENCES players(id) ON DELETE RESTRICT ON UPDATE RESTRICT")
                .execute();
    }

    public int loginPlayer(String name, String password) {
        try {
            String query = String.format("SELECT id FROM players WHERE name = '%s' AND password = '%s'", name,
                    password);
            ResultSet results = connection.prepareStatement(query).executeQuery();
            if (results.next())
                return results.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int[] getWinsLoses(int id) {
        try {
            int[] totals = new int[2];
            String query = String.format("SELECT COUNT(*) from matches WHERE winner_id = '$d'", id);
            ResultSet results = connection.prepareStatement(query).executeQuery();
            if (results.next())
                totals[0] = results.getInt(1);

            query = String.format("SELECT COUNT(*) from matches WHERE loser_id = '%d'", id);
            results = connection.prepareStatement(query).executeQuery();
            if (results.next())
                totals[1] = results.getInt(1);

            return totals;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void registerPlayer(String name, String password) {
        try {
            String query = String.format("INSERT INTO players (name, password) VALUES ('%s', '%s')", name, password);
            connection.prepareStatement(query).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createMatch(int winner_id, int loser_id) {
        try {
            String query = String.format("INSERT INTO matches (winner_id, loser_id) VALUES ('%d', '%d')", winner_id,
                    loser_id);
            connection.prepareStatement(query).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
