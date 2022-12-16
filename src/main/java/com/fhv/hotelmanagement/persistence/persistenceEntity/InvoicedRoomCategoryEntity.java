//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "invoiced_room_category")
public class InvoicedRoomCategoryEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "invoice_number")
    private InvoiceEntity invoice;

    @Id
    @ManyToOne
    @JoinColumn(name = "invoiced_room_category")
    private RoomCategoryEntity roomCategory;

    @Column(name = "invoiced_room_category_price_per_night")
    private BigDecimal pricePerNight;

    @Column(name = "invoiced_room_category_amount")
    private int amount;

    public InvoicedRoomCategoryEntity() {}
    public InvoicedRoomCategoryEntity(InvoiceEntity invoice, RoomCategoryEntity roomCategory, BigDecimal pricePerNight, int amount) {
        this.invoice = invoice;
        this.roomCategory = roomCategory;
        this.pricePerNight = pricePerNight;
        this.amount = amount;
    }

    public InvoiceEntity getInvoice() {
        return invoice;
    }

    public RoomCategoryEntity getRoomCategory() {
        return roomCategory;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
