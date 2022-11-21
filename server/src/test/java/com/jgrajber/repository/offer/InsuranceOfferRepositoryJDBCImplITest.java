package com.jgrajber.repository.offer;

import com.jgrajber.model.InsuranceOffer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.jgrajber.db.PGDataSourceFactory.*;
import static org.junit.jupiter.api.Assertions.*;

/***
 * Start docker container with PostrgreSQL and init script
 * before running following tests.
 */

class InsuranceOfferRepositoryJDBCImplITest {

    private InsuranceOfferRepositoryJDBCImpl offerRepository = new InsuranceOfferRepositoryJDBCImpl(createPSQLDataSource());

    @Test
    void givenVehicleId_whenGetOffersByVehicleId_thenReturnListOfOffers() {

        // given
        long vehicleId = 1;

        // when
        List<InsuranceOffer> offersByVehicleId = offerRepository.getOffersByVehicleId(vehicleId);

        // then
        assertEquals(3, offersByVehicleId.size());
    }

    @Test
    void givenMissingVehicleId_whenGetOffersByVehicleId_thenReturnEmptyListOfOffers() {

        // given
        long vehicleId = 325;

        // when
        List<InsuranceOffer> offersByVehicleId = offerRepository.getOffersByVehicleId(vehicleId);

        // then
        assertEquals(0, offersByVehicleId.size());
    }
}