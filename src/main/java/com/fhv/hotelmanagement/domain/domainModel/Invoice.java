//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.domainModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Invoice {
    private Long number;
    private Booking booking;
    private Board board;
    private BigDecimal pricePerNightForBoard;
    private LocalDate fromDate;
    private LocalDate toDate;
    private ArrayList<InvoicedRoomCategory> invoicedRoomCategories;

    public Invoice(Long number, Booking booking, Board board, BigDecimal pricePerNightForBoard,
                   LocalDate fromDate, LocalDate toDate, ArrayList<InvoicedRoomCategory> invoicedRoomCategories) {
        this.number = number;
        this.booking = booking;
        this.board = board;
        this.pricePerNightForBoard = pricePerNightForBoard;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.invoicedRoomCategories = invoicedRoomCategories;
    }

    public Booking getBooking() {
        return booking;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        if (this.number == null) {
            this.number = number;
        }
    }

    public Board getBoard() {
        return board;
    }

    public BigDecimal getPricePerNightForBoard() {
        return pricePerNightForBoard;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public ArrayList<InvoicedRoomCategory> getInvoicedRoomCategories() {
        return invoicedRoomCategories;
    }
}
