package com.fhv.hotelmanagement.view.DTOs;

public class RoomDTO {
    private int number;

    private Boolean isFree;

    private Boolean isClean;
    private RoomCategoryDTO category;

    public RoomDTO() {}

    public int getNumber() {
        return number;
    }

    public RoomCategoryDTO getCategory(){
        return category;
    }

    public boolean getIsFree(){
        return  isFree;
    }

    public boolean getIsClean(){
        return isClean;
    }
}
