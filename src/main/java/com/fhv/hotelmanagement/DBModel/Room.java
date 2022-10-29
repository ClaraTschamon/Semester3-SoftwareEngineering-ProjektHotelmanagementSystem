package com.fhv.hotelmanagement.DBModel;

import jakarta.persistence.*;

@Entity
@Table (name = "room")
public class Room {
    private int number;
    private RoomCategory category;

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
    public RoomCategory getCategory() {
        return category;
    }

    public void setCategory(RoomCategory category) {
        this.category = category;
    }
}
