package com.jgrajber.service.offer;

import com.jgrajber.model.InsuranceOffer;
import com.jgrajber.repository.offer.InsuranceOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceOfferServiceImpl implements InsuranceOfferService {

    private final InsuranceOfferRepository insuranceOfferRepository;

    @Autowired
    public InsuranceOfferServiceImpl(InsuranceOfferRepository insuranceOfferRepository) {
        this.insuranceOfferRepository = insuranceOfferRepository;
    }

    @Override
    public List<InsuranceOffer> getOffersByVehicleId(long vehicleId) {
        return insuranceOfferRepository.getOffersByVehicleId(vehicleId);
    }
}
