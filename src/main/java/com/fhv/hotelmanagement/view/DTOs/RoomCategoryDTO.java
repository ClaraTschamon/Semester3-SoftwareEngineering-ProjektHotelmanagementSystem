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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomCategoryDTO roomCategoryDTO = (RoomCategoryDTO) o;
        return (roomCategoryDTO.getName().equals(name)) &&
                (roomCategoryDTO.getPricePerNight().equals(pricePerNight));
    }
}
