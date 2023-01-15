//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.factory;

import com.fhv.hotelmanagement.domain.domainModel.Invoice;
import com.fhv.hotelmanagement.domain.domainModel.InvoicedRoomCategory;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.view.DTOs.InvoiceDTO;

import java.util.ArrayList;

public class InvoiceFactory {

    public static Long saveInvoice(InvoiceDTO invoiceDTO) {
        if (checkInvoice(invoiceDTO)) {
            Invoice invoice = createInvoice(invoiceDTO, true);

            return PersistenceFacade.insertInvoice(invoice);
        }
        return null;
    }

    protected static Invoice createInvoice(InvoiceDTO invoiceDTO, boolean fillArrays) {
        ArrayList<InvoicedRoomCategory> invoicedRoomCategories = new ArrayList<>();
        if (fillArrays) {
            invoicedRoomCategories = InvoicedRoomCategoryFactory.createInvoicedRoomCategories(invoiceDTO.getInvoicedRoomCategories());
        }
        return new Invoice(invoiceDTO.getNumber(), BookingFactory.createBooking(invoiceDTO.getBooking(), ReservationFactory.createReservation(invoiceDTO.getBooking().getReservation(), true), false),
                BoardFactory.createBoard(invoiceDTO.getBoard()), invoiceDTO.getPricePerNightForBoard(), invoiceDTO.getFromDate(),
                invoiceDTO.getToDate(), invoicedRoomCategories);

    }

    public static boolean checkInvoice(InvoiceDTO invoiceDTO) {
        return true; // TODO
    }
}
