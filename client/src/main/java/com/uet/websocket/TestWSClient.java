package com.uet.websocket;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class TestWSClient {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Starting session...");
        MySocketClient socketClient = new MySocketClient("1", "Phong", "localhost");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Session ready.");
        while (true) {
            System.out.println("Enter your message:");
            String message = scanner.nextLine();
            if (message.equals("exit")) {
                System.out.println("Session ended.");
                break;
            }
            socketClient.sendMessage(message);
        }
    }
}
