package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.util.ArrayList;


public class DomainController {

    public static ArrayList<RoomDTO> getRooms() {
        ArrayList<RoomDTO> rooms = new ArrayList<>();
        for (RoomDTO r : MainApplication.getDomainManager().getAllRooms()) {
            System.out.println(r.getNumber());
        }

        return rooms;
    }

    public static boolean saveCustomer(CustomerDTO customerDTO) {
        boolean saved = false;

        if (DomainValidator.checkCustomer(customerDTO)) {
            boolean isNew = customerDTO.getNumber().equals(null);
            Customer customer = DomainCreator.createCustomer(customerDTO);

            if (isNew) {
                PersistenceFacade.insertCustomer(customer);
            } else {
                PersistenceFacade.storeCustomer(customer);
            }
            saved = true;
        }
        return saved;
    }

    public static boolean saveBooking(BookingDTO bookingDTO) {
        boolean saved = false;
        if (DomainValidator.checkBooking(bookingDTO)) {
            boolean isNew = bookingDTO.getNumber().equals(null);
            Booking booking = DomainCreator.createBooking(bookingDTO);

            if (isNew) {
                PersistenceFacade.insertBooking(booking);
            } else {
                PersistenceFacade.storeBooking(booking);
            }
            saved = true;
        }
        return saved;
    }
}
