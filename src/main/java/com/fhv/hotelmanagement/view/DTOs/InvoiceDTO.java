package com.fhv.hotelmanagement.view.DTOs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class InvoiceDTO {
    private Long number;
    private BookingDTO booking;
    private BoardDTO board;
    private BigDecimal pricePerNightForBoard;
    private LocalDate fromDate;
    private LocalDate toDate;
    private ArrayList<InvoicedRoomCategoryDTO> invoicedRoomCategories;

    public InvoiceDTO(Long number, BookingDTO booking, BoardDTO board, BigDecimal pricePerNightForBoard,
                   LocalDate fromDate, LocalDate toDate, ArrayList<InvoicedRoomCategoryDTO> invoicedRoomCategories) {
        this.number = number;
        this.booking = booking;
        this.board = board;
        this.pricePerNightForBoard = pricePerNightForBoard;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.invoicedRoomCategories = invoicedRoomCategories;
    }

    public InvoiceDTO(BookingDTO booking) {
        this.number = null;
        this.booking = booking;
        this.board = booking.getBoard();
        this.pricePerNightForBoard = booking.getPricePerNightForBoard();
        this.fromDate = booking.getArrivalDate();
        this.toDate = booking.getDepartureDate();

        ArrayList<InvoicedRoomCategoryDTO> invoicedRoomCategoryDTOS = new ArrayList<>();
        for (BookedRoomCategoryDTO c : booking.getBookedRoomCategories()) {
            invoicedRoomCategoryDTOS.add(new InvoicedRoomCategoryDTO(this, c.getRoomCategory(),
                    c.getPricePerNight(), c.getAmount()));
        }
        this.invoicedRoomCategories = invoicedRoomCategoryDTOS;
    }

    public Long getNumber() {
        return number;
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public BoardDTO getBoard() {
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

    public ArrayList<InvoicedRoomCategoryDTO> getInvoicedRoomCategories() {
        return invoicedRoomCategories;
    }
}
