package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.util.ArrayList;


public class DomainController {

    public static boolean saveCustomer(CustomerDTO customerDTO) {
        boolean saved = false;

        if (DomainValidator.checkCustomer(customerDTO)) {
            boolean isNew = false;
            if (customerDTO.getNumber() == null) {
                isNew = true;
            }
            Customer customer = DomainCreator.createCustomer(customerDTO);

            if (isNew) {
                PersistenceFacade.insertCustomer(customer);
            } else {
                PersistenceFacade.storeCustomer(customer);
            }
            System.out.println("saved customer");
            saved = true;
        }
        return saved;
    }

    public static boolean saveBooking(BookingDTO bookingDTO) {
        boolean saved = false;
        if (DomainValidator.checkBooking(bookingDTO)) {
            boolean isNew = false;
            if (bookingDTO.getNumber() == null) {
                isNew = true;
            }
            Booking booking = DomainCreator.createBooking(bookingDTO);

            if (isNew) {
                PersistenceFacade.insertBooking(booking);
            } else {
                PersistenceFacade.storeBooking(booking);
            }
            System.out.println("saved booking");
            saved = true;
        }
        return saved;
    }
}
