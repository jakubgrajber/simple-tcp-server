package com.jgrajber.repository.offer;

import com.jgrajber.model.InsuranceOffer;

import java.util.List;

public interface InsuranceOfferRepository {

    List<InsuranceOffer> getOffersByVehicleId(long vehicleId);
}
