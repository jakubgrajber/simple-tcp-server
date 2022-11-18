package com.jgrajber.client;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket client;

    public void connect(String ip, int port) throws IOException {
        client = new Socket(ip, port);
    }

    public void disconnect() throws IOException {
        client.close();
    }

    public static void main(String[] args) {

        var client = new Client();
        try {
            client.connect("localhost", 1234);
            client.disconnect();
        } catch (IOException e) {
            System.err.println("Couldn't connect.");
            e.printStackTrace();
        }
    }
}
