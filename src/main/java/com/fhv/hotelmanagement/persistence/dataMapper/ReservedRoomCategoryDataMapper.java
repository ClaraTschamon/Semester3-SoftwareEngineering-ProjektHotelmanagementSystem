//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.persistenceEntity.*;

import java.util.ArrayList;
import java.util.Optional;

public class ReservedRoomCategoryDataMapper {

    private ReservedRoomCategoryDataMapper(){}

    private static ReservedRoomCategoryDataMapper _instance = new ReservedRoomCategoryDataMapper();

    public static ReservedRoomCategoryDataMapper instance(){return _instance;}

    protected Optional<ReservedRoomCategory> get(final Reservation reservation){
        ReservedRoomCategoryEntity entity = PersistenceManager.instance().entityManager.find(ReservedRoomCategoryEntity.class, reservation);
        if(entity != null){
            ReservedRoomCategory reservedRoomCategory = createReservedRoomCategory(entity, reservation);
            return Optional.of(reservedRoomCategory);
        }
        return Optional.empty();
    }

    public static ArrayList<ReservedRoomCategory> getAll(){
        ArrayList<ReservedRoomCategoryEntity> entities = (ArrayList<ReservedRoomCategoryEntity>) PersistenceManager.instance().entityManager.createQuery("from ReservedRoomCategoryEntity ").getResultList();
        ArrayList<ReservedRoomCategory> reservedRoomCategories = new ArrayList<>();
        for(ReservedRoomCategoryEntity e : entities){
            reservedRoomCategories.add(createReservedRoomCategory(e, ReservationDataMapper.createReservation(e.getReservation())));
        }
        return reservedRoomCategories;
    }

    public void insert(ReservedRoomCategory reservedRoomCategory){
        var entityManager = PersistenceManager.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.persist(createReservedRoomCategoryEntity(reservedRoomCategory));
        entityManager.getTransaction().commit();
    }

    protected void store(ReservedRoomCategory reservedRoomCategory){
        var entityManager = PersistenceManager.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.merge(createReservedRoomCategoryEntity(reservedRoomCategory));
        entityManager.getTransaction().commit();
    }

    protected static ReservedRoomCategoryEntity createReservedRoomCategoryEntity(ReservedRoomCategory reservedRoomCategory) {
        Reservation reservation = reservedRoomCategory.getReservation();
        CustomerEntity customerEntity = CustomerDataMapper.createCustomerEntity(reservation.getCustomer());
        BookingEntity bookingEntity = BookingDataMapper.createBookingEntity(reservation.getBooking(), customerEntity);
        return new ReservedRoomCategoryEntity(ReservationDataMapper.createReservationEntity(reservation, bookingEntity, customerEntity),
                RoomCategoryDataMapper.createRoomCategoryEntity(reservedRoomCategory.getRoomCategory()),
                reservedRoomCategory.getPricePerNight(), reservedRoomCategory.getAmount());
    }

    protected static ReservedRoomCategory createReservedRoomCategory(ReservedRoomCategoryEntity reservedRoomCategoryEntity, Reservation reservation) {
        return new ReservedRoomCategory(reservation, RoomCategoryDataMapper.createRoomCategory(reservedRoomCategoryEntity.getRoomCategory()),
                reservedRoomCategoryEntity.getPricePerNight(), reservedRoomCategoryEntity.getAmount());
    }
}
