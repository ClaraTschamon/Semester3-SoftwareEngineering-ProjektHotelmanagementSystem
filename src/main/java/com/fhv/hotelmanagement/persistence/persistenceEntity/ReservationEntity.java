package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

import java.time.LocalDate;

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

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column (name = "departure_date")
    private LocalDate departureDate;

}
