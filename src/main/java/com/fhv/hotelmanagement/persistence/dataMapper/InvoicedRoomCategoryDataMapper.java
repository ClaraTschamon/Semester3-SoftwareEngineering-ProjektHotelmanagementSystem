//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.InvoicedRoomCategory;
import com.fhv.hotelmanagement.persistence.persistenceEntity.InvoicedRoomCategoryEntity;

public class InvoicedRoomCategoryDataMapper {
    private InvoicedRoomCategoryDataMapper(){}

    private static InvoicedRoomCategoryDataMapper _instance = new InvoicedRoomCategoryDataMapper();

    public static InvoicedRoomCategoryDataMapper instance(){
        return _instance;
    }

    //create
    public void insert(InvoicedRoomCategory invoicedRoomCategory) {
        var entityManager = PersistenceManager.instance().entityManager;
        InvoicedRoomCategoryEntity invoicedRoomCategoryEntity = createInvoicedRoomCategoryEntity(invoicedRoomCategory);
        entityManager.getTransaction().begin();
        entityManager.persist(invoicedRoomCategoryEntity);
        entityManager.getTransaction().commit();
    }

    public void store(InvoicedRoomCategory invoicedRoomCategory){
        var entityManager = PersistenceManager.instance().entityManager;
        InvoicedRoomCategoryEntity invoicedRoomCategoryEntity = createInvoicedRoomCategoryEntity(invoicedRoomCategory);
        entityManager.getTransaction().begin();
        entityManager.merge(invoicedRoomCategoryEntity);
        entityManager.getTransaction().commit();
    }

    private InvoicedRoomCategoryEntity createInvoicedRoomCategoryEntity(InvoicedRoomCategory invoicedRoomCategory) {
        return new InvoicedRoomCategoryEntity(InvoiceDataMapper.createInvoiceEntity(invoicedRoomCategory.getInvoice()),
                RoomCategoryDataMapper.createRoomCategoryEntity(invoicedRoomCategory.getRoomCategory()),
                invoicedRoomCategory.getPricePerNight(), invoicedRoomCategory.getAmount());
    }
}
