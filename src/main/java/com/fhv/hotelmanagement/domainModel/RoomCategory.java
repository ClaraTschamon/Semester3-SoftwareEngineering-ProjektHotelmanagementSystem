package com.fhv.hotelmanagement.domainModel;

import java.math.BigDecimal;

public class RoomCategory {
    private String name;
    private BigDecimal pricePerNight;

    public RoomCategory(String name, BigDecimal pricePerNight) {
        this.name = name;
        this.pricePerNight = pricePerNight;
    }
}
