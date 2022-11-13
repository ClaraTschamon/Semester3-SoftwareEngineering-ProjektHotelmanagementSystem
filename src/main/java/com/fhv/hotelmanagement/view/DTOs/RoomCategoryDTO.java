package com.fhv.hotelmanagement.view.DTOs;

import java.math.BigDecimal;

public class RoomCategoryDTO {
    private String name;
    private BigDecimal pricePerNight;

    public RoomCategoryDTO() {}

    public RoomCategoryDTO(String name, BigDecimal pricePerNight) {
        this.name = name;
        this.pricePerNight = pricePerNight;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setName(String name){
        this.name = name;
    }
}
