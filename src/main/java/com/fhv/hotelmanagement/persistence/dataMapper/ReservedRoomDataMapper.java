package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.Reservation;
import com.fhv.hotelmanagement.domain.domainModel.ReservedRoom;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookingEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.CustomerEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.ReservedRoomEntity;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public static ArrayList<ReservedRoom> getAll(){
        ArrayList<ReservedRoomEntity> entities = (ArrayList<ReservedRoomEntity>) PersistenceManager.instance().entityManager.createQuery("from ReservedRoomEntity ").getResultList();
        ArrayList<ReservedRoom> reservedRooms = new ArrayList<>();
        for(ReservedRoomEntity e : entities){
            reservedRooms.add(createReservedRoom(e, ReservationDataMapper.createReservation(e.getReservation())));
        }
        return reservedRooms;
    }

    public static ArrayList<ReservedRoom> getReservedRoomsBetween(LocalDate minDate, LocalDate maxDate){
        ArrayList<ReservedRoomEntity> entities;

        entities = (ArrayList<ReservedRoomEntity>) PersistenceManager.instance().entityManager.createQuery(
                "SELECT reservedRoom FROM ReservedRoomEntity  reservedRoom " +
                        "WHERE (reservedRoom.fromDate < :minimumDate AND :minimumDate <= reservedRoom.toDate)" +
                        "OR (:minimumDate <= reservedRoom.fromDate AND reservedRoom.fromDate <= :maximumDate)")
                .setParameter("minimumDate", minDate)
                .setParameter("maximumDate", maxDate).getResultList();

        ArrayList<ReservedRoom> reservedRooms = new ArrayList<>();
        for(ReservedRoomEntity e : entities){
            reservedRooms.add(createReservedRoom(e, ReservationDataMapper.createReservation(e.getReservation())));
        }
        return reservedRooms;
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
        CustomerEntity customerEntity = CustomerDataMapper.createCustomerEntity(reservation.getCustomer());
        if(reservation.getBooking() == null){
            return new ReservedRoomEntity(
                    ReservationDataMapper.createReservationEntity(reservation, null, customerEntity),
                    RoomDataMapper.createRoomEntity(reservedRoom.getRoom()),
                    reservedRoom.getFromDate(), reservedRoom.getToDate());
        } else {
            BookingEntity bookingEntity = BookingDataMapper.createBookingEntity(reservation.getBooking(), customerEntity);
            return new ReservedRoomEntity(
                    ReservationDataMapper.createReservationEntity(reservation, bookingEntity, customerEntity),
                    RoomDataMapper.createRoomEntity(reservedRoom.getRoom()),
                    reservedRoom.getFromDate(), reservedRoom.getToDate());
        }

    }

    protected static ReservedRoom createReservedRoom(ReservedRoomEntity reservedRoomEntity, Reservation reservation) {
        return new ReservedRoom(reservation, RoomDataMapper.createRoom(reservedRoomEntity.getRoom()), reservedRoomEntity.getFromDate(),
                reservedRoomEntity.getToDate());
    }
}
