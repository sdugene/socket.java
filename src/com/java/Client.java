package com.java;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client
{
    public static void main(String[] args)
    {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 2009);

            InputStream inputStream = socket.getInputStream();
            /*byte[] b = new byte[4096];
            int size = inputStream.read(b);
            String content = new String(b, 0, size);
            System.out.println(content);*/

            int id = inputStream.read();
            System.out.println("I'm client "+id);

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(("> Hello, I'm client "+id).getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
