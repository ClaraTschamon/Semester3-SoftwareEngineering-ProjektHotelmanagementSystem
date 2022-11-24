package com.fhv.hotelmanagement.view.controller.useCaseController;

import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.domain.exceptions.BookingIsInvalidException;
import com.fhv.hotelmanagement.domain.exceptions.CustomerIsInvalidException;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WalkInUseCaseController {
    BookingDTO booking;
    CustomerDTO customer;
    String roomPrice;
    boolean billingAddressEqualsCustomerAddress;

    public WalkInUseCaseController() throws IOException {
        booking = new BookingDTO();
        customer = new CustomerDTO();
        booking.setCustomer(customer);
        booking.setArrivalDate(LocalDate.now());
        customer.setSaved(true);
        billingAddressEqualsCustomerAddress = true;
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void cancel() throws IOException {
        booking = null;
        customer = null;
    }

    public void save() throws IOException {
        if (booking != null && customer != null) {
            booking.setCheckInDatetime(LocalDateTime.now());
            try {
                Long customerNumber = DomainController.saveCustomer(customer);
                customer.setNumber(customerNumber);
                booking.setCustomer(customer);
            } catch (CustomerIsInvalidException e) {
                System.out.println(e);
            }
            try {
                DomainController.saveBooking(booking);
            } catch (BookingIsInvalidException e) {
                System.out.println(e);
            }
        }
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public boolean isBillingAddressEqualsCustomerAddress() {
        return billingAddressEqualsCustomerAddress;
    }

    public void setBillingAddressEqualsCustomerAddress(boolean billingAddressEqualsCustomerAddress) {
        this.billingAddressEqualsCustomerAddress = billingAddressEqualsCustomerAddress;
    }
}
