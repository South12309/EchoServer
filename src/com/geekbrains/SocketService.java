package com.geekbrains;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public abstract class SocketService {
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;



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
        try {
            while (true) {
                message = in.readUTF();
                System.out.println(message);
                if ("/end".equalsIgnoreCase(message))
                    System.exit(0);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendMessage() {
        String message;
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                message = scanner.nextLine();
                out.writeUTF(message);
                if ("/end".equalsIgnoreCase(message))
                    System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void makeStream(Socket socket) throws IOException {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    protected void setSocket(Socket socket) throws IOException {
        this.socket = socket;
        makeStream(socket);
    }

    protected abstract void openConnect();
}
