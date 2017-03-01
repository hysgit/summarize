package com.wolsx.summarize.netsocket.serc;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by hy on 2/9/17.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("10.248.248.27", 50000);
        InputStream inputStream = socket.getInputStream();
        byte[] bi = new byte[2];
        while (true) {
            int read = inputStream.read(bi);
            if (read != -1) {
                String s = new String(bi);
                System.out.println(s);
            }
        }
    }
}
