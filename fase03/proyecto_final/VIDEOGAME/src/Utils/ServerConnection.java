package Utils;

import java.io.*;

public class ServerConnection  {
    private int id;
    private String name;
    private File connectionFile;

    public ServerConnection(int id) throws IOException {
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

    public DataInputStream getDataInputStream() throws IOException {
        return new DataInputStream(new FileInputStream(connectionFile));
    }

    public void deleteConnection() {
        connectionFile.delete();
    }

    public String toString() {
        return id + ": " + name;
    }

}
