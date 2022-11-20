package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.util.ArrayList;

public class DomainCreator {

    protected static Board createBoard(BoardDTO boardDTO){
        return new Board(boardDTO.getName(), boardDTO.getPricePerNight());
    }

    protected static BookedRoom createBookedRoom(BookedRoomDTO bookedRoomDTO) {
        return new BookedRoom(createBooking(bookedRoomDTO.getBooking(), false), createRoom(bookedRoomDTO.getRoom()),
                bookedRoomDTO.getFromDate(), bookedRoomDTO.getToDate());
    }

    protected static BookedRoomCategory createBookedRoomCategory(BookedRoomCategoryDTO bookedRoomCategoryDTO) {
        return new BookedRoomCategory(createBooking(bookedRoomCategoryDTO.getBooking(), false),
                createRoomCategory(bookedRoomCategoryDTO.getRoomCategory()), bookedRoomCategoryDTO.getPricePerNight(),
                bookedRoomCategoryDTO.getAmount());
    }

    protected static Booking createBooking(BookingDTO bookingDTO, boolean fillArrays) {
        AddressDTO billingAddress = bookingDTO.getBillingAddress();

        ArrayList<BookedRoomCategory> bookedRoomCategories = new ArrayList<>();
        ArrayList<BookedRoom> bookedRooms = new ArrayList<>();

        Booking booking = new Booking(bookingDTO.getNumber(), createCustomer(bookingDTO.getCustomer()), bookingDTO.getArrivalDate(),
                bookingDTO.getCheckInDatetime(), bookingDTO.getDepartureDate(), bookingDTO.getCheckOutDatetime(),
                billingAddress.getStreet(), billingAddress.getHouseNumber(), billingAddress.getPostalCode(),
                billingAddress.getCity(), billingAddress.getCountry(), bookingDTO.getComment(), bookingDTO.getPaymentMethod(),
                bookingDTO.getCreditCardNumber(), bookingDTO.getExpirationDate(), bookingDTO.getAuthorisationNumber(),
                createBoard(bookingDTO.getBoard()), bookingDTO.getPricePerNightForBoard(), bookedRoomCategories,bookedRooms);

        if (fillArrays) {
            for (BookedRoomCategoryDTO bookedRoomCategoryDTO : bookingDTO.getBookedRoomCategories()) {
                bookedRoomCategories.add(createBookedRoomCategory(bookedRoomCategoryDTO));
            }
            for (BookedRoomDTO bookedRoomDTO : bookingDTO.getBookedRooms()) {
                bookedRooms.add(createBookedRoom(bookedRoomDTO));
            }
        }

        return booking;
    }

    protected static Customer createCustomer(CustomerDTO customerDTO) {
        AddressDTO address = customerDTO.getAddress();
        return new Customer(customerDTO.getNumber(), customerDTO.getFirstName(), customerDTO.getLastName(),
                customerDTO.getDateOfBirth(), customerDTO.getNationality(), customerDTO.getPhoneNumber(), customerDTO.getEmail(),
                address.getStreet(), address.getHouseNumber(), address.getPostalCode(), address.getCity(), address.getCountry(),
                customerDTO.getSaved(), new ArrayList<>());
    }

    protected static Room createRoom(RoomDTO roomDTO) {
        return new Room(roomDTO.getNumber(), createRoomCategory(roomDTO.getCategory()));
    }

    protected static RoomCategory createRoomCategory(RoomCategoryDTO roomCategoryDTO) {
        return new RoomCategory(roomCategoryDTO.getName(), roomCategoryDTO.getPricePerNight());
    }
}
