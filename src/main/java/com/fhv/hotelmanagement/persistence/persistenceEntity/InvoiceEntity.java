//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "invoice")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_number")
    private Long number;

    @ManyToOne
    @JoinColumn(name = "booking_number")
    private BookingEntity booking;

    @ManyToOne
    @JoinColumn(name = "board_name")
    private BoardEntity board;

    @Column(name = "invoiced_price_per_night_for_board")
    private BigDecimal invoicedPricePerNightForBoard;

    @Column(name = "invoiced_from_date")
    private LocalDate fromDate;

    @Column(name = "invoiced_to_date")
    private LocalDate toDate;

    @OneToMany(
            mappedBy = "invoice",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<InvoicedRoomCategoryEntity> invoicedRoomCategories;

    public InvoiceEntity() {}

    public InvoiceEntity(Long number, BookingEntity booking, BoardEntity board, BigDecimal invoicedPricePerNightForBoard,
                         LocalDate fromDate, LocalDate toDate, Set<InvoicedRoomCategoryEntity> invoicedRoomCategories) {
        this.number = number;
        this.booking = booking;
        this.board = board;
        this.invoicedPricePerNightForBoard = invoicedPricePerNightForBoard;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.invoicedRoomCategories = invoicedRoomCategories;
    }

    public Long getNumber() {
        return number;
    }
}
