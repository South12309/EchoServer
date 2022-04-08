package com.geekbrains;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public abstract class Echo {
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {

    }

    protected void start() {
        openConnect();
        Thread receiveThread = new Thread(() -> receiveMessage());
        Thread sendThread = new Thread(() -> sendMessage());
        receiveThread.start();
        sendThread.start();

        try {
            receiveThread.join();
            sendThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void receiveMessage() {
        String message;
        while (true) {
            try {
                message = in.readUTF();
                if ("/end".equalsIgnoreCase(message))
                    break;
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void sendMessage() {
        String message;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                message = scanner.nextLine();
                out.writeUTF(message);
                if ("/end".equalsIgnoreCase(message))
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    protected void makeStream(Socket socket) throws IOException {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }
    protected abstract void openConnect();


}
