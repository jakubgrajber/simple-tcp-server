package com.jgrajber.server;

import java.io.IOException;
import java.net.Socket;



public class Connection extends Thread{

    private Socket client;


    public Connection(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
