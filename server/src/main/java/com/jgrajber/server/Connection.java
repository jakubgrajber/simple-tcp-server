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
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/***
 * Connection represents every single client connected to the server.
 * It is used to process client request by server
 */

public class Connection extends Thread {

    private Socket client;

    private PrintWriter out;
    private BufferedReader in;

    private UserService userService;
    private VehicleService vehicleService;
    private InsuranceOfferService offerService;

    public Connection(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        try {
            onConnect();
            String login = in.readLine();
            String password = in.readLine();

            if (userService.login(login, password)) {

                out.println("logged-in");
                System.out.println("Client logged in successfully");

                Payload payload = generateResponse(login);

                try (var objOut = new ObjectOutputStream(client.getOutputStream())) {
                    objOut.writeObject(payload);
                }

            } else {
                System.out.println("Client provided wrong credentials");
                out.println("Wrong credentials");
            }
            onDisconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Payload generateResponse(String login) {
        return new Payload(getVehiclesInsuranceOffers(login));
    }

    private Map<Vehicle, List<InsuranceOffer>> getVehiclesInsuranceOffers(String login) {
        long id = userService.getIdByLogin(login);

        return vehicleService.getVehiclesByUserId(id).stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        vehicle -> offerService.getOffersByVehicleId(vehicle.id())));
    }

    private void onConnect() throws IOException {

        System.out.println("Client connected: " + client.getInetAddress());

        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        userService = context.getBean(UserService.class);
        vehicleService = context.getBean(VehicleService.class);
        offerService = context.getBean(InsuranceOfferService.class);
    }

    private void onDisconnect() throws IOException {
        System.out.println("Client disconnected: " + client.getInetAddress());
        in.close();
        out.close();
        client.close();
    }
}
