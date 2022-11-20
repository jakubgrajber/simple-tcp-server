package com.jgrajber.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public record InsuranceOffer(long id, String insurer, BigDecimal price, Timestamp insertTime) implements Serializable {

    @Override
    public String toString() {
        return "Offer ID: " + id + " | " + insurer + " | PRICE: " + price;
    }
}
