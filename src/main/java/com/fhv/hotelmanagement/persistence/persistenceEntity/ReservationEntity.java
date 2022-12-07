package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_number")
    private Long number;

    @ManyToOne()
    @JoinColumn(name = "customer_number", nullable = false)
    private CustomerEntity customer;

    @Column(name = "creation_timestamp")
    private LocalDateTime creationTimestamp;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "billing_street")
    private String billingStreet;

    @Column(name = "billing_house_number")
    private String billingHouseNumber;

    @Column(name = "billing_postal_code")
    private String billingPostalCode;

    @Column(name = "billing_city")
    private String billingCity;

    @Column(name = "billing_country")
    private String billingCountry;

    @Column(name = "comment")
    private String comment;

    @Column(name ="payment_method")
    private String paymentMethod;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    @Column(name = "expiration_date")
    private String expirationDate;

    @Column(name = "authorisation_number")
    private String authorisationNumber;

    @ManyToOne
    @JoinColumn(name="board_name")
    private BoardEntity board;

    @Column(name = "price_per_night_for_board")
    private Integer price_per_night_for_board;

    public ReservationEntity(){}

    public ReservationEntity(Long number, LocalDateTime creationTimestamp, LocalDate arrivalDate, LocalDate departureDate,
                             String billingStreet, String billingHouseNumber, String billingPostalCode, String billingCity,
                             String  billingCountry, String comment, String paymentMethod, String creditCardNumber,
                             String expirationDate, String authorisationNumber, BoardEntity board){

        this.number=number;
        this.creationTimestamp=creationTimestamp;
        this.arrivalDate=arrivalDate;
        this.departureDate=departureDate;
        this.billingStreet = billingStreet;
        this.billingHouseNumber=billingHouseNumber;
        this.billingPostalCode=billingPostalCode;
        this.billingCity=billingCity;
        this.billingCountry=billingCountry;
        this.comment=comment;
        this.paymentMethod=paymentMethod;
        this.creditCardNumber=creditCardNumber;
        this.expirationDate=expirationDate;
        this.authorisationNumber=authorisationNumber;
        this.board=board;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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

    public BoardEntity getBoard() {
        return board;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public Integer getPrice_per_night_for_board() {
        return price_per_night_for_board;
    }

    public String getAuthorisationNumber() {
        return authorisationNumber;
    }

    public String getBillingStreet() {
        return billingStreet;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public String getBillingHouseNumber() {
        return billingHouseNumber;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public String getComment() {
        return comment;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setAuthorisationNumber(String authorisationNumber) {
        this.authorisationNumber = authorisationNumber;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public void setBillingHouseNumber(String billingHouseNumber) {
        this.billingHouseNumber = billingHouseNumber;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public void setBoard(BoardEntity board) {
        this.board = board;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setBillingCountry(String country) {
        this.billingCountry = country;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        creditCardNumber = creditCardNumber;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPrice_per_night_for_board(Integer price_per_night_for_board) {
        this.price_per_night_for_board = price_per_night_for_board;
    }
}

