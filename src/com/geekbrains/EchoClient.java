package com.geekbrains;

import java.io.IOException;
import java.net.Socket;

public class EchoClient extends Echo {
    public static void main(String[] args) {
        EchoClient client = new EchoClient();
        client.start();
    }

    @Override
    protected void openConnect() {

        try (Socket socket = new Socket("localhost", 8189)) {
            System.out.println("Подключено к серверу");
            makeStream(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
