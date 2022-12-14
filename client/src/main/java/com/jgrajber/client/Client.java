package com.jgrajber.client;

import com.jgrajber.model.Payload;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket client;
    private PrintWriter out;
    private BufferedReader in;

    public static void main(String[] args) {

        var client = new Client();

        try {
            client.connect("localhost", 1234);
            Scanner input = new Scanner(System.in);

            System.out.print("Login: ");
            if (input.hasNextLine()) {
                client.sendMessage(input.nextLine());
            }

            System.out.print("Password: ");
            if (input.hasNextLine()) {
                client.sendMessage(input.nextLine());
            }

            String receivedMessage = client.receiveMessage();

            if (receivedMessage.equals("logged-in")){
                try (var inputObject = new ObjectInputStream(client.client.getInputStream())) {
                    Payload offers= (Payload) inputObject.readObject();
                    offers.print();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(receivedMessage);
            }

            client.disconnect();
        } catch (IOException e) {
            System.err.println("Couldn't connect.");
            e.printStackTrace();
        }
    }

    public void connect(String ip, int port) throws IOException {
        client = new Socket(ip, port);
        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public void sendMessage(String message) throws IOException {
        out.println(message);
    }


    public String receiveMessage() throws IOException {
        return in.readLine();
    }

    public void disconnect() throws IOException {
        in.close();
        out.close();
        client.close();
    }
}
