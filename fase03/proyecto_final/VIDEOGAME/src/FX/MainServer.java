package FX;

import Utils.ServerConnection;
import Utils.DBConnector;
import Utils.Operation;
import Utils.Utils;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

import FX.MainGame.Board;

public class MainServer extends Thread implements Operation {
    private ArrayList<ServerConnection> connectionsList = new ArrayList<ServerConnection>();
    private HashMap<Integer, Long> lastModifiedMap = new HashMap<Integer, Long>(); // really bad name, use it to store
                                                                                   // when ANY file is modified or received
    private int totalConnections;
    private boolean active = true;

    private HashMap<String, int[]> matches = new HashMap<String, int[]>();

    public void run() {
        new DBConnector();
        File directory = new File("connections");
        try {
            while (active) {
                int newTotalConnections = 0;
                for (File file : directory.listFiles())
                    if (file.getName().endsWith(".dat"))
                        newTotalConnections++;
                System.out.println(newTotalConnections);

                if (totalConnections != newTotalConnections) {
                    for (int id = totalConnections; id < newTotalConnections; id++) {
                        // Se crea una nueva conexión y se agrega a la lista.
                        ServerConnection connection = new ServerConnection(totalConnections);
                        System.out.println("connecting: " + connection);
                        connectionsList.add(connection);
                        lastModifiedMap.put(totalConnections, (long) 0);
                    }
                    totalConnections = newTotalConnections;

                }

                for (int id = 0; id < totalConnections; id++)
                    respond(id);

                sleep(1000);
            }

            for (ServerConnection connection : connectionsList) {
                connection.deleteDataConnection();
                connection = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void end() {
        active = false;
    }

    private void respond(int id) {
        ServerConnection connection = connectionsList.get(id);
        System.out.println("responding: " + connection);
        long lastModified = connection.getLastModified();

        // Se verifica si la conexión ha sido modificada desde la última respuesta.
        if (lastModifiedMap.get(id) != lastModified) {
            try {
                DataInputStream in = connection.getDataInputStream();
                int operation = in.readInt();
                String code = Utils.readString(in);

                switch (operation) {
                    case OPERATION_CREATE:
                        // Se almacena la información de la conexión que ha creado un nuevo código.
                        matches.put(code, new int[] { connection.getId(), -1 });
                        lastModifiedMap.put(id, lastModified);
                        break;

                    case OPERATION_JOIN:
                        // Se intenta unir dos conexiones con el código.
                        int[] ids = matches.get(code);
                        DataOutputStream toGuest = new DataOutputStream(
                                new FileOutputStream(connection.getConnectionDataFile()));
                        toGuest.writeInt(RESPONSE_GUEST);
                        if (ids != null && ids[1] == -1) {
                            ServerConnection host = connectionsList.get(ids[0]);
                            ids[1] = connection.getId();
                            toGuest.writeChars(host.getName());
                            toGuest.writeChar(0);
                            toGuest.writeChars(host.getKingdom());
                            toGuest.writeChar(0);

                            DataOutputStream toHost = new DataOutputStream(
                                    new FileOutputStream(host.getConnectionDataFile()));
                            toHost.writeInt(RESPONSE_HOST);
                            toHost.writeChars(connection.getName());
                            toHost.writeChar(0);
                            toHost.writeChars(connection.getKingdom());
                            toHost.writeChar(0);

                            toHost.close();
                            lastModifiedMap.put(host.getId(), host.getLastModified());
                        }
                        toGuest.writeChar(0);
                        toGuest.close();
                        lastModifiedMap.put(id, connection.getLastModified());
                        break;

                    case OPERATION_START:
                        System.out.println("found file");
                        // Se inicia la conexión del invitado con el código.
                        ObjectInputStream inObj = connection.getObjectInputStream();
                        Board board = (Board) inObj.readObject();
                        inObj.close();
                        connection.deleteObjConnection();
                        System.out.println(board);

                        int idGuest = matches.get(code)[1];
                        ServerConnection guest = connectionsList.get(idGuest);

                        DataOutputStream out = new DataOutputStream(
                                new FileOutputStream(guest.getConnectionDataFile()));
                        ObjectOutputStream outObj = new ObjectOutputStream(
                                new FileOutputStream(guest.getConnectionObjFile()));
                        out.writeInt(RESPONSE_START);
                        outObj.writeObject(board);
                        System.out.println("sent board back");
                        out.close();
                        outObj.close();

                        lastModifiedMap.put(idGuest, guest.getLastModified());
                        lastModifiedMap.put(id, connection.getLastModified());
                        break;
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MainServer server = new MainServer();
        server.start();
        JOptionPane.showMessageDialog(null, "El servidor esta ejecutandose correctamente\nPresione ok para detenerlo");
        server.end();
    }
}
