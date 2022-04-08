package com.geekbrains;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    DataInputStream in;
    DataOutputStream out;

    public static void main(String[] args) {
        String message;
        EchoServer server = new EchoServer();
        server.startServer();
        while (true) {
            try {
                message = server.receiveMessage();
                if ("/end".equalsIgnoreCase(message))
                    break;
                System.out.println(message);
                server.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String receiveMessage() throws IOException {
        return in.readUTF();
    }

    private void sendMessage(String message) throws IOException {
        out.writeUTF(message);
    }

    private void startServer() {
        Socket socket;

        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен. Ожидание подключения....");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");

            in = new DataInputStream(socket.getInputStream());

            out = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
