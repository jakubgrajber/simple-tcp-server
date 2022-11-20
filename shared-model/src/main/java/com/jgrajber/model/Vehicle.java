package com.jgrajber.model;

import java.io.Serializable;
import java.sql.Timestamp;

public record Vehicle(long id, String brand, String model, Timestamp insertTime) implements Serializable {

    @Override
    public String toString() {
        return "ID: " + id + " | " + brand + " " + model;
    }
}
