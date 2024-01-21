package Utils;

import java.io.*;

public class Utils {
    public static String readString(DataInputStream in) throws IOException {
        char c;
        String str = "";
        while ((c = in.readChar()) != 0)
            str += c;
        return str;
    }
}
