//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.factory;

import com.fhv.hotelmanagement.domain.domainModel.BookedRoomCategory;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomCategoryDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;

import java.util.ArrayList;

public class BookedRoomCategoryFactory {

    protected static BookedRoomCategoryDTO createRoomCategoryDTO(BookedRoomCategory bookedRoomCategory) {
        return new BookedRoomCategoryDTO(BookingFactory.createBookingDTO(bookedRoomCategory.getBooking(), false, null),
                RoomCategoryFactory.createRoomCategoryDTO(bookedRoomCategory.getRoomCategory()), bookedRoomCategory.getPricePerNight(),
                bookedRoomCategory.getAmount());
    }

    protected static BookedRoomCategory createBookedRoomCategory(BookedRoomCategoryDTO bookedRoomCategoryDTO) {
        return new BookedRoomCategory(BookingFactory.createBooking(bookedRoomCategoryDTO.getBooking(), false),
                RoomCategoryFactory.createRoomCategory(bookedRoomCategoryDTO.getRoomCategory()), bookedRoomCategoryDTO.getPricePerNight(),
                bookedRoomCategoryDTO.getAmount());
    }

    protected static boolean checkBookedRoomCategory(BookedRoomCategoryDTO bookedRoomCategoryDTO, boolean validateBooking) {
        BookingDTO bookingDTO = bookedRoomCategoryDTO.getBooking();
        return ((!validateBooking) || (validateBooking && BookingFactory.checkBooking(bookingDTO))) &&
                (RoomCategoryFactory.getAllRoomCategories().containsValue(bookedRoomCategoryDTO.getRoomCategory())) &&
                (bookedRoomCategoryDTO.getPricePerNight() != null) &&
                (bookedRoomCategoryDTO.getAmount() > 0);
    }

    protected static boolean checkBookedRoomCategories(ArrayList<BookedRoomCategoryDTO> bookedRoomCategoryDTOS, boolean validateBooking) {
        for (BookedRoomCategoryDTO bookedRoomCategoryDTO : bookedRoomCategoryDTOS) {
            if (!checkBookedRoomCategory(bookedRoomCategoryDTO, validateBooking)) {
                return false;
            }
        }
        return true;
    }
}
