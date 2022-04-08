package com.geekbrains;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoServer extends SocketService {
    private final int SERVER_PORT = 8189;

    public static void main(String[] args) {
        EchoServer server = new EchoServer();
        server.start();
    }
    @Override
    protected void openConnect() {

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Сервер запущен. Ожидание подключения....");
            setSocket(serverSocket.accept());
            System.out.println("Клиент подключился");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
