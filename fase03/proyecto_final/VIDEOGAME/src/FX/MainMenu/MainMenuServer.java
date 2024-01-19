package FX.MainMenu;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MainMenuServer extends Thread {
    private ArrayList<Long> lastModifiedList = new ArrayList<Long>();
    private ArrayList<File> connectionsList = new ArrayList<File>();
    private int totalConnections;
    private boolean active = true;

    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream("server.dat"));
            out.writeInt(totalConnections);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while (active) {
                DataInputStream in = new DataInputStream(new FileInputStream("server.dat"));

                int newTotalConnections = in.read();
                if (totalConnections != newTotalConnections) {
                    for (int id = totalConnections; id < newTotalConnections; id++) {
                        File file = new File(id + ".dat");
                        connectionsList.add(file);
                        lastModifiedList.add(file.lastModified());
                    }
                    totalConnections = newTotalConnections;
                }

                for (int id = 0; id < totalConnections; id++)
                    respond(id);

                in.close();
                sleep(1000);
            }

            for (File connection : connectionsList) {
                connection.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void end() {
        active = false;
    }

    private void respond(int id) {
        long lastModified = connectionsList.get(id).lastModified();
        if (lastModified != lastModifiedList.get(id)) {

            lastModifiedList.set(id, lastModified);
        }
    }

    public static void main(String[] args) {
        MainMenuServer server = new MainMenuServer();
        server.start();
        JOptionPane.showMessageDialog(null, "El servidor esta ejecutandose correctamente\nPresione ok para detenerlo");
        server.end();
    }
}
