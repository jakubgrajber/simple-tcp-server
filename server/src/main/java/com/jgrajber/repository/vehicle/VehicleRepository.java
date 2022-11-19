package com.jgrajber.repository.vehicle;

import com.jgrajber.model.Vehicle;

import java.util.List;

public interface VehicleRepository {

    List<Vehicle> getVehiclesByUserId(long userId);
}
