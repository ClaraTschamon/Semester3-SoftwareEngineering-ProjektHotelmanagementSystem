//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.factory;

import com.fhv.hotelmanagement.domain.domainModel.BookedRoomCategory;
import com.fhv.hotelmanagement.domain.domainModel.ReservedRoomCategory;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomCategoryDTO;
import com.fhv.hotelmanagement.view.DTOs.ReservationDTO;
import com.fhv.hotelmanagement.view.DTOs.ReservedRoomCategoryDTO;

import java.util.ArrayList;

public class ReservedRoomCategoryFactory {

    private static ArrayList<BookedRoomCategory> bookedRoomCategories;

    protected static ReservedRoomCategoryDTO createReservedRoomCategoryDTO(ReservedRoomCategory reservedRoomCategory){
        return new ReservedRoomCategoryDTO(ReservationFactory.createReservationDTO(reservedRoomCategory.getReservation(), false, null),
                RoomCategoryFactory.createRoomCategoryDTO(reservedRoomCategory.getRoomCategory()), reservedRoomCategory.getPricePerNight(),
                reservedRoomCategory.getAmount());
    }

    protected static ReservedRoomCategory createReservedRoomCategory(ReservedRoomCategoryDTO reservedRoomCategoryDTO){
        return new ReservedRoomCategory(ReservationFactory.createReservation(reservedRoomCategoryDTO.getReservation(), false),
                RoomCategoryFactory.createRoomCategory(reservedRoomCategoryDTO.getRoomCategory()), reservedRoomCategoryDTO.getPricePerNight(),
                reservedRoomCategoryDTO.getAmount());
    }

    protected static boolean checkReservedRoomCategory(ReservedRoomCategoryDTO reservedRoomCategoryDTO, boolean validateReservation){
        ReservationDTO reservationDTO = reservedRoomCategoryDTO.getReservation();
        return((!validateReservation) || (validateReservation && ReservationFactory.checkReservation(reservationDTO))) &&
                (RoomCategoryFactory.getAllRoomCategories().containsValue(reservedRoomCategoryDTO.getRoomCategory())) &&
                (reservedRoomCategoryDTO.getPricePerNight() != null) &&
                (reservedRoomCategoryDTO.getAmount() > 0);
    }

    protected static boolean checkReservedRoomCategories(ArrayList<ReservedRoomCategoryDTO> reservedRoomCategoryDTOS, boolean validateReservation){
        for(ReservedRoomCategoryDTO reservedRoomCategoryDTO : reservedRoomCategoryDTOS){
            if(!checkReservedRoomCategory(reservedRoomCategoryDTO, validateReservation)){
                return false;
            }
        }
        return true;
    }
}