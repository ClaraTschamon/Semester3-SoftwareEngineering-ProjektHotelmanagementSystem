package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.util.ArrayList;

public class DomainTranslator {
    protected static AddressDTO translateAddress(Address address) {
        return new AddressDTO(address.getStreet(), address.getHouseNumber(), address.getPostalCode(),
                address.getCity(), address.getCountry());
    }
    protected static BookedRoomDTO translateBookedRoom(BookedRoom bookedRoom) {
        return new BookedRoomDTO(translateBooking(bookedRoom.getBooking(), false), translateRoom(bookedRoom.getRoom()),
                bookedRoom.getFromDate(), bookedRoom.getToDate());
    }

    protected static BookingDTO translateBooking(Booking booking, boolean includeArrays) {
        BookingDTO bookingDTO = new BookingDTO(booking.getNumber(), translateCustomer(booking.getCustomer()), booking.getArrivalDate(),
                booking.getCheckInDatetime(), booking.getDepartureDate(), booking.getCheckOutDatetime(),
                translateAddress(booking.getBillingAddress()), booking.getPaymentMethod(), booking.getCreditCardNumber(),
                booking.getExpirationDate(), booking.getAuthorisationNumber(), translateBoard(booking.getBoard()), booking.getPricePerNightForBoard(),booking.getComment(),
                new ArrayList<BookedRoomCategoryDTO>(), new ArrayList<BookedRoomDTO>());
        if (includeArrays) {
            // TODO
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
        return new RoomDTO(room.getNumber(), room.getIsFree(), room.getIsClean(),
                translateRoomCategory(room.getCategory()));
    }

    //new
    protected static BoardDTO translateBoard(Board board){
        return new BoardDTO(board.getName(), board.getPricePerNight());
    }
}
