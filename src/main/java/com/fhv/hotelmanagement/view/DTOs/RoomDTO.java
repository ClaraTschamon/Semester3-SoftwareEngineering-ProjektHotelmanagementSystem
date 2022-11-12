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

    public void setNumber(int number){
        this.number = number;
    }

    public RoomCategoryDTO getCategory(){
        return category;
    }

    public void setCategory(RoomCategoryDTO category){
        this.category = category;
    }

    public boolean getIsFree(){
        return  isFree;
    }

    public boolean getIsClean(){
        return isClean;
    }

    public void setIsClean(Boolean isClean) {
        this.isClean = isClean;
    }
}
