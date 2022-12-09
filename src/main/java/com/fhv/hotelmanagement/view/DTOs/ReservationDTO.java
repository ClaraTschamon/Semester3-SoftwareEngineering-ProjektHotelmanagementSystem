//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.DTOs;

import com.fhv.hotelmanagement.domain.domainModel.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReservationDTO {
    private Long number;
    private BookingDTO booking;
    private CustomerDTO customer;
    private LocalDateTime creationTimestamp;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private AddressDTO billingAddress;
    private String comment;
    private String paymentMethod;
    private String creditCardNumber;
    private String expirationDate;
    private String authorisationNumber;
    private BoardDTO board;
    private BigDecimal pricePerNightForBoard;

    private Integer amountGuests;

    private ArrayList<ReservedRoomCategoryDTO> reservedRoomCategories;

    private ArrayList<ReservedRoomDTO> reservedRooms;

    public ReservationDTO(){this.billingAddress = new AddressDTO();}

    public ReservationDTO(Long number, BookingDTO booking, CustomerDTO customer, LocalDateTime creationTimestamp, LocalDate arrivalDate, LocalDate departureDate,
                       AddressDTO billingAddress, String paymentMethod, String creditCardNumber,
                       String expirationDate, String authorisationNumber, BoardDTO board, BigDecimal pricePerNightForBoard, String comment, Integer amountGuests,
                       ArrayList<ReservedRoomCategoryDTO> reservedRoomCategories, ArrayList<ReservedRoomDTO> reservedRooms){

        this.number=number;
        this.booking = booking;
        this.customer = customer;
        this.creationTimestamp=creationTimestamp;
        this.arrivalDate=arrivalDate;
        this.departureDate=departureDate;
        this.billingAddress = billingAddress;
        this.paymentMethod=paymentMethod;
        this.creditCardNumber=creditCardNumber;
        this.expirationDate=expirationDate;
        this.authorisationNumber=authorisationNumber;
        this.board=board;
        this.pricePerNightForBoard = pricePerNightForBoard;
        this.comment=comment;
        this.amountGuests = amountGuests;
        this.reservedRoomCategories = reservedRoomCategories;
        this.reservedRooms = reservedRooms;
    }

    public ReservationDTO(CustomerDTO customer, BookingDTO booking, LocalDateTime creationTimestamp, LocalDate arrivalDate, LocalDate departureDate,
                       AddressDTO billingAddress, String paymentMethod, String creditCardNumber,
                       String expirationDate, String authorisationNumber, BoardDTO board, BigDecimal pricePerNightForBoard, String comment,
                       Integer amountGuests, ArrayList<ReservedRoomCategoryDTO> reservedRoomCategories, ArrayList<ReservedRoomDTO> reservedRooms){
        this(null, booking, customer, creationTimestamp, arrivalDate, departureDate, billingAddress, paymentMethod, creditCardNumber, expirationDate, authorisationNumber, board, pricePerNightForBoard,
                comment ,amountGuests, reservedRoomCategories, reservedRooms);
    }

    public Long getNumber() {
        return number;
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
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

    public AddressDTO getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(AddressDTO billingAddress) {
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

    public BoardDTO getBoard() {
        return board;
    }

    public void setBoard(BoardDTO board) {
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

    public ArrayList<ReservedRoomCategoryDTO> getReservedRoomCategories() {
        return reservedRoomCategories;
    }

    public void setReservedRoomCategories(ArrayList<ReservedRoomCategoryDTO> reservedRoomCategories) {
        this.reservedRoomCategories = reservedRoomCategories;
    }

    public ArrayList<ReservedRoomDTO> getReservedRooms() {
        return reservedRooms;
    }

    public void setReservedRooms(ArrayList<ReservedRoomDTO> reservedRooms) {
        this.reservedRooms = reservedRooms;
    }
}
