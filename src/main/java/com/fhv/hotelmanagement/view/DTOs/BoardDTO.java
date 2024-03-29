//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.DTOs;

import java.math.BigDecimal;

public class BoardDTO {
    private String name;
    private BigDecimal pricePerNight;
    private boolean fullboard = true;
    private boolean halfboard;
    private boolean onlyBreakfast;
    private boolean noPackage;

    public BoardDTO(){}

    public BoardDTO(String name, BigDecimal pricePerNight){
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

    public boolean isFullboard() {
        return fullboard;
    }

    public void setFullboard(boolean fullboard) {
        this.fullboard = fullboard;
    }

    public boolean isNoPackage() {
        return noPackage;
    }

    public void setNoPackage(boolean noPackage) {
        this.noPackage = noPackage;
    }

    public boolean isHalfboard() {
        return halfboard;
    }

    public void setHalfboard(boolean halfboard) {
        this.halfboard = halfboard;
    }

    public boolean isOnlyBreakfast() {
        return onlyBreakfast;
    }

    public void setOnlyBreakfast(boolean onlyBreakfast) {
        this.onlyBreakfast = onlyBreakfast;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDTO boardDTO = (BoardDTO) o;
        return (boardDTO.getName().equals(name)) &&
                (boardDTO.getPricePerNight().equals(pricePerNight));
    }
}
