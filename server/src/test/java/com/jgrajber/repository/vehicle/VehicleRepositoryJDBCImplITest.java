package com.jgrajber.repository.vehicle;

import com.jgrajber.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static com.jgrajber.db.PGDataSourceFactory.*;
import static org.junit.jupiter.api.Assertions.*;

class VehicleRepositoryJDBCImplITest {

    private final VehicleRepositoryJDBCImpl vehicleRepository = new VehicleRepositoryJDBCImpl(createPSQLDataSource());

    @ParameterizedTest
    @CsvSource({"1,2", "2,2", "3,3", "4,1"})
    void givenUserId_whenGetVehiclesByUserId_thenReturnListOfVehicles(String userId, String numberOfVehicles) {

        // when
        List<Vehicle> vehiclesByUserId = vehicleRepository.getVehiclesByUserId(Long.parseLong(userId));

        // then
        assertEquals(Integer.parseInt(numberOfVehicles), vehiclesByUserId.size());
    }

    @Test
    void givenMissingUserId_whenGetVehiclesByUserId_thenReturnEmptyList() {

        // given
        long userId = 100;

        // when
        List<Vehicle> vehiclesByUserId = vehicleRepository.getVehiclesByUserId(userId);

        // then
        assertTrue(vehiclesByUserId.isEmpty());
    }
}