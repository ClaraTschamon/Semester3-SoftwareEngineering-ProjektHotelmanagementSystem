package com.fhv.hotelmanagement.DTOs;

import java.math.BigDecimal;

public class RoomCategoryDTO {
    private String name;
    private BigDecimal pricePerNight;

    public RoomCategoryDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
