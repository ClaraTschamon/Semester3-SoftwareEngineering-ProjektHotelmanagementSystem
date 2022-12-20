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
    ReservationDTO reservation;
    CustomerDTO customer;
    String roomPrice;
    boolean billingAddressEqualsCustomerAddress;
    ArrayList<CustomerDTO> customers;

    ArrayList<ReservationDTO> reservations;

    public CheckInUseCaseController() throws IOException {
        booking = new BookingDTO();
        reservation = null;
        customer = new CustomerDTO();
        booking.setCustomer(customer);
        booking.setArrivalDate(LocalDate.now());
        //customer.setSaved(true);
        billingAddressEqualsCustomerAddress = true;
        customers = DomainController.getSavedCustomers();
        reservations = DomainController.getAllReservationsBetween(LocalDate.now().minusDays(1), LocalDate.now().plusDays(3));
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

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }

    public ArrayList<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservation(ReservationDTO reservation){
        this.reservation = reservation;

        //Create booking element
        BookingDTO bookingDTO = null;
        if(reservation.getBooking() == null) {
            bookingDTO = new BookingDTO(null, reservation, reservation.getCustomer(), reservation.getArrivalDate(), null,
                    reservation.getDepartureDate(), null, reservation.getBillingAddress(), reservation.getPaymentMethod(),
                    reservation.getCreditCardNumber(), reservation.getExpirationDate(), reservation.getAuthorisationNumber(), reservation.getBoard(),
                    reservation.getPricePerNightForBoard(), reservation.getComment(), reservation.getAmountGuests(), null, null);


            ArrayList<BookedRoomCategoryDTO> bookedRoomCategoryDTOS = new ArrayList<>();

            for (ReservedRoomCategoryDTO reservedRoomCategoryDTO : reservation.getReservedRoomCategories()) {
                BookedRoomCategoryDTO bookedRoomCategoryDTO = new BookedRoomCategoryDTO();
                bookedRoomCategoryDTO.setRoomCategory(reservedRoomCategoryDTO.getRoomCategory());
                bookedRoomCategoryDTO.setAmount(reservedRoomCategoryDTO.getAmount());
                bookedRoomCategoryDTO.setPricePerNight(reservedRoomCategoryDTO.getPricePerNight());
                bookedRoomCategoryDTO.setBooking(bookingDTO);
                bookedRoomCategoryDTOS.add(bookedRoomCategoryDTO);
            }

            ArrayList<BookedRoomDTO> bookedRoomDTOS = new ArrayList<>();

            for (ReservedRoomDTO reservedRoomDTO : reservation.getReservedRooms()) {
                BookedRoomDTO bookedRoomDTO = new BookedRoomDTO();
                bookedRoomDTO.setRoom(reservedRoomDTO.getRoom());
                bookedRoomDTO.getRoom().setNumber(reservedRoomDTO.getRoom().getNumber());
                bookedRoomDTO.setFromDate(reservedRoomDTO.getFromDate());
                bookedRoomDTO.setToDate(reservedRoomDTO.getToDate());
                bookedRoomDTO.setBooking(bookingDTO);
                bookedRoomDTOS.add(bookedRoomDTO);
            }

            bookingDTO.setBookedRoomCategories(bookedRoomCategoryDTOS);
            bookingDTO.setBookedRooms(bookedRoomDTOS);
            bookingDTO.setReservation(reservation);
            reservation.setBooking(bookingDTO);

        }
        setBooking(bookingDTO);
        setCustomer(reservation.getCustomer());
    }

}
