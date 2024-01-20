package FX;

import Utils.ServerConnection;
import Utils.Operation;
import Utils.Utils;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class MainServer extends Thread implements Operation {
    private ArrayList<ServerConnection> connectionsList = new ArrayList<ServerConnection>();
    private HashMap<Integer, Long> lastModifiedMap = new HashMap<Integer, Long>();
    private int totalConnections;
    private boolean active = true;

    private HashMap<String, int[]> matches = new HashMap<String, int[]>();

    public void run() {
        File directory = new File("connections");
        try {
            while (active) {
                int newTotalConnections = directory.listFiles().length;
                System.out.println(newTotalConnections);
                
                if (totalConnections != newTotalConnections) {
                    // Se crea una nueva conexión y se agrega a la lista.
                    ServerConnection connection = new ServerConnection(totalConnections);
                    System.out.println("connecting: " + connection);
                    connectionsList.add(connection);
                    lastModifiedMap.put(totalConnections, (long) 0);
                    totalConnections = newTotalConnections;
                }

                for (int id = 0; id < totalConnections; id++)
                    respond(id);

                sleep(1000);
            }

            for (ServerConnection connection : connectionsList) {
                connection.deleteConnection();
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
                        // Se intenta unir dos conexiones basándose en un código.
                        int[] ids = matches.get(code);
                        DataOutputStream toGuest = new DataOutputStream(
                                new FileOutputStream(connection.getConnectionFile()));
                        toGuest.writeInt(RESPONSE_GUEST);
                        if (ids != null && ids[1] == -1) {
                            ServerConnection host = connectionsList.get(ids[0]);
                            ids[1] = connection.getId();
                            toGuest.writeChars(host.getName());

                            DataOutputStream toHost = new DataOutputStream(
                                    new FileOutputStream(host.getConnectionFile()));
                            toHost.writeInt(RESPONSE_HOST);
                            toHost.writeChars(connection.getName());
                            toHost.writeChar(0);

                            toHost.close();
                            lastModifiedMap.put(host.getId(), host.getLastModified());
                        }
                        toGuest.writeChar(0);
                        toGuest.close();
                        lastModifiedMap.put(id, connection.getLastModified());
                        break;

                    case OPERATION_START:
                        // Se inicia la conexión del invitado basándose en un código.
                        int idGuest = matches.get(code)[1];
                        ServerConnection guest = connectionsList.get(idGuest);
                        DataOutputStream out = new DataOutputStream(
                                new FileOutputStream(guest.getConnectionFile()));
                        out.writeInt(RESPONSE_START);
                        out.close();
                        lastModifiedMap.put(idGuest, guest.getLastModified());
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
