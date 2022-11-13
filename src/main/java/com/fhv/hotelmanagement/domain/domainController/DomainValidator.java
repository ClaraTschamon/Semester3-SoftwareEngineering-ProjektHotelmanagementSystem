package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.view.DTOs.AddressDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.DTOs.CustomerDTO;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class DomainValidator {
    protected static boolean checkAddress(AddressDTO addressDTO) {
        if (
                (!addressDTO.equals(null)) &&
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

    protected static boolean checkCustomer(CustomerDTO customerDTO) {
        if (
                (!customerDTO.equals(null)) &&
                        (checkString(customerDTO.getFirstName())) &&
                        (checkString(customerDTO.getLastName())) &&
                        (customerDTO.getDateOfBirth() != null && customerDTO.getDateOfBirth().isBefore(LocalDate.now())) &&
                        (checkString(customerDTO.getNationality())) &&
                        (checkString(customerDTO.getPhoneNumber())) && //checkValidPhoneNumber(customerDTO.getPhoneNumber())) &&
                        (checkString(customerDTO.getEmail())) && //checkValidEmail(customerDTO.getEmail())) &&
                        (checkAddress(customerDTO.getAddress())) &&
                        (customerDTO.getSaved() != null)
        ) {
            return true;
        }
        return false;
    }

    protected static boolean checkBooking(BookingDTO bookingDTO) {
        if (
                (!bookingDTO.equals(null)) &&
                        (checkCustomer(bookingDTO.getCustomer())) &&
                        (!bookingDTO.getArrivalDate().equals(null)) &&
                        (!bookingDTO.getDepartureDate().equals(null)) &&
                        (bookingDTO.getArrivalDate().isBefore(bookingDTO.getDepartureDate())) &&
                        (checkAddress(bookingDTO.getBillingAddress())) &&
                        (checkString(bookingDTO.getPaymentMethod())) &&
                        (checkString(bookingDTO.getCreditCardNumber())) &&
                        (checkString(bookingDTO.getExpirationDate()) && checkRegex(bookingDTO.getExpirationDate(), "[0-1][0-9]/[0-9][0-9]")) &&
                        (checkString(bookingDTO.getAuthorisationNumber())) &&
                        (!bookingDTO.getBookedRooms().equals(null) && !bookingDTO.getBookedRooms().isEmpty()) &&
                        (!bookingDTO.getBookedRoomCategories().equals(null) && !bookingDTO.getBookedRoomCategories().isEmpty())
        ) {
            // TODO validate arrays
            return true;
        }
        return false;
    }

    private static boolean checkString(String string) {
        if (!string.equals(null) && !string.equals("")) {
            return true;
        }
        return false;

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
