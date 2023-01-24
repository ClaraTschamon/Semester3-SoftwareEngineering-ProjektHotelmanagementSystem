//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "reservation")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_number")
    private Long number;

    @OneToOne(mappedBy = "reservation")
    private BookingEntity booking;

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
    private BigDecimal pricePerNightForBoard;

    @Column(name="amount_guests")
    private Integer amountGuests;

    @OneToMany(
            mappedBy = "reservation",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<ReservedRoomCategoryEntity> reservedRoomCategories;

    @OneToMany(
            mappedBy = "reservation",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<ReservedRoomEntity> reservedRooms;



    public ReservationEntity(){}

    public ReservationEntity(Long number, BookingEntity booking, CustomerEntity customer,
                             LocalDateTime creationTimestamp, LocalDate arrivalDate, LocalDate departureDate,
                             String billingStreet, String billingHouseNumber, String billingPostalCode,
                             String billingCity, String billingCountry, String comment, String paymentMethod,
                             String creditCardNumber, String expirationDate, String authorisationNumber, BoardEntity board,
                             BigDecimal pricePerNightForBoard, Integer amountGuests, Set<ReservedRoomCategoryEntity> reservedRoomCategories,
                             Set<ReservedRoomEntity> reservedRooms) {
        this.number = number;
        this.booking = booking;
        this.customer = customer;
        this.creationTimestamp = creationTimestamp;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.billingStreet = billingStreet;
        this.billingHouseNumber = billingHouseNumber;
        this.billingPostalCode = billingPostalCode;
        this.billingCity = billingCity;
        this.billingCountry = billingCountry;
        this.comment = comment;
        this.paymentMethod = paymentMethod;
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.authorisationNumber = authorisationNumber;
        this.board = board;
        this.pricePerNightForBoard = pricePerNightForBoard;
        this.amountGuests = amountGuests;
        this.reservedRoomCategories = reservedRoomCategories;
        this.reservedRooms = reservedRooms;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
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

    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public String getBillingHouseNumber() {
        return billingHouseNumber;
    }

    public void setBillingHouseNumber(String billingHouseNumber) {
        this.billingHouseNumber = billingHouseNumber;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
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

    public BoardEntity getBoard() {
        return board;
    }

    public void setBoard(BoardEntity board) {
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

    public Set<ReservedRoomCategoryEntity> getReservedRoomCategories() {
        return reservedRoomCategories;
    }

    public void setReservedRoomCategories(Set<ReservedRoomCategoryEntity> reservedRoomCategories) {
        this.reservedRoomCategories = reservedRoomCategories;
    }

    public Set<ReservedRoomEntity> getReservedRooms() {
        return reservedRooms;
    }

    public void setReservedRooms(Set<ReservedRoomEntity> reservedRooms) {
        this.reservedRooms = reservedRooms;
    }
}