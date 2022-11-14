package com.fhv.hotelmanagement.services;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class StringValidator {
    public static boolean checkString(String string) {
        return string != null && !string.equals("");

    }

    public static boolean checkStringValidCharacters(String string, LinkedList<Character> validCharacters) {
        if (string == null) {
            return false;
        }
        for (char c : string.toCharArray()) {
            if (!validCharacters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkValidPhoneNumber(String phoneNumber) {
        return checkStringValidCharacters(phoneNumber, getValidPhoneNumberCharacters());
    }

    public static LinkedList<Character> getValidPhoneNumberCharacters() {
        LinkedList<Character> validPhoneNumberCharacters = getNumberList();
        validPhoneNumberCharacters.add('/');
        validPhoneNumberCharacters.add('+');
        validPhoneNumberCharacters.add(' ');
        return validPhoneNumberCharacters;
    }

    public static LinkedList<Character> getNumberList() {
        return new LinkedList<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
    }

    public static boolean checkValidEmail(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return checkRegex(email, regexPattern);
    }

    public static boolean checkValidExpirationDate(String expirationDate) {
        String regexPattern = "[0-1][0-9]/[0-9][0-9]";
        return checkRegex(expirationDate, regexPattern);
    }

    public static boolean checkRegex(String string, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(string).matches();
    }
}
