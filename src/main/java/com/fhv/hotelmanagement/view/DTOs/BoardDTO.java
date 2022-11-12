package com.fhv.hotelmanagement.view.DTOs;

import java.math.BigDecimal;

public class BoardDTO {
    private String name;
    private BigDecimal pricePerNight;

    public BoardDTO(){}

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
