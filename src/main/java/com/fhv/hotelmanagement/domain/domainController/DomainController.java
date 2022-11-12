package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.view.DTOs.*;
import com.fhv.hotelmanagement.persistence.dataMapper.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class DomainController {

    public void saveCustomer(CustomerDTO customerDTO) {
        AddressDTO address = customerDTO.getAddress();
        if (
                (checkString(customerDTO.getFirstName())) &&
                (checkString(customerDTO.getLastName())) &&
                (customerDTO.getDateOfBirth().isBefore(LocalDate.now())) &&
                (checkString(customerDTO.getNationality())) &&
                (checkValidPhoneNumber(customerDTO.getPhoneNumber())) &&
                (checkValidEmail(customerDTO.getEmail())) &&
                (checkString(address.getStreet())) &&
                (checkString(address.getHouseNumber())) &&
                (checkString(address.getCity())) &&
                (checkString(address.getPostalCode())) &&
                (checkString(address.getCountry())) &&
                (!customerDTO.getSaved().equals(null))
        ) {
            boolean isNew = customerDTO.getNumber().equals(null);
            Customer customer = new Customer(customerDTO.getNumber(), customerDTO.getFirstName(), customerDTO.getLastName(),
                    customerDTO.getDateOfBirth(), customerDTO.getNationality(), customerDTO.getPhoneNumber(), customerDTO.getEmail(),
                    address.getStreet(), address.getHouseNumber(), address.getPostalCode(), address.getCity(), address.getCountry(),
                    customerDTO.getSaved(), new ArrayList<>());

            if (isNew) {
                CustomerDataMapper.instance().insert(customer);
            } else {
                CustomerDataMapper.instance().store(customer);
            }
        }
    }

    private boolean checkString(String string) {
        if (!string.equals(null) && !string.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkInteger(Integer integer) {
        if (!integer.equals(null)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkStringValidCharacters(String string, LinkedList<Character> validCharacters) {
        for (char c : string.toCharArray()) {
            if (!validCharacters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkValidPhoneNumber(String phoneNumber) {
        return checkStringValidCharacters(phoneNumber, getValidPhoneNumberCharacters());
    }

    private LinkedList<Character> getValidPhoneNumberCharacters() {
        LinkedList<Character> validPhoneNumberCharacters = getNumberList();
        validPhoneNumberCharacters.add('/');
        validPhoneNumberCharacters.add('+');
        validPhoneNumberCharacters.add(' ');
        return validPhoneNumberCharacters;
    }

    private LinkedList<Character> getNumberList() {
        return new LinkedList<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
    }

    private boolean checkValidEmail(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern).matcher(email).matches();
    }
}
