//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.DTOs;

public class DepositDTO {

    private String date;
    private String amount;
    private String iban;
    private String reservationNumber;

    public DepositDTO(String date, String amount, String iban, String reservationNumber) {
        this.date = date;
        this.amount = amount;
        this.iban = iban;
        this.reservationNumber = reservationNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }
}
