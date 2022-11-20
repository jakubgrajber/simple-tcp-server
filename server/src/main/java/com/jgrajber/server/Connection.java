package com.jgrajber.server;

import com.jgrajber.config.AppConfig;
import com.jgrajber.model.InsuranceOffer;
import com.jgrajber.model.Payload;
import com.jgrajber.model.Vehicle;
import com.jgrajber.service.offer.InsuranceOfferService;
import com.jgrajber.service.user.UserService;
import com.jgrajber.service.vehicle.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Connection extends Thread {

    private Socket client;

    public Connection(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        var userService = context.getBean(UserService.class);
        var vehicleService = context.getBean(VehicleService.class);
        var offerService = context.getBean(InsuranceOfferService.class);
        Payload payload = null;


        try (var out = new PrintWriter(client.getOutputStream(), true);
             var in = new BufferedReader(new InputStreamReader(client.getInputStream()))) {

            String login = in.readLine();
            String password = in.readLine();

            if (userService.login(login, password)) {
                out.println("logged-in");
                long userId = userService.getIdByLogin(login);
                List<Vehicle> vehiclesByUserId = vehicleService.getVehiclesByUserId(userId);

                Map<Vehicle, List<InsuranceOffer>> map = new HashMap<>();
                vehiclesByUserId.forEach(vehicle ->
                    map.put(vehicle, offerService.getOffersByVehicleId(vehicle.id()))
                );
                payload = new Payload(map);

                try (var objOut = new ObjectOutputStream(client.getOutputStream())) {

                    objOut.writeObject(payload);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                out.println("Wrong credentials");
            }
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
