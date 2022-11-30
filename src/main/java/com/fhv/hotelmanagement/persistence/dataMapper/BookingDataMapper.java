//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.persistenceEntity.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

public class BookingDataMapper {
    private BookingDataMapper(){}

    private static BookingDataMapper _instance = new BookingDataMapper();

    public static BookingDataMapper instance(){
        return _instance;
    }

    //read
    public Optional<Booking> get(final Long number){
        BookingEntity entity = PersistenceManager.instance().entityManager.find(BookingEntity.class, number);
        if(entity != null){
            Booking booking = createBooking(entity);
            return Optional.of(booking);
        }
        return Optional.empty();
    }

    public static ArrayList<Booking> getAll(){
        ArrayList<BookingEntity> entities = (ArrayList<BookingEntity>) PersistenceManager.instance().entityManager.createQuery("from BookingEntity").getResultList();
        ArrayList<Booking> bookings = new ArrayList<>();
        for(BookingEntity b : entities){
            bookings.add(createBooking(b));
        }
        return bookings;
    }

    //create
    public Long insert(Booking booking) {
        BookingEntity bookingEntity = createBookingEntity(booking, CustomerDataMapper.createCustomerEntity(booking.getCustomer()));
        var entityManager = PersistenceManager.instance().entityManager;

        entityManager.getTransaction().begin();
        entityManager.persist(bookingEntity);
        entityManager.getTransaction().commit();

        Long bookingNumber = bookingEntity.getNumber();
        booking.setNumber(bookingNumber);

        return bookingNumber;
    }

    //update
    public void store(Booking booking){
        BookingEntity bookingEntity = createBookingEntity(booking, CustomerDataMapper.createCustomerEntity(booking.getCustomer()));
        var entityManager = PersistenceManager.instance().entityManager;

        entityManager.getTransaction().begin();
        entityManager.merge(bookingEntity);
        entityManager.getTransaction().commit();
    }

    protected static BookingEntity createBookingEntity(Booking booking, CustomerEntity customerEntity) {
        Address address = booking.getBillingAddress();
        HashSet<BookedRoomCategoryEntity> bookedRoomCategoryEntities = new HashSet<>();
        HashSet<BookedRoomEntity> bookedRoomEntities = new HashSet<>();

        BookingEntity bookingEntity = new BookingEntity(booking.getNumber(), customerEntity,
                booking.getArrivalDate(), booking.getCheckInDatetime(), booking.getDepartureDate(), booking.getCheckOutDatetime(),
                address.getStreet(), address.getHouseNumber(), address.getPostalCode(), address.getCity(), address.getCountry(),
                booking.getComment(), booking.getPaymentMethod(), booking.getCreditCardNumber(), booking.getExpirationDate(),
                booking.getAuthorisationNumber(), BoardDataMapper.createBoardEntity(booking.getBoard()), booking.getPricePerNightForBoard(),
                booking.getAmountGuests(), bookedRoomCategoryEntities, bookedRoomEntities);

        for (BookedRoomCategory c : booking.getBookedRoomCategories()) {
            bookedRoomCategoryEntities.add(new BookedRoomCategoryEntity(bookingEntity, RoomCategoryDataMapper.createRoomCategoryEntity(c.getRoomCategory()),
                    c.getPricePerNight(), c.getAmount()));

        }
        for (BookedRoom b : booking.getBookedRooms()) {
            bookedRoomEntities.add(new BookedRoomEntity(bookingEntity, RoomDataMapper.createRoomEntity(b.getRoom()), b.getFromDate(), b.getToDate()));
        }

        return bookingEntity;
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
