//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.factory;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.domain.exceptions.ReservationIsInvalidException;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.services.StringValidator;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.util.ArrayList;

public class ReservationFactory {
    private static ArrayList<Reservation> reservations;

    public static ReservationDTO getReservation(Long number){
        Reservation reservation = PersistenceFacade.getReservation(number).get();
        if(reservation != null){
            return createReservationDTO(reservation, true, null);
        }
        return null;
    }

    public static ArrayList<ReservationDTO> getAllReservations(){
        if(reservations==null){
            refreshReservations();
        }

        ArrayList<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation r: reservations){
            reservationDTOS.add(createReservationDTO(r, true, null));
        }

        return reservationDTOS;
    }

    private static void refreshReservations() {
        reservations= new ArrayList<>();
        for(Reservation r : PersistenceFacade.getAllReservations()){
            reservations.add(r);
        }
    }


    public static Long saveReservation(ReservationDTO reservationDTO) throws ReservationIsInvalidException {
        Long reservationNumber = reservationDTO.getNumber();
        if(checkReservation(reservationDTO)){
            Reservation reservation = createReservation(reservationDTO, true);
            if(reservationNumber == null){
                reservationNumber = PersistenceFacade.insertReservation(reservation);
            } else{
                PersistenceFacade.storeReservation(reservation);
            }
        } else {
            throw new ReservationIsInvalidException();
        }
        return reservationNumber;
    }

    protected static ReservationDTO createReservationDTO(Reservation reservation, boolean includeArrays, ReservedRoom allExcept){
        if(reservation == null){
            return null;
        }
        ArrayList<ReservedRoomCategoryDTO> reservedRoomCategoryDTOS = new ArrayList<>();
        ArrayList<ReservedRoomDTO> reservedRoomDTOS = new ArrayList<>();

        ReservationDTO reservationDTO = new ReservationDTO(reservation.getNumber(), null,
                CustomerFactory.createCustomerDTO(reservation.getCustomer()),
                reservation.getCreationTimestamp(), reservation.getArrivalDate(),
                reservation.getDepartureDate(), AddressFactory.createAddressDTO(reservation.getBillingAddress()),
                reservation.getPaymentMethod(), reservation.getCreditCardNumber(), reservation.getExpirationDate(), reservation.getAuthorisationNumber(),
                BoardFactory.createBoardDTO(reservation.getBoard()), reservation.getPricePerNightForBoard(),reservation.getComment(),
                reservation.getAmountGuests(), reservedRoomCategoryDTOS, reservedRoomDTOS);

        if(includeArrays){
            for(ReservedRoomCategory reservedRoomCategory : reservation.getReservedRoomCategories()){
                reservedRoomCategoryDTOS.add(ReservedRoomCategoryFactory.createReservedRoomCategoryDTO(reservedRoomCategory));
            }
            for(ReservedRoom reservedRoom : reservation.getReservedRooms()){
                reservedRoomDTOS.add(ReservedRoomFactory.createReservedRoomDTO(reservedRoom, false));
            }
        }
        return reservationDTO;
    }

    protected static Reservation createReservation(ReservationDTO reservationDTO, boolean fillArrays){
        if(reservationDTO == null){
            return null;
        }
        AddressDTO billingAddress = reservationDTO.getBillingAddress();

        ArrayList<ReservedRoomCategory> reservedRoomCategories = new ArrayList<>();
        ArrayList<ReservedRoom> reservedRooms = new ArrayList<>();
        Reservation reservation;

        if(reservationDTO.getBooking() == null){ //neu
            reservation = new Reservation(reservationDTO.getNumber(), null,
                    CustomerFactory.createCustomer(reservationDTO.getCustomer()),
                    reservationDTO.getCreationTimestamp(), reservationDTO.getArrivalDate(), reservationDTO.getDepartureDate(),
                    billingAddress.getStreet(), billingAddress.getHouseNumber(), billingAddress.getPostalCode(), billingAddress.getCity(),
                    billingAddress.getCountry(), reservationDTO.getComment(), reservationDTO.getPaymentMethod(), reservationDTO.getCreditCardNumber(),
                    reservationDTO.getExpirationDate(), reservationDTO.getAuthorisationNumber(), BoardFactory.createBoard(reservationDTO.getBoard()),
                    reservationDTO.getPricePerNightForBoard(), reservationDTO.getAmountGuests(), reservedRoomCategories, reservedRooms);
        } else {

            reservation = new Reservation(reservationDTO.getNumber(), BookingFactory.createBooking(reservationDTO.getBooking(), true),
                    CustomerFactory.createCustomer(reservationDTO.getCustomer()),
                    reservationDTO.getCreationTimestamp(), reservationDTO.getArrivalDate(), reservationDTO.getDepartureDate(),
                    billingAddress.getStreet(), billingAddress.getHouseNumber(), billingAddress.getPostalCode(), billingAddress.getCity(),
                    billingAddress.getCountry(), reservationDTO.getComment(), reservationDTO.getPaymentMethod(), reservationDTO.getCreditCardNumber(),
                    reservationDTO.getExpirationDate(), reservationDTO.getAuthorisationNumber(), BoardFactory.createBoard(reservationDTO.getBoard()),
                    reservationDTO.getPricePerNightForBoard(), reservationDTO.getAmountGuests(), reservedRoomCategories, reservedRooms);
        }
        if(fillArrays){
            for(ReservedRoomCategoryDTO reservedRoomCategoryDTO : reservationDTO.getReservedRoomCategories()){
                reservedRoomCategories.add(ReservedRoomCategoryFactory.createReservedRoomCategory(reservedRoomCategoryDTO));
            }
            for(ReservedRoomDTO reservedRoomDTO : reservationDTO.getReservedRooms()){
                reservedRooms.add(ReservedRoomFactory.createReservedRoom(reservedRoomDTO));
            }
        }
        return reservation;
    }

    protected static boolean checkReservation(ReservationDTO reservationDTO){
        return (reservationDTO != null) && (CustomerFactory.checkCustomer(reservationDTO.getCustomer())) &&
                (reservationDTO.getArrivalDate() != null) && (reservationDTO.getDepartureDate() != null) &&
                (reservationDTO.getArrivalDate().isBefore(reservationDTO.getDepartureDate())) &&
                (AddressFactory.checkAddress(reservationDTO.getBillingAddress())) &&
                (checkPaymentMethod(reservationDTO)) &&
                (reservationDTO.getReservedRooms() != null && !reservationDTO.getReservedRooms().isEmpty()) &&
                (ReservedRoomCategoryFactory.checkReservedRoomCategories(reservationDTO.getReservedRoomCategories(), false)) &&
                (ReservedRoomFactory.checkReservedRooms(reservationDTO.getReservedRooms(), false)) &&
                ((reservationDTO.getBoard() == null && reservationDTO.getPricePerNightForBoard() == null)
                    || (BoardFactory.checkBoard(reservationDTO.getBoard()) && reservationDTO.getPricePerNightForBoard() != null && reservationDTO.getPricePerNightForBoard().intValue() >= 0));
    }

    private static boolean checkPaymentMethod(ReservationDTO reservationDTO){
        if (StringValidator.checkString(reservationDTO.getPaymentMethod())) {
            if (reservationDTO.getPaymentMethod().equals("Credit Card")) { //TODO: Passt das so?
                return (StringValidator.checkString(reservationDTO.getCreditCardNumber())) &&
                        (StringValidator.checkString(reservationDTO.getExpirationDate()) && StringValidator.checkValidExpirationDate(reservationDTO.getExpirationDate())) &&
                        (StringValidator.checkString(reservationDTO.getAuthorisationNumber()));
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
