package com.jgrajber.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/***
 * Simple TCP server
 *
 * The server creates a separate thread (Connection) for each new client.
 *
 */

public class Server implements Runnable{

    private int port;
    private boolean runFlag;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (var server = new ServerSocket(port)){
            System.out.println("Server is listening on port " + port);

            while (runFlag) {
                Socket client = server.accept();
                System.out.println("New client connected " + client.getInetAddress());

                new Connection(client).start();
            }
        } catch (IOException exception) {
            System.err.println("Server: ");
            exception.printStackTrace();
        }
    }

    public void start() {
        runFlag = true;
        new Thread(this).start();
    }

    public static void main(String[] args) throws IOException {
        var properties = new Properties();
        int port;

        try(var in = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(in);
            port = Integer.parseInt(properties.getProperty("server.port"));
        }

        new Server(port).start();

    }
}
