//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.domainModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reservation {

    private Long number;
    private Customer customer;
    private LocalDateTime creationTimestamp;
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

    private ArrayList<ReservedRoomCategory> reservedRoomCategories;

    private ArrayList<ReservedRoom> reservedRooms;

    public Reservation(Long number, Customer customer, LocalDateTime creationTimestamp, LocalDate arrivalDate, LocalDate departureDate,
                             String billingStreet, String billingHouseNumber, String billingPostalCode, String billingCity,
                             String  billingCountry, String comment, String paymentMethod, String creditCardNumber,
                             String expirationDate, String authorisationNumber, Board board, BigDecimal pricePerNightForBoard, Integer amountGuests,
                             ArrayList<ReservedRoomCategory> reservedRoomCategories, ArrayList<ReservedRoom> reservedRooms){

        this.number=number;
        this.customer = customer;
        this.creationTimestamp=creationTimestamp;
        this.arrivalDate=arrivalDate;
        this.departureDate=departureDate;
        this.billingAddress = new Address(billingStreet, billingHouseNumber, billingPostalCode, billingCity, billingCountry);
        this.comment=comment;
        this.paymentMethod=paymentMethod;
        this.creditCardNumber=creditCardNumber;
        this.expirationDate=expirationDate;
        this.authorisationNumber=authorisationNumber;
        this.board=board;
        this.pricePerNightForBoard = pricePerNightForBoard;
        this.amountGuests = amountGuests;
        this.reservedRoomCategories = reservedRoomCategories;
        this.reservedRooms = reservedRooms;
    }

    public Reservation(Customer customer, LocalDateTime creationTimestamp, LocalDate arrivalDate, LocalDate departureDate,
                       String billingStreet, String billingHouseNumber, String billingPostalCode,
                       String billingCity, String billingCountry, String comment, String paymentMethod, String creditCardNumber,
                       String expirationDate, String authorisationNumber, Board board, BigDecimal pricePerNightForBoard,
                       Integer amountGuests, ArrayList<ReservedRoomCategory> reservedRoomCategories, ArrayList<ReservedRoom> reservedRooms){
        this(null, customer, creationTimestamp, arrivalDate, departureDate, billingStreet, billingHouseNumber, billingPostalCode,
                billingCity, billingCountry, comment, paymentMethod, creditCardNumber, expirationDate, authorisationNumber, board, pricePerNightForBoard,
                amountGuests, reservedRoomCategories, reservedRooms);
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        if(this.number == null){
            this.number = number;
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Integer getAmountGuests() {
        return amountGuests;
    }

    public void setAmountGuests(Integer amountGuests) {
        this.amountGuests = amountGuests;
    }

    public ArrayList<ReservedRoomCategory> getReservedRoomCategories() {
        return reservedRoomCategories;
    }

    public void setReservedRoomCategories(ArrayList<ReservedRoomCategory> reservedRoomCategories) {
        this.reservedRoomCategories = reservedRoomCategories;
    }

    public ArrayList<ReservedRoom> getReservedRooms() {
        return reservedRooms;
    }

    public void setReservedRooms(ArrayList<ReservedRoom> reservedRooms) {
        this.reservedRooms = reservedRooms;
    }
}
