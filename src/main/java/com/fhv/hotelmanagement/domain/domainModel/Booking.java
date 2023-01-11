//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.domainModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Booking {

    private Long number;
    private Reservation reservation;
    private Customer customer;
    private LocalDate arrivalDate;
    private LocalDateTime checkInDatetime;
    private LocalDate departureDate;
    private LocalDateTime checkOutDatetime;
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
    private ArrayList<BookedRoom> bookedRooms;

    public Booking(Long number, Reservation reservation, Customer customer, LocalDate arrivalDate, LocalDateTime checkInDatetime, LocalDate departureDate,
                   LocalDateTime checkOutDatetime, String billingStreet, String billingHouseNumber, String billingPostalCode,
                   String billingCity, String billingCountry, String comment, String paymentMethod, String creditCardNumber,
                   String expirationDate, String authorisationNumber, Board board, BigDecimal pricePerNightForBoard,
                   Integer amountGuests, ArrayList<BookedRoomCategory> bookedRoomCategories, ArrayList<BookedRoom> bookedRooms) {

        this.number = number;
        this.reservation = reservation;
        this.customer = customer;
        this.arrivalDate = arrivalDate;
        this.checkInDatetime = checkInDatetime;
        this.departureDate = departureDate;
        this.checkOutDatetime = checkOutDatetime;
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
        this.bookedRooms = bookedRooms;
    }

    public Booking(Customer customer, Reservation reservation, LocalDate arrivalDate, LocalDateTime checkInDatetime, LocalDate departureDate,
                   LocalDateTime checkOutDatetime, String billingStreet, String billingHouseNumber, String billingPostalCode,
                   String billingCity, String billingCountry, String comment, String paymentMethod, String creditCardNumber,
                   String expirationDate, String authorisationNumber, Board board, BigDecimal pricePerNightForBoard,
                   Integer amountGuests, ArrayList<BookedRoomCategory> bookedRoomCategories,
                   ArrayList<BookedRoom> bookedRooms) {
        this(null, reservation, customer, arrivalDate, checkInDatetime, departureDate, checkOutDatetime, billingStreet,
                billingHouseNumber, billingPostalCode, billingCity, billingCountry, comment, paymentMethod,
                creditCardNumber, expirationDate, authorisationNumber, board, pricePerNightForBoard, amountGuests,
                bookedRoomCategories, bookedRooms);
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        if (this.number == null) {
            this.number = number;
        }
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
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

    public LocalDateTime getCheckInDatetime() {
        return checkInDatetime;
    }

    public void setCheckInDatetime(LocalDateTime checkInDatetime) {
        this.checkInDatetime = checkInDatetime;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getCheckOutDatetime() {
        return checkOutDatetime;
    }

    public void setCheckOutDatetime(LocalDateTime checkOutDatetime) {
        this.checkOutDatetime = checkOutDatetime;
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

    public ArrayList<BookedRoom> getBookedRooms() {
        return bookedRooms;
    }

    public void setBookedRooms(ArrayList<BookedRoom> bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    public Integer getAmountGuests() {
        return amountGuests;
    }

    public void setAmountGuests(Integer amountGuests) {
        this.amountGuests = amountGuests;
    }
}
