package com.java;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client
{
    public static void main(String[] args)
    {
        Socket socket;
        try {
            socket = new Socket("localhost", 2009);

            InputStream inputStream = socket.getInputStream();
            byte[] b = new byte[4096];
            inputStream.read(b);
            System.out.println(b);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
