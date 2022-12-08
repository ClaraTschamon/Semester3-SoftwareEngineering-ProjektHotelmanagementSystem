package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.Reservation;
import com.fhv.hotelmanagement.domain.domainModel.ReservedRoom;
import com.fhv.hotelmanagement.persistence.persistenceEntity.ReservedRoomEntity;

import java.util.Optional;

public class ReservedRoomDataMapper {
    private ReservedRoomDataMapper(){}

    private static ReservedRoomDataMapper _instance = new ReservedRoomDataMapper();

    public static ReservedRoomDataMapper instance(){return _instance;}

    protected Optional<ReservedRoom> get(final Reservation reservation){
        ReservedRoomEntity entity = PersistenceManager.instance().entityManager.find(ReservedRoomEntity.class, reservation);
        if(entity != null){
            ReservedRoom reservedRoom = createReservedRoom(entity, reservation);
            return Optional.of(reservedRoom);
        }
        return Optional.empty();
    }

    protected void insert(ReservedRoom reservedRoom){
        var entityManager = PersistenceManager.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.persist(createReservedRoomEntity(reservedRoom));
        entityManager.getTransaction().commit();
    }


    protected void store(ReservedRoom reservedRoom){
        var entityManager = PersistenceManager.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.merge(createReservedRoomEntity(reservedRoom));
        entityManager.getTransaction().commit();
    }

    protected static ReservedRoomEntity createReservedRoomEntity(ReservedRoom reservedRoom) {
        Reservation reservation = reservedRoom.getReservation();
        return new ReservedRoomEntity(ReservationDataMapper.createReservationEntity(reservation,
                CustomerDataMapper.createCustomerEntity(reservation.getCustomer())),
                RoomDataMapper.createRoomEntity(reservedRoom.getRoom()),
                reservedRoom.getFromDate(), reservedRoom.getToDate());
    }

    protected static ReservedRoom createReservedRoom(ReservedRoomEntity reservedRoomEntity, Reservation reservation) {
        return new ReservedRoom(reservation, RoomDataMapper.createRoom(reservedRoomEntity.getRoom()), reservedRoomEntity.getFromDate(),
                reservedRoomEntity.getToDate());
    }
}
