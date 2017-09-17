package com.java;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args)
    {
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(2009);
            Thread t = new Thread(new AcceptClient(serverSocket));
            t.start();
            System.out.println("Server Ready");

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static class AcceptClient implements Runnable
    {
        private ServerSocket serverSocket;
        private int clientCount = 1;

        AcceptClient(ServerSocket s) {
            this.serverSocket = s;
        }

        @Override
        public void run() {
            Socket socket = null;
            try {
                while(true){
                    socket = serverSocket.accept();
                    System.out.println("Client "+clientCount+" is connected");


                    OutputStream outputStream = socket.getOutputStream();
                    //outputStream.write("Hello".getBytes());

                    outputStream.write(clientCount);

                    InputStream inputStream = socket.getInputStream();
                    byte[] b = new byte[4096];
                    int size = inputStream.read(b);
                    String content = new String(b, 0, size);
                    System.out.println(content);

                    clientCount++;
                    if (clientCount > 30) {
                        break;
                    }
                }

            } catch (IOException e){
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
}



