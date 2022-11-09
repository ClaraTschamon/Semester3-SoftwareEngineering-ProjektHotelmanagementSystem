package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

@Entity
@Table (name = "room")
public class RoomEntity {
    private int number;

    private boolean isFree;

    private boolean isClean;
    private RoomCategoryEntity category;

    public RoomEntity(){};

    public RoomEntity(int number,boolean isFree, boolean isClean, RoomCategoryEntity category) {
        this.number = number;
        this.isFree = isFree;
        this.isClean = isClean;
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

    @Column(name = "is_free")
    public boolean getIsFree(){
        return  isFree;
    }

    public void setIsFree(boolean isFree){
        this.isFree = isFree;
    }

    @Column(name = "is_clean")
    public boolean getIsClean(){
        return isClean;
    }

    public void setIsClean(boolean isClean){
        this.isClean = isClean;
    }

    @ManyToOne
    @JoinColumn (name = "room_category_name")
    public RoomCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(RoomCategoryEntity category) {
        this.category = category;
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
