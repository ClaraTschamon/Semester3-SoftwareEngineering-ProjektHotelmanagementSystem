package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.view.DTOs.*;

import java.util.LinkedList;

public class DomainController {

    public void saveCustomer(CustomerDTO customerDTO) {

    }

    private Boolean checkString(String string) {
        if (!string.equals(null) && !string.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean checkInteger(Integer integer) {
        if (!integer.equals(null)) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean checkStringValidCharacters(String string, LinkedList<Character> validCharacters) {
        for (char c : string.toCharArray()) {
            if (!validCharacters.contains(c)) {
                return false;
            }
        }
        return true;
    }
}
