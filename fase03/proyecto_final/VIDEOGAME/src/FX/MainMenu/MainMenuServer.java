package FX.MainMenu;

import java.io.*;

public class MainMenuServer extends Thread {
    private File matchFile = new File("match.dat");
    private long lastModified;
    private int totalConnections;

    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream("server.dat"));
            out.writeInt(totalConnections);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            lastModified = matchFile.lastModified();
            DataInputStream in = new DataInputStream(new FileInputStream("server.dat"));
            in.mark(0);
            while (true) {
                in.reset();
                totalConnections = in.readInt();

                for (int id = 0; id < totalConnections; id++)
                    respond(id);

                sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void respond(int id) {
        if (matchFile.lastModified() != lastModified) {

            lastModified = matchFile.lastModified();
        }
    }

    public static void main(String[] args) {
        new MainMenuServer().start();
    }
}
