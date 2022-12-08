package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.persistenceEntity.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

public class ReservationDataMapper {

    private ReservationDataMapper(){}

    private static ReservationDataMapper _instance = new ReservationDataMapper();

    public static ReservationDataMapper instance(){return _instance;}

    public Optional<Reservation> get(final Long number){
        ReservationEntity entity = PersistenceManager.instance().entityManager.find(ReservationEntity.class, number);
        if(entity != null){
            Reservation reservation = createReservation(entity);
            return Optional.of(reservation);
        }
        return Optional.empty();
    }

    public static ArrayList<Reservation> getAll(){
        ArrayList<ReservationEntity> entities = (ArrayList<ReservationEntity>) PersistenceManager.instance().entityManager.createQuery("from ReservationEntity ").getResultList();
        ArrayList<Reservation> reservations = new ArrayList<>();
        for(ReservationEntity r : entities){
            reservations.add(createReservation(r));
        }
        return reservations;
    }

    public Long insert(Reservation reservation) {
        ReservationEntity reservationEntity = createReservationEntity(reservation, CustomerDataMapper.createCustomerEntity(reservation.getCustomer()));
        var entityManager = PersistenceManager.instance().entityManager;

        entityManager.getTransaction().begin();
        entityManager.persist(reservationEntity);
        entityManager.getTransaction().commit();

        Long reservationNumber = reservationEntity.getNumber();
        reservation.setNumber(reservationNumber);

        return reservationNumber;
    }

    public void store(Reservation reservation){
        ReservationEntity reservationEntity = createReservationEntity(reservation, CustomerDataMapper.createCustomerEntity(reservation.getCustomer()));
        var entityManager = PersistenceManager.instance().entityManager;

        entityManager.getTransaction().begin();
        entityManager.merge(reservationEntity);
        entityManager.getTransaction().commit();
    }

    protected static ReservationEntity createReservationEntity(Reservation reservation, CustomerEntity customerEntity) {
        Address address = reservation.getBillingAddress();
        HashSet<ReservedRoomCategoryEntity> reservedRoomCategoryEntities = new HashSet<>();
        HashSet<ReservedRoomEntity> reservedRoomEntities = new HashSet<>();
        HashSet<BookingEntity> bookings = new HashSet<>();

        ReservationEntity reservationEntity = new ReservationEntity(reservation.getNumber(), customerEntity, reservation.getCreationTimestamp(),
                reservation.getArrivalDate(), reservation.getDepartureDate(), address.getStreet(), address.getHouseNumber(), address.getPostalCode(),
                address.getCity(), address.getCountry(), reservation.getComment(), reservation.getPaymentMethod(), reservation.getCreditCardNumber(),
                reservation.getExpirationDate(), reservation.getAuthorisationNumber(), BoardDataMapper.createBoardEntity(reservation.getBoard()), reservation.getPricePerNightForBoard(),
                reservation.getAmountGuests(), reservedRoomCategoryEntities, reservedRoomEntities, bookings);

        for (ReservedRoomCategory c : reservation.getReservedRoomCategories()) {
            reservedRoomCategoryEntities.add(new ReservedRoomCategoryEntity(reservationEntity, RoomCategoryDataMapper.createRoomCategoryEntity(c.getRoomCategory()),
                    c.getPricePerNight(), c.getAmount()));

        }
        for (ReservedRoom r : reservation.getReservedRooms()) {
            reservedRoomEntities.add(new ReservedRoomEntity(reservationEntity, RoomDataMapper.createRoomEntity(r.getRoom()), r.getFromDate(), r.getToDate()));
        }

        return reservationEntity;
    }

    protected static Reservation createReservation(ReservationEntity reservationEntity) {
        ArrayList<ReservedRoomCategory> reservedRoomCategories = new ArrayList<>();
        ArrayList<ReservedRoom> reservedRooms = new ArrayList<>();

        Reservation reservation = new Reservation(reservationEntity.getNumber(), CustomerDataMapper.createCustomer(reservationEntity.getCustomer()),
                reservationEntity.getCreationTimestamp(), reservationEntity.getArrivalDate(), reservationEntity.getDepartureDate(),
                reservationEntity.getBillingStreet(), reservationEntity.getBillingHouseNumber(), reservationEntity.getBillingPostalCode(),
                reservationEntity.getBillingCity(), reservationEntity.getBillingCountry(), reservationEntity.getComment(),
                reservationEntity.getPaymentMethod(), reservationEntity.getCreditCardNumber(), reservationEntity.getExpirationDate(),
                reservationEntity.getAuthorisationNumber(), BoardDataMapper.createBoard(reservationEntity.getBoard()),
                reservationEntity.getPricePerNightForBoard(), reservationEntity.getAmountGuests(), reservedRoomCategories, reservedRooms);

        for (ReservedRoomCategoryEntity e : reservationEntity.getReservedRoomCategories()) {
            reservedRoomCategories.add(ReservedRoomCategoryDataMapper.createReservedRoomCategory(e, reservation));
        }
        for (ReservedRoomEntity e : reservationEntity.getReservedRooms()) {
            reservedRooms.add(ReservedRoomDataMapper.createReservedRoom(e, reservation));
        }

        return reservation;
    }
}
