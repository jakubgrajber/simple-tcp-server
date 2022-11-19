package com.jgrajber.service.vehicle;

import com.jgrajber.model.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getVehiclesByUserId(long userId);
}
