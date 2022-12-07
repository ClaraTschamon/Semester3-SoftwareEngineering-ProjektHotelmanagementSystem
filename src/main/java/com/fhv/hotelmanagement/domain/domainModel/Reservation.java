//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.domainModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reservation {

    private Long number;
    private Customer customer;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private Address billingAddress;
    private String comment;
    private String paymentMethod;
    private String creditCardNumber;
    private String expirationDate;
    private String authorisationNumber;
    private Board board;
    private BigDecimal pricePerNightForBoard;
    private Integer amountGuests;
    private ArrayList<BookedRoomCategory> bookedRoomCategories;


    public Reservation(Long number, Customer customer, LocalDate arrivalDate, LocalDate departureDate, String billingStreet, String billingHouseNumber, String billingPostalCode,
                   String billingCity, String billingCountry, String comment, String paymentMethod, String creditCardNumber,
                   String expirationDate, String authorisationNumber, Board board, BigDecimal pricePerNightForBoard,
                   Integer amountGuests, ArrayList<BookedRoomCategory> bookedRoomCategories) {

        this.number = number;
        this.customer = customer;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.billingAddress = new Address(billingStreet, billingHouseNumber, billingPostalCode, billingCity, billingCountry);
        this.comment = comment;
        this.paymentMethod = paymentMethod;
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.authorisationNumber = authorisationNumber;
        this.board = board;
        this.pricePerNightForBoard = pricePerNightForBoard;
        this.amountGuests = amountGuests;
        this.bookedRoomCategories = bookedRoomCategories;
    }

    public Reservation(Customer customer, LocalDate arrivalDate, LocalDateTime checkInDatetime, LocalDate departureDate,
                   LocalDateTime checkOutDatetime, String billingStreet, String billingHouseNumber, String billingPostalCode,
                   String billingCity, String billingCountry, String comment, String paymentMethod, String creditCardNumber,
                   String expirationDate, String authorisationNumber, Board board, BigDecimal pricePerNightForBoard,
                   Integer amountGuests, ArrayList<BookedRoomCategory> bookedRoomCategories,
                   ArrayList<BookedRoom> bookedRooms) {
        this(null,customer, arrivalDate, departureDate, billingStreet,
                billingHouseNumber, billingPostalCode, billingCity, billingCountry, comment, paymentMethod,
                creditCardNumber, expirationDate, authorisationNumber, board, pricePerNightForBoard, amountGuests,
                bookedRoomCategories);
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        if (this.number == null) {
            this.number = number;
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getAuthorisationNumber() {
        return authorisationNumber;
    }

    public void setAuthorisationNumber(String authorisationNumber) {
        this.authorisationNumber = authorisationNumber;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public BigDecimal getPricePerNightForBoard() {
        return pricePerNightForBoard;
    }

    public void setPricePerNightForBoard(BigDecimal pricePerNightForBoard) {
        this.pricePerNightForBoard = pricePerNightForBoard;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ArrayList<BookedRoomCategory> getBookedRoomCategories() {
        return bookedRoomCategories;
    }

    public void setBookedRoomCategories(ArrayList<BookedRoomCategory> bookedRoomCategories) {
        this.bookedRoomCategories = bookedRoomCategories;
    }

    public Integer getAmountGuests() {
        return amountGuests;
    }

    public void setAmountGuests(Integer amountGuests) {
        this.amountGuests = amountGuests;
    }
}
