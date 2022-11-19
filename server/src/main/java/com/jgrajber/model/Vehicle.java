package com.jgrajber.model;

import java.sql.Timestamp;

public record Vehicle(long id, String brand, String model, Timestamp insertTime) {
}
