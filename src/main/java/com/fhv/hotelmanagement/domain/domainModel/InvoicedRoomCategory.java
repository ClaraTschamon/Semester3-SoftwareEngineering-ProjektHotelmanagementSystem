//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.domainModel;

import java.math.BigDecimal;

public class InvoicedRoomCategory {
    private Invoice invoice;
    private RoomCategory roomCategory;
    private BigDecimal pricePerNight;
    private int amount;

    public InvoicedRoomCategory(Invoice invoice, RoomCategory roomCategory, BigDecimal pricePerNight, int amount) {
        this.invoice = invoice;
        this.roomCategory = roomCategory;
        this.pricePerNight = pricePerNight;
        this.amount = amount;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public int getAmount() {
        return amount;
    }
}
