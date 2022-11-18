package com.fhv.hotelmanagement.view.DTOs;

import java.util.ArrayList;
import java.util.Objects;

public class RoomDTO {
    private int number;
    private Boolean isFree;
    private Boolean isClean;
    private RoomCategoryDTO category;

    public RoomDTO() {}

    public RoomDTO(int number, Boolean isFree, Boolean isClean, RoomCategoryDTO category) {
        this.number = number;
        this.isFree = isFree;
        this.isClean = isClean;
        this.category = category;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomDTO roomDTO = (RoomDTO) o;
        return getNumber() == roomDTO.getNumber() &&
                Objects.equals(getIsFree(), roomDTO.getIsFree())
                && Objects.equals(getIsClean(), roomDTO.getIsClean()) &&
                Objects.equals(getCategory(), roomDTO.getCategory());
    }

}
