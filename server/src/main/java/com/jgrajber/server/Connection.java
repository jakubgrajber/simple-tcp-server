package com.jgrajber.server;

import com.jgrajber.config.AppConfig;
import com.jgrajber.service.user.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;


public class Connection extends Thread {

    private Socket client;


    public Connection(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        var userService = context.getBean(UserService.class);

        try (var out = new PrintWriter(client.getOutputStream(), true);
             var in = new BufferedReader(new InputStreamReader(client.getInputStream()))) {

            String login = in.readLine();
            String password = in.readLine();

            if (userService.login(login, password)) {
                out.println("You are logged in");
            } else {
                out.println("Wrong credentials");
            }

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
