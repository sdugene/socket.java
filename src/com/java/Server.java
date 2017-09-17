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
        private Socket socket;
        private int clientCount = 1;

        public AcceptClient(ServerSocket s) {
            this.serverSocket = s;
        }

        @Override
        @SuppressWarnings("InfiniteLoopStatement")
        public void run() {
            try {
                while(true){
                    socket = serverSocket.accept();
                    System.out.println("Client "+clientCount+" is connected");
                    clientCount++;

                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write("Hello".getBytes());

                    socket.close();

                    if (clientCount > 10) {
                        break;
                    }
                }

            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}



