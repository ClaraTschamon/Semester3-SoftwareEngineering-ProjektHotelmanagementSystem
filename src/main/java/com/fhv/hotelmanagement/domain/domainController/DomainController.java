package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class DomainController {

    public static boolean checkAddress(AddressDTO addressDTO) {
        if (
                (addressDTO.equals(null)) &&
                (checkString(addressDTO.getStreet())) &&
                (checkString(addressDTO.getHouseNumber())) &&
                (checkString(addressDTO.getCity())) &&
                (checkString(addressDTO.getPostalCode())) &&
                (checkString(addressDTO.getCountry()))
        ) {
            return true;
        }
        return false;
    }

    private static boolean checkCustomer(CustomerDTO customerDTO) {
        if (
                (!customerDTO.equals(null)) &&
                (checkString(customerDTO.getFirstName())) &&
                (checkString(customerDTO.getLastName())) &&
                (!customerDTO.getDateOfBirth().equals(null) && customerDTO.getDateOfBirth().isBefore(LocalDate.now())) &&
                (checkString(customerDTO.getNationality())) &&
                (checkString(customerDTO.getPhoneNumber()) && checkValidPhoneNumber(customerDTO.getPhoneNumber())) &&
                (checkString(customerDTO.getEmail()) && checkValidEmail(customerDTO.getEmail())) &&
                (checkAddress(customerDTO.getAddress())) &&
                (!customerDTO.getSaved().equals(null))
        ) {
            return true;
        }
        return false;
    }

    private static Customer createCustomer(CustomerDTO customerDTO) {
        AddressDTO address = customerDTO.getAddress();
        return new Customer(customerDTO.getNumber(), customerDTO.getFirstName(), customerDTO.getLastName(),
                customerDTO.getDateOfBirth(), customerDTO.getNationality(), customerDTO.getPhoneNumber(), customerDTO.getEmail(),
                address.getStreet(), address.getHouseNumber(), address.getPostalCode(), address.getCity(), address.getCountry(),
                customerDTO.getSaved(), new ArrayList<>());
    }

    public static boolean saveCustomer(CustomerDTO customerDTO) {
        boolean saved = false;

        if (checkCustomer(customerDTO)) {
            boolean isNew = customerDTO.getNumber().equals(null);
            Customer customer = createCustomer(customerDTO);

            if (isNew) {
                PersistenceFacade.insertCustomer(customer);
            } else {
                PersistenceFacade.storeCustomer(customer);
            }
            saved = true;
        }
        return saved;
    }

    public static boolean checkBooking(BookingDTO bookingDTO) {
        if (
                (!bookingDTO.equals(null)) &&
                (checkCustomer(bookingDTO.getCustomer())) &&
                (!bookingDTO.getArrivalDate().equals(null)) &&
                (!bookingDTO.getDepartureDate().equals(null)) &&
                (bookingDTO.getArrivalDate().isBefore(bookingDTO.getDepartureDate())) &&
                (checkAddress(bookingDTO.getBillingAddress())) &&
                (checkString(bookingDTO.getPaymentMethod())) &&
                (checkString(bookingDTO.getCreditCardNumber())) &&
                (checkString(bookingDTO.getExpirationDate()) && checkRegex(bookingDTO.getExpirationDate(), "")) &&
                (checkString(bookingDTO.getAuthorisationNumber())) &&
                (!bookingDTO.getBookedRooms().equals(null) && !bookingDTO.getBookedRooms().isEmpty()) &&
                (!bookingDTO.getBookedRoomCategories().equals(null) && !bookingDTO.getBookedRoomCategories().isEmpty())
        ) {
            return true;
        }
        return false;
    }

    private static Booking createBooking(BookingDTO bookingDTO) {
        AddressDTO billingAddress = bookingDTO.getBillingAddress();

        ArrayList<BookedRoomCategory> bookedRoomCategories = new ArrayList<>();
        ArrayList<BookedRoom> bookedRooms = new ArrayList<>();


        Booking booking = new Booking(bookingDTO.getNumber(), createCustomer(bookingDTO.getCustomer()), bookingDTO.getArrivalDate(),
                bookingDTO.getCheckInDatetime(), bookingDTO.getDepartureDate(), bookingDTO.getCheckOutDatetime(),
                billingAddress.getStreet(), billingAddress.getHouseNumber(), billingAddress.getPostalCode(),
                billingAddress.getCity(), billingAddress.getCountry(), bookingDTO.getComment(), bookingDTO.getPaymentMethod(),
                bookingDTO.getCreditCardNumber(), bookingDTO.getExpirationDate(), bookingDTO.getAuthorisationNumber(),
                bookedRoomCategories, bookedRooms);

        // TODO fill lists
        return booking;
    }

    public static boolean saveBooking(BookingDTO bookingDTO) {
        boolean saved = false;
        if (checkBooking(bookingDTO)) {
            AddressDTO billingAddress = bookingDTO.getBillingAddress();
            boolean isNew = bookingDTO.getNumber().equals(null);



            Booking booking = new Booking(bookingDTO.getNumber(), bookingDTO.getCustomer(), bookingDTO.getArrivalDate(),
                    bookingDTO.getCheckInDatetime(), bookingDTO.getDepartureDate(), bookingDTO.getCheckOutDatetime(),
                    billingAddress.getStreet(), billingAddress.getHouseNumber(), billingAddress.getPostalCode(),
                    billingAddress.getCity(), billingAddress.getCountry(), bookingDTO.getComment(), bookingDTO.getPaymentMethod(),
                    bookingDTO.getCreditCardNumber(), bookingDTO.getExpirationDate(), bookingDTO.getAuthorisationNumber(),
                    bookedRoomCategories, bookedRooms);

            if (isNew) {
                PersistenceFacade.insertCustomer(customer);
            } else {
                PersistenceFacade.storeCustomer(customer);
            }
            saved = true;
        }
        return saved;
    }

    private static boolean checkString(String string) {
        if (!string.equals(null) && !string.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean checkStringValidCharacters(String string, LinkedList<Character> validCharacters) {
        for (char c : string.toCharArray()) {
            if (!validCharacters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkValidPhoneNumber(String phoneNumber) {
        return checkStringValidCharacters(phoneNumber, getValidPhoneNumberCharacters());
    }

    private static LinkedList<Character> getValidPhoneNumberCharacters() {
        LinkedList<Character> validPhoneNumberCharacters = getNumberList();
        validPhoneNumberCharacters.add('/');
        validPhoneNumberCharacters.add('+');
        validPhoneNumberCharacters.add(' ');
        return validPhoneNumberCharacters;
    }

    private static LinkedList<Character> getNumberList() {
        return new LinkedList<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
    }

    private static boolean checkValidEmail(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return checkRegex(email, regexPattern);
    }

    private static boolean checkRegex(String string, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(string).matches();
    }
}
