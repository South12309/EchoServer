package com.geekbrains;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer extends Echo{
    public static void main(String[] args) {
        EchoServer server = new EchoServer();
        server.start();
    }
    @Override
    protected void openConnect() {
        Socket socket;

        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен. Ожидание подключения....");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            makeStream(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
