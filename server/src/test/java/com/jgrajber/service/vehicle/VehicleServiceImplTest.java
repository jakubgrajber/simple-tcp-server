package com.jgrajber.service.vehicle;

import com.jgrajber.repository.vehicle.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {

    @Mock
    VehicleRepository vehicleRepository;

    @InjectMocks
    VehicleServiceImpl vehicleService;

    @Test
    void givenUserId_whenGetVehiclesByUserId_thenReturnListOfVehicles() {

        // given
        long userId = 1;

        // when
        vehicleService.getVehiclesByUserId(userId);

        // then
        Mockito.verify(vehicleRepository, Mockito.times(1)).getVehiclesByUserId(userId);
    }
}