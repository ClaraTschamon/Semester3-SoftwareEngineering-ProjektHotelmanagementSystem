package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.util.ArrayList;

public class DomainTranslator {
    protected static AddressDTO translateAddress(Address address) {
        return new AddressDTO(address.getStreet(), address.getHouseNumber(), address.getPostalCode(),
                address.getCity(), address.getCountry());
    }
    protected static BookedRoomDTO translateBookedRoom(BookedRoom bookedRoom, boolean includeBookingArrays) {
        if (!includeBookingArrays) {
            return new BookedRoomDTO(translateBooking(bookedRoom.getBooking(), false, null), translateRoom(bookedRoom.getRoom()),
                    bookedRoom.getFromDate(), bookedRoom.getToDate());
        } else {
            return new BookedRoomDTO(translateBooking(bookedRoom.getBooking(), true, bookedRoom), translateRoom(bookedRoom.getRoom()),
                    bookedRoom.getFromDate(), bookedRoom.getToDate());
        }
    }

    protected static BookedRoomCategoryDTO translateBookedRoomCategory(BookedRoomCategory bookedRoomCategory) {
        return new BookedRoomCategoryDTO(translateBooking(bookedRoomCategory.getBooking(), false, null),
                translateRoomCategory(bookedRoomCategory.getRoomCategory()), bookedRoomCategory.getPricePerNight(),
                bookedRoomCategory.getAmount());
    }

    protected static BookingDTO translateBooking(Booking booking, boolean includeArrays, BookedRoom allExcept) {
        ArrayList<BookedRoomCategoryDTO> bookedRoomCategoryDTOS = new ArrayList<>();
        ArrayList<BookedRoomDTO> bookedRoomDTOS = new ArrayList<>();

        BookingDTO bookingDTO = new BookingDTO(booking.getNumber(), translateCustomer(booking.getCustomer()), booking.getArrivalDate(),
                booking.getCheckInDatetime(), booking.getDepartureDate(), booking.getCheckOutDatetime(),
                translateAddress(booking.getBillingAddress()), booking.getPaymentMethod(), booking.getCreditCardNumber(),
                booking.getExpirationDate(), booking.getAuthorisationNumber(), translateBoard(booking.getBoard()),
                booking.getPricePerNightForBoard(), booking.getComment(), booking.getAmountGuests(),
                bookedRoomCategoryDTOS, bookedRoomDTOS);
        if (includeArrays) {
            for (BookedRoomCategory bookedRoomCategory : booking.getBookedRoomCategories()) {
                bookedRoomCategoryDTOS.add(translateBookedRoomCategory(bookedRoomCategory));
            }
            for (BookedRoom bookedRoom : booking.getBookedRooms()) {
                bookedRoomDTOS.add(translateBookedRoom(bookedRoom, false));
//                if (bookedRoom.equals(allExcept)) {
//                    bookedRoomDTOS.add(translateBookedRoom(bookedRoom, false));
//                } else {
//                    bookedRoomDTOS.add(translateBookedRoom(bookedRoom, true));
//                }
            }
        }
        return bookingDTO;
    }

    protected static CustomerDTO translateCustomer(Customer customer) {
        return new CustomerDTO(customer.getNumber(), customer.getFirstName(), customer.getLastName(),
                customer.getDateOfBirth(), customer.getNationality(), customer.getPhoneNumber(), customer.getEmail(),
                translateAddress(customer.getAddress()), customer.getSaved());
    }

    protected static RoomCategoryDTO translateRoomCategory(RoomCategory roomCategory) {
        return new RoomCategoryDTO(roomCategory.getName(), roomCategory.getPricePerNight());
    }

    protected static RoomDTO translateRoom(Room room) {
        return new RoomDTO(room.getNumber(), room.isFree(), true, //TODO when implementing clean
                translateRoomCategory(room.getCategory()));
    }

    //new
    protected static BoardDTO translateBoard(Board board){
        return new BoardDTO(board.getName(), board.getPricePerNight());
    }
}
