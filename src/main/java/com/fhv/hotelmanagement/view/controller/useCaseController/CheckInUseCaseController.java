//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.useCaseController;

import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.domain.exceptions.BookingIsInvalidException;
import com.fhv.hotelmanagement.domain.exceptions.CustomerIsInvalidException;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CheckInUseCaseController {
    BookingDTO booking;

    CustomerDTO customer;
    String roomPrice;
    boolean billingAddressEqualsCustomerAddress;
    ArrayList<CustomerDTO> customers;
    ReservationDTO reservation;

    public CheckInUseCaseController() throws IOException {
        booking = new BookingDTO();
        customer = new CustomerDTO();
        booking.setCustomer(customer);
        booking.setArrivalDate(LocalDate.now());
        //customer.setSaved(true);
        billingAddressEqualsCustomerAddress = true;
        customers = DomainController.getSavedCustomers();
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public ArrayList<CustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public void cancel() throws IOException {
        booking = null;
        customer = null;
    }

    public void save() throws IOException, BookingIsInvalidException, CustomerIsInvalidException {
        if (booking != null && customer != null) {
            booking.setCheckInDatetime(LocalDateTime.now());
            Long customerNumber = DomainController.saveCustomer(customer);
            customer.setNumber(customerNumber);
            booking.setCustomer(customer);
            DomainController.saveBooking(booking);
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

    public ReservationDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDTO reservation) {
        this.reservation = reservation;
    }

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }
}
