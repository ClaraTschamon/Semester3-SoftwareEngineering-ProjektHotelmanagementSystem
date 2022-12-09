//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.Address;
import com.fhv.hotelmanagement.domain.domainModel.Booking;
import com.fhv.hotelmanagement.domain.domainModel.Customer;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookingEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.CustomerEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.ReservationEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

public class CustomerDataMapper{

    private CustomerDataMapper(){}
    private static CustomerDataMapper _instance = new CustomerDataMapper();
    public static CustomerDataMapper instance(){
        return _instance;
    }

    //read
    public Optional<Customer> get(final Long number){
        CustomerEntity entity = PersistenceManager.instance().entityManager.find(CustomerEntity.class, number);
        if(entity != null){
            Customer customer = createCustomer(entity);
            return Optional.of(customer);
        }
        return Optional.empty();
    }

    public static ArrayList<Customer> getSavedCustomers(){
        ArrayList<CustomerEntity> entities;

        entities = (ArrayList<CustomerEntity>) PersistenceManager.instance().entityManager.createQuery(
                "SELECT customer FROM CustomerEntity customer WHERE customer.saved = true"
        ).getResultList();

        ArrayList<Customer> customers = new ArrayList<>();
        for(CustomerEntity e : entities){
            customers.add(createCustomer(e));
        }
        return customers;
    }

    //create
    public Long insert(Customer customer){
        CustomerEntity customerEntity = createCustomerEntity(customer);
        var entityManager = PersistenceManager.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.persist(customerEntity);
        entityManager.getTransaction().commit();
        return customerEntity.getNumber();
    }

    //update
    public void store(Customer customer){
        CustomerEntity customerEntity = createCustomerEntity(customer);
        var entityManager = PersistenceManager.instance().entityManager;

        entityManager.getTransaction().begin();
        entityManager.merge(customerEntity);
        entityManager.getTransaction().commit();
    }

    protected static CustomerEntity createCustomerEntity(Customer customer) {
        Address address = customer.getAddress();
        HashSet<BookingEntity> bookingEntities = new HashSet<>();
        HashSet<ReservationEntity> reservationEntities = new HashSet<>();

        CustomerEntity customerEntity = new CustomerEntity(customer.getNumber(), customer.getFirstName(), customer.getLastName(), customer.getDateOfBirth(),
                customer.getNationality(), customer.getPhoneNumber(), customer.getEmail(), address.getStreet(), address.getHouseNumber(),
                address.getPostalCode(), address.getCity(), address.getCountry(), customer.getSaved(), bookingEntities, reservationEntities);

//        for (Booking b : customer.getBookings()) {
//            bookingEntities.add(BookingDataMapper.createBookingEntity(b, customerEntity));
//        }

        return customerEntity;
    }

    protected static Customer createCustomer(CustomerEntity customerEntity) {
        ArrayList<Booking> bookings = new ArrayList<>();

        Customer customer = new Customer(customerEntity.getNumber(), customerEntity.getFirstName(), customerEntity.getLastName(),
                customerEntity.getDateOfBirth(), customerEntity.getNationality(), customerEntity.getPhoneNumber(),
                customerEntity.getEmail(), customerEntity.getStreet(), customerEntity.getHouseNumber(),
                customerEntity.getPostalCode(), customerEntity.getCity(), customerEntity.getCountry(), customerEntity.getSaved(),
                bookings);

//        for (BookingEntity e : customerEntity.getBookings()) {
//            bookings.add(BookingDataMapper.createBooking(e));
//        }

        return customer;
    }
}
