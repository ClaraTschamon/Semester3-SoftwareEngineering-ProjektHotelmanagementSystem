//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.domainModel;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomCategory roomCategory = (RoomCategory) o;
        return getName().equals(roomCategory.getName()) &&
                getPricePerNight().equals(roomCategory.getPricePerNight());
    }
}
