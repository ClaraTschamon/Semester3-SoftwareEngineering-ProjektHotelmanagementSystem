package com.fhv.hotelmanagement.view.viewController.useCaseController;

import com.fhv.hotelmanagement.view.DTOs.*;

import java.io.IOException;

public class WalkInUseCaseController {
    BookingDTO booking;
    CustomerDTO customer;
    PackageDTO packageDTO;
    RoomDTO roomDTO;
    AddressDTO addressDTO;

    public WalkInUseCaseController() throws IOException {
        booking = new BookingDTO();
        customer = new CustomerDTO();
        packageDTO = new PackageDTO();
        roomDTO = new RoomDTO();
        addressDTO= new AddressDTO();
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public PackageDTO getPackage(){return packageDTO;}

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void cancel() throws IOException {
        booking = null;
        customer = null;
        packageDTO=null;
        roomDTO=null;
        addressDTO=null;
    }

    public void save() throws IOException {
        if (booking != null && customer != null) {
            // TODO
        }
    }
}
