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

    protected static BookingEntity createReservationEntity(Reservation reservation, CustomerEntity customerEntity) {
        Address address = reservation.getBillingAddress();
        HashSet<ReservedRoomCategoryEntity> reservedRoomCategoryEntities = new HashSet<>();
        HashSet<ReservedRoomEntity> reservedRoomEntities = new HashSet<>();

        ReservationEntity reservationEntity = new ReservationEntity(reservation.getNumber(), customerEntity,
                reservation.getArrivalDate(), reservation.getDepartureDate(),
                address.getStreet(), address.getHouseNumber(), address.getPostalCode(), address.getCity(), address.getCountry(),
                reservation.getComment(), reservation.getPaymentMethod(), reservation.getCreditCardNumber(), reservation.getExpirationDate(),
                reservation.getAuthorisationNumber(), BoardDataMapper.createBoardEntity(reservation.getBoard()), reservation.getPricePerNightForBoard(),
                reservation.getAmountGuests(), reservedRoomCategoryEntities, reservedRoomEntities);

        for (BookedRoomCategory c : reservation.getBookedRoomCategories()) {
            reservedRoomCategoryEntities.add(new BookedRoomCategoryEntity(reservationEntity, RoomCategoryDataMapper.createRoomCategoryEntity(c.getRoomCategory()),
                    c.getPricePerNight(), c.getAmount()));

        }
        for (BookedRoom b : reservation.getBookedRooms()) {
            reservedRoomEntities.add(new BookedRoomEntity(reservationEntity, RoomDataMapper.createRoomEntity(b.getRoom()), b.getFromDate(), b.getToDate()));
        }

        return reservationEntity;
    }

    protected static Booking createBooking(BookingEntity bookingEntity) {
        ArrayList<BookedRoomCategory> bookedRoomCategories = new ArrayList<>();
        ArrayList<BookedRoom> bookedRooms = new ArrayList<>();

        Booking booking = new Booking(bookingEntity.getNumber(), CustomerDataMapper.createCustomer(bookingEntity.getCustomer()),
                bookingEntity.getArrivalDate(), bookingEntity.getCheckInDatetime(), bookingEntity.getDepartureDate(),
                bookingEntity.getCheckOutDatetime(), bookingEntity.getBillingStreet(), bookingEntity.getBillingHouseNumber(),
                bookingEntity.getBillingPostalCode(), bookingEntity.getBillingCity(), bookingEntity.getBillingCountry(),
                bookingEntity.getComment(), bookingEntity.getPaymentMethod(), bookingEntity.getCreditCardNumber(),
                bookingEntity.getExpirationDate(), bookingEntity.getAuthorisationNumber(), BoardDataMapper.createBoard(bookingEntity.getBoard()),
                bookingEntity.getPricePerNightForBoard(), bookingEntity.getAmountGuests(), bookedRoomCategories, bookedRooms);

        for (BookedRoomCategoryEntity e : bookingEntity.getBookedRoomCategories()) {
            bookedRoomCategories.add(BookedRoomCategoryDataMapper.createBookedRoomCategory(e, booking));
        }
        for (BookedRoomEntity e : bookingEntity.getBookedRooms()) {
            bookedRooms.add(BookedRoomDataMapper.createBookedRoom(e, booking));
        }

        return booking;
    }

}
