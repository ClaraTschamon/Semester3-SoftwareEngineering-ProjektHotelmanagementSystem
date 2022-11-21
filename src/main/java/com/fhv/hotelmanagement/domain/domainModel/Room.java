package com.fhv.hotelmanagement.domain.domainModel;

import com.fhv.hotelmanagement.persistence.PersistenceFacade;

import java.time.LocalDate;
import java.util.Objects;

public class Room {
    private Integer number;
    private RoomCategory category;

    public Room(Integer number, RoomCategory category) {
        this.number = number;
        this.category = category;
    }

    public Integer getNumber() {
        return number;
    }

    public RoomCategory getCategory(){
        return category;
    }

    public boolean isFree() {
        for (BookedRoom r : PersistenceFacade.getBookedRoomsBetween(LocalDate.now(), LocalDate.now())) {
            if (r.getRoom().equals(this)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return getNumber() == room.getNumber() &&
                Objects.equals(getCategory(), room.getCategory());
    }
}
