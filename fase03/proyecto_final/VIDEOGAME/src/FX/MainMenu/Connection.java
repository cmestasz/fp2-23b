package FX.MainMenu;

import java.io.*;
import Utils.Utils;

public class Connection  {
    private int id;
    private String name;
    private File connectionFile;

    public Connection(int id) throws Exception {
        this.id = id;
        this.connectionFile = new File("connections/" + id + ".dat");
        DataInputStream in = getDataInputStream();
        in.readInt();
        Utils.readString(in);
        this.name = Utils.readString(in);
        in.close();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public File getConnectionFile() {
        return connectionFile;
    }

    public long getLastModified() {
        return connectionFile.lastModified();
    }

    public DataInputStream getDataInputStream() throws Exception {
        return new DataInputStream(new FileInputStream(connectionFile));
    }

    public void deleteConnection() {
        connectionFile.delete();
    }

    public String toString() {
        return id + ": " + name;
    }

}
