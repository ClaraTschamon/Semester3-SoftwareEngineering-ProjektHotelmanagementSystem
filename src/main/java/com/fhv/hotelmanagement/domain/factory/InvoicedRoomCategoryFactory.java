package com.fhv.hotelmanagement.domain.factory;

import com.fhv.hotelmanagement.domain.domainModel.InvoicedRoomCategory;
import com.fhv.hotelmanagement.view.DTOs.InvoicedRoomCategoryDTO;

import java.util.ArrayList;

public class InvoicedRoomCategoryFactory {

    protected static InvoicedRoomCategory createInvoicedRoomCategory(InvoicedRoomCategoryDTO c) {
        return new InvoicedRoomCategory(InvoiceFactory.createInvoice(c.getInvoice(), false),
                RoomCategoryFactory.createRoomCategory(c.getRoomCategory()),
                c.getPricePerNight(), c.getAmount());
    }

    protected static ArrayList<InvoicedRoomCategory> createInvoicedRoomCategories(ArrayList<InvoicedRoomCategoryDTO> invoicedRoomCategoryDTOS) {
        ArrayList<InvoicedRoomCategory> invoicedRoomCategories = new ArrayList<>();
        for (InvoicedRoomCategoryDTO c : invoicedRoomCategoryDTOS) {
            invoicedRoomCategories.add(createInvoicedRoomCategory(c));
        }
        return invoicedRoomCategories;
    }
}
