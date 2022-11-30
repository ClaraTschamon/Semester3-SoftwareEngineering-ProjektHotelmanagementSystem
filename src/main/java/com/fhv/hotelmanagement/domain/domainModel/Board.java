//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.domainModel;

import java.math.BigDecimal;

public class Board {
    private String name;
    private BigDecimal pricePerNight;

    public Board(String name, BigDecimal pricePerNight) {
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
