package com.fhv.hotelmanagement.DTOs;

import java.math.BigDecimal;

public class PackageDTO {
    private String name;
    private BigDecimal pricePerNight;

    public PackageDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}
