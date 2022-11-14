package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.view.DTOs.*;


public class DomainController {

    public static boolean saveCustomer(CustomerDTO customerDTO) {
        boolean saved = false;

        if (DomainValidator.checkCustomer(customerDTO)) {
            boolean isNew = customerDTO.getNumber() == null;
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
            boolean isNew = bookingDTO.getNumber() == null;
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
