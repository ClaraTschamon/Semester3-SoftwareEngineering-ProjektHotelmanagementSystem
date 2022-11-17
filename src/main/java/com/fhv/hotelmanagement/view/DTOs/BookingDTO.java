package com.fhv.hotelmanagement.view.DTOs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BookingDTO {

    private Long number;
    private CustomerDTO customer;
    private LocalDate arrivalDate;
    private LocalDateTime checkInDatetime;
    private LocalDate departureDate;
    private LocalDateTime checkOutDatetime;
    private AddressDTO billingAddress;
    private String paymentMethod;
    private String creditCardNumber;
    private String expirationDate;
    private String authorisationNumber;
    private BoardDTO board;
    private BigDecimal pricePerNightForBoard;
    private String comment;
    private ArrayList<BookedRoomCategoryDTO> bookedRoomCategories;
    private ArrayList<BookedRoomDTO> bookedRooms;

    public BookingDTO() { this.billingAddress = new AddressDTO();}

    public BookingDTO(Long number, CustomerDTO customer, LocalDate arrivalDate,
                      LocalDateTime checkInDatetime, LocalDate departureDate,
                      LocalDateTime checkOutDatetime, AddressDTO billingAddress,
                      String paymentMethod, String creditCardNumber, String expirationDate,
                      String authorisationNumber, BoardDTO board, BigDecimal pricePerNightForBoard,
                      String comment, ArrayList<BookedRoomCategoryDTO> bookedRoomCategories,
                      ArrayList<BookedRoomDTO> bookedRooms) {
        this.number = number;
        this.customer = customer;
        this.arrivalDate = arrivalDate;
        this.checkInDatetime = checkInDatetime;
        this.departureDate = departureDate;
        this.checkOutDatetime = checkOutDatetime;
        this.billingAddress = billingAddress;
        this.paymentMethod = paymentMethod;
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.authorisationNumber = authorisationNumber;
        this.board = board;
        this.pricePerNightForBoard = pricePerNightForBoard;
        this.comment = comment;
        this.bookedRoomCategories = bookedRoomCategories;
        this.bookedRooms = bookedRooms;
    }

    public BookingDTO(CustomerDTO customer, LocalDate arrivalDate,
                      LocalDateTime checkInDatetime, LocalDate departureDate,
                      LocalDateTime checkOutDatetime, AddressDTO billingAddress,
                      String paymentMethod, String creditCardNumber, String expirationDate,
                      String authorisationNumber, BoardDTO board, BigDecimal pricePerNightForBoard,
                      String comment, ArrayList<BookedRoomCategoryDTO> bookedRoomCategories,
                      ArrayList<BookedRoomDTO> bookedRooms) {
        this(null, customer, arrivalDate, checkInDatetime, departureDate, checkOutDatetime, billingAddress, paymentMethod, creditCardNumber, expirationDate, authorisationNumber, board, pricePerNightForBoard,comment, bookedRoomCategories,bookedRooms);
    }

    public Long getNumber() {
        return number;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
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

    public AddressDTO getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(AddressDTO billingAddress) {
        this.billingAddress = billingAddress;
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

    public BoardDTO getBoard() {
        return board;
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

    public ArrayList<BookedRoomCategoryDTO> getBookedRoomCategories() {
        return bookedRoomCategories;
    }

    public void setBookedRoomCategories(ArrayList<BookedRoomCategoryDTO> bookedRoomCategories) {
        this.bookedRoomCategories = bookedRoomCategories;
    }

    public ArrayList<BookedRoomDTO> getBookedRooms() {
        return bookedRooms;
    }

    public void setBookedRooms(ArrayList<BookedRoomDTO> bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
