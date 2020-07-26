package com.oio.origin;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * oio实现的socket
 */
public class Server {
    public void server(int port) throws Exception{
        final ServerSocket socket = new ServerSocket(port);
        while (true) {

            try {
                Socket accept = socket.accept();
                System.out.println("Accepted from " + accept);
                new Thread(() ->{
                    OutputStream outputStream;
                    try {
                        outputStream = accept.getOutputStream();
                        outputStream.write(("Hello, ur ip is " + accept.getInetAddress().getHostName()).getBytes(Charset.forName("UTF-8")));
                        outputStream.flush();
                        accept.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            accept.close();
                        } catch (Exception e) {
                        }
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Server().server(9000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
