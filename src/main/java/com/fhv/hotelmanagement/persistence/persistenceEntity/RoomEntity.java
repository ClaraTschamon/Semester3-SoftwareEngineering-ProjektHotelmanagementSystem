package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

@Entity
@Table (name = "room")
public class RoomEntity {

    @Id
    @Column (name = "room_number")
    private int number;

    @Column(name = "is_free")
    private boolean isFree;

    @Column(name = "is_clean")
    private boolean isClean;

    @ManyToOne
    @JoinColumn (name = "room_category_name")
    private RoomCategoryEntity category;

    public RoomEntity(){};

    public RoomEntity(int number,boolean isFree, boolean isClean, RoomCategoryEntity category) {
        this.number = number;
        this.isFree = isFree;
        this.isClean = isClean;
        this.category = category;
    }


    public int getNumber() {
        return number;
    }

    public boolean getIsFree(){
        return  isFree;
    }

    public boolean getIsClean(){
        return isClean;
    }

    public RoomCategoryEntity getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "number=" + number +
                ", isFree=" + isFree +
                ", isClean=" + isClean +
                ", category=" + category +
                '}';
    }
}
