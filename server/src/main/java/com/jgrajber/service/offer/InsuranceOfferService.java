package com.jgrajber.service.offer;

import com.jgrajber.model.InsuranceOffer;

import java.util.List;

public interface InsuranceOfferService {

    List<InsuranceOffer> getOffersByVehicleId(long vehicleId);
}
