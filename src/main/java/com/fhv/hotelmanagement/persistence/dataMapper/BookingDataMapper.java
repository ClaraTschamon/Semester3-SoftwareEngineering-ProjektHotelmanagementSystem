package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.Address;
import com.fhv.hotelmanagement.domain.domainModel.Booking;
import com.fhv.hotelmanagement.domain.domainModel.Customer;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookedRoomCategoryEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookedRoomEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookingEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.CustomerEntity;

import java.util.HashSet;
import java.util.Optional;

public class BookingDataMapper {
    private BookingDataMapper(){}

    private static BookingDataMapper _instance = new BookingDataMapper();

    public static BookingDataMapper instance(){
        return _instance;
    }

    //read
    public Optional<Booking> get(final int number){
        BookingEntity entity = PersistenceFacade.instance().entityManager.find(BookingEntity.class, number);
        if(entity != null){
            Booking booking = new Booking(entity);
            return Optional.of(booking);
        }
        return Optional.empty();
    }

    //create
    public void insert(Booking booking){
        var entityManager = PersistenceFacade.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.persist(booking.getEntity());
        entityManager.getTransaction().commit();
    }

    //update
    public void store(Booking booking){
        PersistenceFacade.instance().entityManager.merge(booking.getEntity());
    }

    protected BookingEntity createBookingEntity(Booking booking) {
        Address address = booking.getBillingAddress();
        return new BookingEntity(booking.getNumber(), CustomerDataMapper.instance().createCustomerEntity(booking.getCustomer()),
                booking.getArrivalDate(), booking.getCheckInDatetime(), booking.getDepartureDate(), booking.getCheckOutDatetime(),
                address.getStreet(), address.getHouseNumber(), address.getPostalCode(), address.getCity(), address.getCountry(),
                new HashSet<BookedRoomCategoryEntity>(), new HashSet<BookedRoomEntity>());
    }

    protected Booking createBooking(BookingEntity bookingEntity) {
        return new Customer(customerEntity.getNumber(), customerEntity.getFirstName(), customerEntity.getLastName(),
                customerEntity.getDateOfBirth(), customerEntity.getNationality(), customerEntity.getPhoneNumber(),
                customerEntity.getEmail(), customerEntity.getStreet(), customerEntity.getHouseNumber(),
                customerEntity.getPostalCode(), customerEntity.getCity(), customerEntity.getCountry(), customerEntity.getSaved());
    }
}
