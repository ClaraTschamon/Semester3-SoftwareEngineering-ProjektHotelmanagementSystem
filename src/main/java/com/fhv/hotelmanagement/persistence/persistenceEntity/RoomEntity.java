//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

@Entity
@Table (name = "room")
public class RoomEntity {

    @Id
    @Column (name = "room_number")
    private int number;

    @ManyToOne
    @JoinColumn (name = "room_category_name")
    private RoomCategoryEntity category;

    public RoomEntity(){};

    public RoomEntity(int number, RoomCategoryEntity category) {
        this.number = number;
        this.category = category;
    }


    public int getNumber() {
        return number;
    }

    public RoomCategoryEntity getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "number=" + number +
                ", category=" + category +
                '}';
    }
}
