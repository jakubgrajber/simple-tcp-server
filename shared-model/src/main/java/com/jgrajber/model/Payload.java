package com.jgrajber.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Payload implements Serializable {

    private final Map<Vehicle, List<InsuranceOffer>> vehicleInsuranceOffers;

    public Payload(Map<Vehicle, List<InsuranceOffer>> vehicleInsuranceOffers) {
        this.vehicleInsuranceOffers = vehicleInsuranceOffers;
    }

    public void print() {
        System.out.println("Insurance offers for your vehicles:");
        System.out.println();
        vehicleInsuranceOffers.forEach((vehicle, offers) -> {
            System.out.println(vehicle + ": ");
            offers.forEach(System.out::println);
            System.out.println();
        });
    }
}
