package com;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class Client {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1", 9000);
            InputStream inputStream = client.getInputStream();
            OutputStream outputStream = client.getOutputStream();
            outputStream.write("hello, i am willis".getBytes(Charset.forName("utf-8")));
            byte[] bytes = new byte[1024];
            while (true) {
                int r = inputStream.read(bytes, 0, bytes.length);
                if (r != -1) {
                    System.out.println("server response=" + new String(bytes, "utf-8"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
