//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

@Entity
@Table(name="reserved_room")
public class ReservedRoomEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "reservation_number")
    private ReservationEntity reservation;

}
