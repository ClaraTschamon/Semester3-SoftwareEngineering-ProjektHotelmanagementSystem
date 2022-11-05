package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

@Entity
@Table (name = "room")
public class RoomEntity {
    private int number;
    private RoomCategoryEntity category;

    public RoomEntity(){};

    public RoomEntity(int number, RoomCategoryEntity category) {
        this.number = number;
        this.category = category;
    }

    @Id
    @Column (name = "room_number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @ManyToOne
    @JoinColumn (name = "room_category_name")
    public RoomCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(RoomCategoryEntity category) {
        this.category = category;
    }

}
