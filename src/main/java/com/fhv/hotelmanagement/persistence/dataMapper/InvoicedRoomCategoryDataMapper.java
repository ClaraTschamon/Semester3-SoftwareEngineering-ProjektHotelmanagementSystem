package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.Booking;
import com.fhv.hotelmanagement.domain.domainModel.Invoice;
import com.fhv.hotelmanagement.domain.domainModel.InvoicedRoomCategory;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.InvoiceEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.InvoicedRoomCategoryEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.RoomCategoryEntity;

import java.util.HashSet;
import java.util.Optional;

public class InvoicedRoomCategoryDataMapper {
    private InvoicedRoomCategoryDataMapper(){}

    private static InvoicedRoomCategoryDataMapper _instance = new InvoicedRoomCategoryDataMapper();

    public static InvoicedRoomCategoryDataMapper instance(){
        return _instance;
    }

    //create
    public void insert(InvoicedRoomCategory invoicedRoomCategory) {
        var entityManager = PersistenceFacade.instance().entityManager;
        InvoicedRoomCategoryEntity invoicedRoomCategoryEntity = createInvoicedRoomCategoryEntity(invoicedRoomCategory);
        entityManager.getTransaction().begin();
        entityManager.persist(invoicedRoomCategoryEntity);
        entityManager.getTransaction().commit();
    }

    private InvoicedRoomCategoryEntity createInvoicedRoomCategoryEntity(InvoicedRoomCategory invoicedRoomCategory) {
        return new InvoicedRoomCategoryEntity(InvoiceDataMapper.createInvoiceEntity(invoicedRoomCategory.getInvoice()),
                RoomCategoryDataMapper.createRoomCategoryEntity(invoicedRoomCategory.getRoomCategory()),
                invoicedRoomCategory.getPricePerNight(), invoicedRoomCategory.getAmount());
    }
}
