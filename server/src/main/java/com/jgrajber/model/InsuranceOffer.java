package com.jgrajber.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record InsuranceOffer(long id, String insurer, BigDecimal price, Timestamp insertTime) {
}
