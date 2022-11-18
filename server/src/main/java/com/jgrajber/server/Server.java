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

public class Server {

    public void start(int port) {

        try (var server = new ServerSocket(port)){
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket client = server.accept();
                System.out.println("New client connected " + client.getInetAddress());

                new Connection(client).start();
            }
        } catch (IOException exception) {
            System.err.println("Server: ");
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        var server = new Server();

        var properties = new Properties();

        try(var in = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(in);
            server.start(Integer.parseInt(properties.getProperty("server.port")));

        }
    }
}
