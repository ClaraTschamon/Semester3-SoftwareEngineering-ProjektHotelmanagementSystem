package com.fhv.hotelmanagement.domain.domainModel;

import java.math.BigDecimal;

public class RoomCategory {
    private String name;
    private BigDecimal pricePerNight;

    public RoomCategory(String name, BigDecimal pricePerNight) {
        this.name = name;
        this.pricePerNight = pricePerNight;
    }

    public String getName(){
        return name;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }
}
