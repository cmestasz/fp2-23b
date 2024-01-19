package FX.MainMenu;

import Utils.Utils;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class MainMenuServer extends Thread implements Operation {
    private ArrayList<Connection> connectionsList = new ArrayList<Connection>();
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
                    Connection connection = new Connection(totalConnections);
                    System.out.println("connecting: " + connection);
                    connectionsList.add(connection);
                    lastModifiedMap.put(totalConnections, (long) 0);
                    totalConnections = newTotalConnections;
                }

                for (int id = 0; id < totalConnections; id++)
                    respond(id);

                sleep(1000);
            }

            for (Connection connection : connectionsList) {
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
        Connection connection = connectionsList.get(id);
        System.out.println("responding: " + connection);
        long lastModified = connection.getLastModified();
        if (lastModifiedMap.get(id) != lastModified) {
            try {
                DataInputStream in = connection.getDataInputStream();
                Utils.readString(in);
                String code = Utils.readString(in);
                switch (in.readInt()) {
                    case OPERATION_CREATE:
                        matches.put(code, new int[] { connection.getId(), -1 });
                        lastModifiedMap.put(id, lastModified);
                        break;
                    case OPERATION_JOIN:
                        int[] ids = matches.get(code);
                        DataOutputStream toGuest = new DataOutputStream(
                                new FileOutputStream(connection.getConnectionFile()));
                        toGuest.writeInt(RESPONSE_GUEST);
                        if (ids != null && ids[1] == -1) {
                            Connection host = connectionsList.get(ids[0]);
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
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MainMenuServer server = new MainMenuServer();
        server.start();
        JOptionPane.showMessageDialog(null, "El servidor esta ejecutandose correctamente\nPresione ok para detenerlo");
        server.end();
    }
}
