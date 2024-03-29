//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.DTOs;

import java.math.BigDecimal;

public class InvoicedRoomCategoryDTO {
    private InvoiceDTO invoice;
    private RoomCategoryDTO roomCategory;
    private BigDecimal pricePerNight;
    private int amount;

    public InvoicedRoomCategoryDTO(InvoiceDTO invoice, RoomCategoryDTO roomCategory, BigDecimal pricePerNight, int amount) {
        this.invoice = invoice;
        this.roomCategory = roomCategory;
        this.pricePerNight = pricePerNight;
        this.amount = amount;
    }

    public InvoiceDTO getInvoice() {
        return invoice;
    }

    public RoomCategoryDTO getRoomCategory() {
        return roomCategory;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public int getAmount() {
        return amount;
    }
}
