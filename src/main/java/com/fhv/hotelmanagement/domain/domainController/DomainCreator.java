package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.util.ArrayList;

public class DomainCreator {
    protected static Customer createCustomer(CustomerDTO customerDTO) {
        AddressDTO address = customerDTO.getAddress();
        return new Customer(customerDTO.getNumber(), customerDTO.getFirstName(), customerDTO.getLastName(),
                customerDTO.getDateOfBirth(), customerDTO.getNationality(), customerDTO.getPhoneNumber(), customerDTO.getEmail(),
                address.getStreet(), address.getHouseNumber(), address.getPostalCode(), address.getCity(), address.getCountry(),
                customerDTO.getSaved(), new ArrayList<>());
    }

    protected static Booking createBooking(BookingDTO bookingDTO) {
        AddressDTO billingAddress = bookingDTO.getBillingAddress();

        ArrayList<BookedRoomCategory> bookedRoomCategories = new ArrayList<>();
        ArrayList<BookedRoom> bookedRooms = new ArrayList<>();

        Booking booking = new Booking(bookingDTO.getNumber(), createCustomer(bookingDTO.getCustomer()), bookingDTO.getArrivalDate(),
                bookingDTO.getCheckInDatetime(), bookingDTO.getDepartureDate(), bookingDTO.getCheckOutDatetime(),
                billingAddress.getStreet(), billingAddress.getHouseNumber(), billingAddress.getPostalCode(),
                billingAddress.getCity(), billingAddress.getCountry(), bookingDTO.getComment(), bookingDTO.getPaymentMethod(),
                bookingDTO.getCreditCardNumber(), bookingDTO.getExpirationDate(), bookingDTO.getAuthorisationNumber(),
                createBoard(bookingDTO.getBoard()), bookingDTO.getPricePerNightForBoard(), bookedRoomCategories,bookedRooms);

        // TODO fill lists
        return booking;
    }

    protected static Board createBoard(BoardDTO boardDTO){
        return new Board(boardDTO.getName(), boardDTO.getPricePerNight());
    }

//    protected static BookedRoom createBookedRoom(BookedRoomDTO bookedRoomDTO) {
//        return new BookedRoom(createBooking(bookedRoomDTO.getBooking()), bookedRoomDTO.);
//    }
}
