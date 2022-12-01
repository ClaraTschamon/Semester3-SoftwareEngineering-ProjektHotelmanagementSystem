//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Date;
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
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][ÄäÖöÜüA-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return checkRegex(email, regexPattern);
    }

    public static boolean checkValidExpirationDate(String expirationDate) {
        String regexPattern = "[0-1][0-9]/[0-9][0-9]";
        if(checkRegex(expirationDate, regexPattern)){
            //check if expired
            StringBuilder sbMonth = new StringBuilder();
            StringBuilder sbYear = new StringBuilder();
            sbMonth.append(expirationDate.charAt(0));
            sbMonth.append(expirationDate.charAt(1));
            sbYear.append(expirationDate.charAt(3));
            sbYear.append(expirationDate.charAt(4));

            int expireMonth = Integer.parseInt(sbMonth.toString());
            int expireYear = Integer.parseInt(sbYear.toString());

            SimpleDateFormat currentMonthDF = new SimpleDateFormat("MM");
            SimpleDateFormat currentYearDF = new SimpleDateFormat("yy");
            Date currentDate = new Date();

            int currentMonth = Integer.parseInt(currentMonthDF.format(currentDate));
            int currentYear = Integer.parseInt(currentYearDF.format(currentDate));

            if(expireYear > currentYear){
                return true;
            }else if(expireYear < currentYear){
                return false;
            }else{
                if(expireMonth < currentMonth){
                    return false;
                }else if(expireMonth > 12){
                    return false;
                }
                return true;
            }

        }
        return false;
    }

    public static boolean checkRegex(String string, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(string).matches();
    }

    public static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public static boolean checkHouseNumber(String houseNumber) {
        String regexPattern = "[0-9 ]{1,5}([a-zA-Z])?";
        return checkRegex(houseNumber, regexPattern);
    }

    public static boolean checkStreet(String street) {
        String regexPattern = "[0-9a-zA-Z-/]*";
        return checkRegex(street, regexPattern);
    }

    public static boolean checkCity(String city) {
        String regexPattern = "[a-zA-ZäÄöÖüÜß]*";
        return checkRegex(city, regexPattern);
    }

    public static boolean checkPostalCode(String postalCode) {
        String regexPattern = "[0-9a-zA-Z]{3,6}";
        return checkRegex(postalCode, regexPattern);
    }
}
