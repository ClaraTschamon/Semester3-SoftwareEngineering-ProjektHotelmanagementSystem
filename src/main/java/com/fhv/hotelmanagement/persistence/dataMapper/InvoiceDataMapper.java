package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.Booking;
import com.fhv.hotelmanagement.domain.domainModel.Invoice;
import com.fhv.hotelmanagement.domain.domainModel.InvoicedRoomCategory;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.InvoiceEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.InvoicedRoomCategoryEntity;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.HashSet;

public class InvoiceDataMapper {
    private static InvoiceDataMapper _instance = new InvoiceDataMapper();

    public static InvoiceDataMapper instance(){
        return _instance;
    }

    //create
    public Long insert(Invoice invoice) {
        InvoiceEntity invoiceEntity = createInvoiceEntity(invoice);
        var entityManager = PersistenceFacade.instance().entityManager;

        entityManager.getTransaction().begin();
        entityManager.persist(invoiceEntity);
        entityManager.getTransaction().commit();

        Long invoiceNumber = invoiceEntity.getNumber();

        for (InvoicedRoomCategory c : invoice.getInvoicedRoomCategories()) {
            c.getInvoice().setNumber(invoiceNumber);
            InvoicedRoomCategoryDataMapper.instance().insert(c);
        }
        return invoiceNumber;
    }

    public void store (Invoice invoice){
        InvoiceEntity invoiceEntity = createInvoiceEntity(invoice);
        var entityManager = PersistenceFacade.instance().entityManager;

        entityManager.getTransaction().begin();
        entityManager.merge(invoiceEntity);
        entityManager.getTransaction().commit();
    }

    protected static InvoiceEntity createInvoiceEntity(Invoice invoice) {
        Booking booking = invoice.getBooking();
        HashSet<InvoicedRoomCategoryEntity> invoicedRoomCategoryEntities = new HashSet<>();
        InvoiceEntity invoiceEntity = new InvoiceEntity(invoice.getNumber(),
                BookingDataMapper.createBookingEntity(booking, CustomerDataMapper.createCustomerEntity(booking.getCustomer())),
                BoardDataMapper.createBoardEntity(invoice.getBoard()), invoice.getPricePerNightForBoard(),
                invoice.getFromDate(), invoice.getToDate(), invoicedRoomCategoryEntities);
        return invoiceEntity;
    }
}
