package com.jgrajber.service.offer;

import com.jgrajber.repository.offer.InsuranceOfferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InsuranceOfferServiceImplTest {

    @Mock
    InsuranceOfferRepository insuranceOfferRepository;

    @InjectMocks
    InsuranceOfferServiceImpl insuranceOfferService;

    @Test
    void givenVehicleId_whenGetOffersByVehicleId_thenReturnListOfOffers() {

        // given
        long vehicleId = 1;

        // when
        insuranceOfferService.getOffersByVehicleId(vehicleId);

        // then
        Mockito.verify(insuranceOfferRepository, Mockito.times(1)).getOffersByVehicleId(vehicleId);
    }
}