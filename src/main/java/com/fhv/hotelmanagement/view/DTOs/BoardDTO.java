package com.fhv.hotelmanagement.view.DTOs;

import java.math.BigDecimal;

public class BoardDTO {
    private String name;
    private BigDecimal pricePerNight;
    private boolean fullboard;
    private boolean halfboard;
    private boolean onlyBreakfast;
    private boolean noPackage;

    public BoardDTO(){}

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
}
