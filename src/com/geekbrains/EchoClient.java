package com.geekbrains;

import java.io.IOException;
import java.net.Socket;

public class EchoClient extends SocketService {
    private final String SERVER_ADDRESS = "localhost";
    private final int SERVER_PORT = 8189;

    public static void main(String[] args) {
        EchoClient client = new EchoClient();
        client.start();
    }

    @Override
    protected void openConnect() {

        try {
            setSocket(new Socket(SERVER_ADDRESS, SERVER_PORT));
            System.out.println("Подключено к серверу");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
