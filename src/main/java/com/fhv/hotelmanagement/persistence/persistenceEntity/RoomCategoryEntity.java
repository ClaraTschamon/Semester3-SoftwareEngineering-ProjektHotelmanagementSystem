//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table (name = "room_category")
public class RoomCategoryEntity {

    @Id
    @Column(name = "room_category_name")
    private String name;

    @Column(name = "price_per_night")
    private BigDecimal pricePerNight;

    public RoomCategoryEntity(){};

    public RoomCategoryEntity(String name, BigDecimal pricePerNight) {
        this.name = name;
        this.pricePerNight = pricePerNight;
    }


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

    @Override
    public String toString() {
        return "RoomCategoryEntity{" +
                "name='" + name + '\'' +
                ", pricePerNight=" + pricePerNight +
                '}';
    }
}
