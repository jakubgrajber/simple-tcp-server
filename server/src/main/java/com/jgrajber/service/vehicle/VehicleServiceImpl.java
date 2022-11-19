package com.jgrajber.service.vehicle;

import com.jgrajber.db.PGDataSourceFactory;
import com.jgrajber.model.Vehicle;
import com.jgrajber.repository.vehicle.VehicleRepository;
import com.jgrajber.repository.vehicle.VehicleRepositoryJDBCImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> getVehiclesByUserId(long userId) {
        return vehicleRepository.getVehiclesByUserId(userId);
    }

    public static VehicleService createVehicleService() {
        return new VehicleServiceImpl(new VehicleRepositoryJDBCImpl(PGDataSourceFactory.createPSQLDataSource()));
    }
}
