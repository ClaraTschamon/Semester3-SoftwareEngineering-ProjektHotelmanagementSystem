package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.persistenceEntity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public static ArrayList<Reservation> getAllReservationsBetween(LocalDate minDate, LocalDate maxDate){
        ArrayList<ReservationEntity> entities;
        entities = (ArrayList<ReservationEntity>) PersistenceManager.instance().entityManager.createQuery("" +
                "SELECT reservation FROM ReservationEntity reservation " +
                "WHERE (reservation.arrivalDate <= :minimumDate AND :minimumDate <= reservation.departureDate)" +
                "OR (:minimumDate <= reservation.arrivalDate AND reservation.departureDate <= :maximumDate)")
                .setParameter("minimumDate", minDate)
                .setParameter("maximumDate", maxDate).getResultList();

        ArrayList<Reservation> reservations = new ArrayList<>();
        for(ReservationEntity e : entities){
            reservations.add(createReservation(e));
        }
        return reservations;
    }

    public static ArrayList<Reservation> getNotConfirmedReservations(){
        List<ReservationEntity> entities;
        entities = (ArrayList<ReservationEntity>) PersistenceManager.instance().entityManager.createQuery("" +
                "SELECT reservation FROM ReservationEntity reservation " +
                "WHERE reservation.booking =: param")
                .setParameter("param", null).getResultList();

        /*entities =  PersistenceManager.instance().entityManager.createQuery("" +
                        "SELECT reservation FROM ReservationEntity reservation " +
                        "WHERE reservation.booking IS NULL").getResultList();*/


        ArrayList<Reservation> reservations = new ArrayList<>();
        for(ReservationEntity e : entities){
            reservations.add(createReservation(e));
        }
        return reservations;
    }

    public static ArrayList<Reservation> getConfirmedReservations(){
        ArrayList<ReservationEntity> entities;
        entities = (ArrayList<ReservationEntity>) PersistenceManager.instance().entityManager.createQuery(""+
                "SELECT reservation FROM ReservationEntity reservation " +
                "WHERE reservation.booking IS NOT NULL").getResultList();

        ArrayList<Reservation> reservations = new ArrayList<>();
        for(ReservationEntity e : entities){
            reservations.add(createReservation(e));
        }
        return reservations;
    }

    public Long insert(Reservation reservation) {
        CustomerEntity customerEntity = CustomerDataMapper.createCustomerEntity(reservation.getCustomer());
        ReservationEntity reservationEntity;
        if(reservation.getBooking() == null) {
            reservationEntity = createReservationEntity(reservation, null, customerEntity);
        }else{
            BookingEntity bookingEntity = BookingDataMapper.createBookingEntity(reservation.getBooking(), customerEntity);
            reservationEntity = createReservationEntity(reservation, bookingEntity, customerEntity);
        }
        var entityManager = PersistenceManager.instance().entityManager;

        entityManager.getTransaction().begin();
        entityManager.persist(reservationEntity);
        entityManager.getTransaction().commit();

        Long reservationNumber = reservationEntity.getNumber();
        reservation.setNumber(reservationNumber);

        return reservationNumber;
    }

    public void store(Reservation reservation){
        CustomerEntity customerEntity = CustomerDataMapper.createCustomerEntity(reservation.getCustomer());
        ReservationEntity reservationEntity;
        if(reservation.getBooking() == null) {
            reservationEntity = createReservationEntity(reservation, null, customerEntity);
        }else{
            BookingEntity bookingEntity = BookingDataMapper.createBookingEntity(reservation.getBooking(), customerEntity);
            reservationEntity = createReservationEntity(reservation, bookingEntity, customerEntity);
        }
        var entityManager = PersistenceManager.instance().entityManager;

        entityManager.getTransaction().begin();
        entityManager.merge(reservationEntity);
        entityManager.getTransaction().commit();
    }

    public void delete (Reservation reservation){
        CustomerEntity customerEntity = CustomerDataMapper.createCustomerEntity(reservation.getCustomer());
        ReservationEntity reservationEntity;
        if(reservation.getBooking() == null) {
            reservationEntity = createReservationEntity(reservation, null, customerEntity);
        }else{
            BookingEntity bookingEntity = BookingDataMapper.createBookingEntity(reservation.getBooking(), customerEntity);
            reservationEntity = createReservationEntity(reservation, bookingEntity, customerEntity);
        }
        var entityManager = PersistenceManager.instance().entityManager;


        entityManager.getTransaction().begin();
        entityManager.remove( entityManager.contains(reservationEntity) ? reservation : entityManager.merge(reservationEntity));
        entityManager.getTransaction().commit();
    }

    protected static ReservationEntity createReservationEntity(Reservation reservation, BookingEntity bookingEntity, CustomerEntity customerEntity) {
        Address address = reservation.getBillingAddress();
        HashSet<ReservedRoomCategoryEntity> reservedRoomCategoryEntities = new HashSet<>();
        HashSet<ReservedRoomEntity> reservedRoomEntities = new HashSet<>();

        ReservationEntity reservationEntity = new ReservationEntity(reservation.getNumber(), bookingEntity, customerEntity, reservation.getCreationTimestamp(),
                reservation.getArrivalDate(), reservation.getDepartureDate(), address.getStreet(), address.getHouseNumber(), address.getPostalCode(),
                address.getCity(), address.getCountry(), reservation.getComment(), reservation.getPaymentMethod(), reservation.getCreditCardNumber(),
                reservation.getExpirationDate(), reservation.getAuthorisationNumber(), BoardDataMapper.createBoardEntity(reservation.getBoard()), reservation.getPricePerNightForBoard(),
                reservation.getAmountGuests(), reservedRoomCategoryEntities, reservedRoomEntities);


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
        if(reservationEntity == null){
            return null;
        }
        ArrayList<ReservedRoomCategory> reservedRoomCategories = new ArrayList<>();
        ArrayList<ReservedRoom> reservedRooms = new ArrayList<>();

        Reservation reservation = new Reservation(reservationEntity.getNumber(), null,
                CustomerDataMapper.createCustomer(reservationEntity.getCustomer()),
                reservationEntity.getCreationTimestamp(), reservationEntity.getArrivalDate(), reservationEntity.getDepartureDate(),
                reservationEntity.getBillingStreet(), reservationEntity.getBillingHouseNumber(), reservationEntity.getBillingPostalCode(),
                reservationEntity.getBillingCity(), reservationEntity.getBillingCountry(), reservationEntity.getComment(),
                reservationEntity.getPaymentMethod(), reservationEntity.getCreditCardNumber(), reservationEntity.getExpirationDate(),
                reservationEntity.getAuthorisationNumber(), BoardDataMapper.createBoard(reservationEntity.getBoard()),
                reservationEntity.getPricePerNightForBoard(), reservationEntity.getAmountGuests(), reservedRoomCategories, reservedRooms);

        Booking booking;
        if (reservationEntity.getBooking() == null){
            booking = null;
        } else{
            booking = BookingDataMapper.createBooking(reservationEntity.getBooking(), reservation);
        }
        reservation.setBooking(booking);

        for (ReservedRoomCategoryEntity e : reservationEntity.getReservedRoomCategories()) {
            reservedRoomCategories.add(ReservedRoomCategoryDataMapper.createReservedRoomCategory(e, reservation));
        }
        for (ReservedRoomEntity e : reservationEntity.getReservedRooms()) {
            reservedRooms.add(ReservedRoomDataMapper.createReservedRoom(e, reservation));
        }

        return reservation;
    }
}
