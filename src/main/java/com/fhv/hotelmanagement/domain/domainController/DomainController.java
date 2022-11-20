package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.domain.exceptions.BookingIsInvalidException;
import com.fhv.hotelmanagement.domain.exceptions.CustomerIsInvalidException;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.view.DTOs.*;


public class DomainController {

    public static Long saveCustomer(CustomerDTO customerDTO) throws CustomerIsInvalidException {
        Long customerNumber = customerDTO.getNumber();
        if (DomainValidator.checkCustomer(customerDTO)) {
            Customer customer = DomainCreator.createCustomer(customerDTO);

            if (customerNumber == null) {
                customerNumber = PersistenceFacade.insertCustomer(customer);
            } else {
                PersistenceFacade.storeCustomer(customer);
            }
        } else {
            throw new CustomerIsInvalidException();
        }
        return customerNumber;
    }

    public static Long saveBooking(BookingDTO bookingDTO) throws BookingIsInvalidException {
        Long bookingNumber = bookingDTO.getNumber();
        if (DomainValidator.checkBooking(bookingDTO)) {
            Booking booking = DomainCreator.createBooking(bookingDTO, true);
            if (bookingNumber == null) {
                bookingNumber = PersistenceFacade.insertBooking(booking);
            } else {
                PersistenceFacade.storeBooking(booking);
            }
            System.out.println("saved booking");
        } else {
            throw new BookingIsInvalidException();
        }
        return bookingNumber;
    }

    public static Long saveInvoice(InvoiceDTO invoiceDTO) {
        if (DomainValidator.checkInvoice(invoiceDTO)) {
            Invoice invoice = DomainCreator.createInvoice(invoiceDTO, true);

            return PersistenceFacade.insertInvoice(invoice);

        }
        return null;
    }
}
