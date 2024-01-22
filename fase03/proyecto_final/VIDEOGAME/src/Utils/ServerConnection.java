package Utils;

import java.io.*;

public class ServerConnection {
    private int id;
    private String name;
    private String kingdom;
    private File connectionDataFile;
    private File connectionObjFile;

    public ServerConnection(int id) throws IOException {
        this.id = id;
        this.connectionDataFile = new File("connections/" + id + ".dat");
        this.connectionObjFile = new File("connections/" + id + ".obj");
        DataInputStream in = getDataInputStream();
        in.readInt();
        Utils.readString(in);
        this.name = Utils.readString(in);
        this.kingdom = Utils.readString(in);
        in.close();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKingdom() {
        return kingdom;
    }

    public File getConnectionDataFile() {
        return connectionDataFile;
    }

    public File getConnectionObjFile() {
        return connectionObjFile;
    }

    public long getLastModified() {
        return connectionDataFile.lastModified();
    }

    public DataInputStream getDataInputStream() throws IOException {
        return new DataInputStream(new FileInputStream(connectionDataFile));
    }

    public ObjectInputStream getObjectInputStream() throws IOException {
        return new ObjectInputStream(new FileInputStream(connectionObjFile));
    }

    public void deleteDataConnection() {
        connectionDataFile.delete();
    }

    public void deleteObjConnection() {
        connectionObjFile.delete();
    }

    public String toString() {
        return id + ": " + name;
    }

}
