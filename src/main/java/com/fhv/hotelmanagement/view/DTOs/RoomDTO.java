package com.fhv.hotelmanagement.view.DTOs;

import com.fhv.hotelmanagement.domain.domainModel.Room;

import java.util.ArrayList;

public class RoomDTO {
    private int number;

    private Boolean isFree;

    private Boolean isClean;
    private RoomCategoryDTO category;
    private int counterSingleRoom;
    private int counterDoubleRoom;
    private int counterFamilyRoom;
    private int counterSuite;
    private String roomPrice;

    private ArrayList<RoomDTO> allRooms;

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

    public int getCounterSingleRoom() {
        return counterSingleRoom;
    }

    public void setCounterSingleRoom(int counterSingleRoom) {
        this.counterSingleRoom = counterSingleRoom;
    }

    public int getCounterDoubleRoom() {
        return counterDoubleRoom;
    }

    public void setCounterDoubleRoom(int counterDoubleRoom) {
        this.counterDoubleRoom = counterDoubleRoom;
    }

    public int getCounterFamilyRoom() {
        return counterFamilyRoom;
    }

    public void setCounterFamilyRoom(int counterFamilyRoom) {
        this.counterFamilyRoom = counterFamilyRoom;
    }

    public int getCounterSuite() {
        return counterSuite;
    }

    public void setCounterSuite(int counterSuite) {
        this.counterSuite = counterSuite;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Boolean getClean() {
        return isClean;
    }

    public void setClean(Boolean clean) {
        isClean = clean;
    }

    public ArrayList<RoomDTO> getAllRooms() {
        return allRooms;
    }

    public void setAllRooms(ArrayList<RoomDTO> allRooms) {
        this.allRooms = allRooms;
    }
}
