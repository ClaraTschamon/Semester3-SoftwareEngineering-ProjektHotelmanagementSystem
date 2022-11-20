package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.services.StringValidator;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class DomainValidator {
    protected static boolean checkAddress(AddressDTO addressDTO) {
        return (addressDTO != null) &&
                (StringValidator.checkString(addressDTO.getStreet())) &&
                (StringValidator.checkString(addressDTO.getHouseNumber())) &&
                (StringValidator.checkString(addressDTO.getCity())) &&
                (StringValidator.checkString(addressDTO.getPostalCode())) &&
                (StringValidator.checkString(addressDTO.getCountry()));
    }

    protected static boolean checkBoard(BoardDTO boardDTO) {
        return (MainApplication.getDomainManager().getAllBoardDTOs().contains(boardDTO));
    }

    protected static boolean checkBookedRoom(BookedRoomDTO bookedRoomDTO, boolean validateBooking) {
        BookingDTO bookingDTO = bookedRoomDTO.getBooking();
        return ((!validateBooking) || (validateBooking && checkBooking(bookingDTO))) &&
                (MainApplication.getDomainManager().getAllRoomDTOs().contains(bookedRoomDTO.getRoom())) &&
                (bookedRoomDTO.getFromDate().isEqual(bookingDTO.getArrivalDate())
                        || (bookedRoomDTO.getFromDate().isAfter(bookingDTO.getArrivalDate()) && bookedRoomDTO.getFromDate().isBefore(bookingDTO.getDepartureDate()))) &&
                (bookedRoomDTO.getToDate().isEqual(bookingDTO.getDepartureDate())
                        || (bookedRoomDTO.getToDate().isBefore(bookingDTO.getDepartureDate()) && bookedRoomDTO.getToDate().isAfter(bookingDTO.getArrivalDate())));
    }

    protected static boolean checkBookedRooms(ArrayList<BookedRoomDTO> bookedRoomDTOS, boolean validateBooking) {
        for (BookedRoomDTO bookedRoomDTO : bookedRoomDTOS) {
            if (!checkBookedRoom(bookedRoomDTO, validateBooking)) {
                return false;
            }
        }
        return true;
    }

    protected static boolean checkBookedRoomCategory(BookedRoomCategoryDTO bookedRoomCategoryDTO, boolean validateBooking) {
        BookingDTO bookingDTO = bookedRoomCategoryDTO.getBooking();
//        System.out.println(validateBooking+" "+
//                MainApplication.getDomainManager().getAllRoomCategoryDTOs().containsValue(bookedRoomCategoryDTO.getRoomCategory())+" "+
//                  bookedRoomCategoryDTO.getPricePerNight() != null + " "+bookedRoomCategoryDTO.getAmount());
        return ((!validateBooking) || (validateBooking && checkBooking(bookingDTO))) &&
                (MainApplication.getDomainManager().getAllRoomCategoryDTOs().containsValue(bookedRoomCategoryDTO.getRoomCategory())) &&
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

    protected static boolean checkBooking(BookingDTO bookingDTO) {
//        System.out.println(bookingDTO + " " +checkCustomer(bookingDTO.getCustomer()) + " " +bookingDTO.getArrivalDate() + " " +
//                bookingDTO.getDepartureDate() + " " + bookingDTO.getArrivalDate().isBefore(bookingDTO.getDepartureDate()) + " " +
//                checkAddress(bookingDTO.getBillingAddress()) + " " + StringValidator.checkString(bookingDTO.getPaymentMethod()) + " " +
//                StringValidator.checkString(bookingDTO.getCreditCardNumber()) + " " + StringValidator.checkString(bookingDTO.getExpirationDate())
//                + StringValidator.checkValidExpirationDate(bookingDTO.getExpirationDate()) + " " + StringValidator.checkValidExpirationDate(bookingDTO.getExpirationDate())
//                + " " + StringValidator.checkString(bookingDTO.getAuthorisationNumber()) + " " + bookingDTO.getBookedRooms() + " " +
//                bookingDTO.getBookedRooms().isEmpty() + " " + bookingDTO.getBookedRoomCategories() + " " + bookingDTO.getBookedRoomCategories().isEmpty() +
//                " " + checkBookedRoomCategories(bookingDTO.getBookedRoomCategories(), false) + " " + checkBookedRooms(bookingDTO.getBookedRooms(), false) +
//                " " + bookingDTO.getBoard() + " " + bookingDTO.getPricePerNightForBoard() + " " + checkBoard(bookingDTO.getBoard()) + " " +
//                bookingDTO.getPricePerNightForBoard() + " " + checkBoard(bookingDTO.getBoard()) + " " + bookingDTO.getPricePerNightForBoard() + " " +
//                bookingDTO.getPricePerNightForBoard().intValue()
//        );

        return (bookingDTO != null) &&
                (checkCustomer(bookingDTO.getCustomer())) &&
                (bookingDTO.getArrivalDate() != null) &&
                (bookingDTO.getDepartureDate() != null) &&
                (bookingDTO.getArrivalDate().isBefore(bookingDTO.getDepartureDate())) &&
                (checkAddress(bookingDTO.getBillingAddress())) &&
                (StringValidator.checkString(bookingDTO.getPaymentMethod())) &&
                (StringValidator.checkString(bookingDTO.getCreditCardNumber())) &&
                (StringValidator.checkString(bookingDTO.getExpirationDate()) && StringValidator.checkValidExpirationDate(bookingDTO.getExpirationDate())) &&
                (StringValidator.checkString(bookingDTO.getAuthorisationNumber())) &&
                (bookingDTO.getBookedRooms() != null && !bookingDTO.getBookedRooms().isEmpty()) &&
                (bookingDTO.getBookedRoomCategories() != null && !bookingDTO.getBookedRoomCategories().isEmpty()) &&
                (checkBookedRoomCategories(bookingDTO.getBookedRoomCategories(), false)) &&
                (checkBookedRooms(bookingDTO.getBookedRooms(), false)) &&
                ((bookingDTO.getBoard() == null && bookingDTO.getPricePerNightForBoard() == null)
                        || (checkBoard(bookingDTO.getBoard()) && bookingDTO.getPricePerNightForBoard() != null && bookingDTO.getPricePerNightForBoard().intValue() >= 0));
    }

    protected static boolean checkCustomer(CustomerDTO customerDTO) {
        return (customerDTO != null) &&
                (StringValidator.checkString(customerDTO.getFirstName())) &&
                (StringValidator.checkString(customerDTO.getLastName())) &&
                (customerDTO.getDateOfBirth() != null && customerDTO.getDateOfBirth().isBefore(LocalDate.now())) &&
                (StringValidator.checkString(customerDTO.getNationality())) &&
                (StringValidator.checkString(customerDTO.getPhoneNumber()) && StringValidator.checkValidPhoneNumber(customerDTO.getPhoneNumber())) &&
                (StringValidator.checkString(customerDTO.getEmail()) && StringValidator.checkValidEmail(customerDTO.getEmail())) &&
                (checkAddress(customerDTO.getAddress())) &&
                (customerDTO.getSaved() != null);
    }

    public static boolean checkInvoice(InvoiceDTO invoiceDTO) {
        return true; // TODO
    }
}
