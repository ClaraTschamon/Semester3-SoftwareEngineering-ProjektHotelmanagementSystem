//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.factory;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.domain.exceptions.ReservationIsInvalidException;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.services.StringValidator;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationFactory {

    private static ArrayList<Reservation> reservations;

    public static ReservationDTO getReservation(Long number){
        Reservation reservation = PersistenceFacade.getReservation(number).get();
        if(reservation != null){
            if(reservation.getBooking() != null) {
                BookingDTO bookingDTO = BookingFactory.createBookingDTO(reservation.getBooking(), true, null);
                return createReservationDTO(reservation, bookingDTO, true, null);
            } else {
                return createReservationDTO(reservation,null,true, null);
            }
        }
        return null;
    }

    public static ArrayList<ReservationDTO> getAllReservations(){
        if(reservations==null){
            refreshReservations();
        }

        ArrayList<ReservationDTO> reservationDTOS = new ArrayList<>();


        for (Reservation r: reservations){
            if(r.getBooking() != null) {
                BookingDTO bookingDTO = BookingFactory.createBookingDTO(r.getBooking(), true, null);
                reservationDTOS.add(createReservationDTO(r, bookingDTO, true, null));
            } else {
                reservationDTOS.add(createReservationDTO(r, null,true, null));
            }
        }

        return reservationDTOS;
    }

    public static ArrayList<ReservationDTO> getAllReservationsBetween(LocalDate minDate, LocalDate maxDate){
        ArrayList<ReservationDTO> reservationDTOS = new ArrayList<>();
        for(Reservation reservation : PersistenceFacade.getAllReservationsBetween(minDate, maxDate)){
            if(reservation.getBooking() != null) {
                BookingDTO bookingDTO = BookingFactory.createBookingDTO(reservation.getBooking(), true, null);
                reservationDTOS.add(createReservationDTO(reservation, bookingDTO, true, null));
            } else {
                reservationDTOS.add(createReservationDTO(reservation,null, true, null));
            }
        }
        return reservationDTOS;
    }

    public static ArrayList<ReservationDTO> getNotConfirmedReservations(){
        ArrayList<ReservationDTO> reservationDTOS = new ArrayList<>();
        for(Reservation reservation : PersistenceFacade.getNotConfirmedReservations()){
            if(reservation.getBooking() != null) {
                BookingDTO bookingDTO = BookingFactory.createBookingDTO(reservation.getBooking(), true, null);
                reservationDTOS.add(createReservationDTO(reservation, bookingDTO, true, null));
            } else {
                reservationDTOS.add(createReservationDTO(reservation,null, true, null));
            }
        }
        return reservationDTOS;
    }

    public static ArrayList<ReservationDTO> getConfirmedReservations(){
        ArrayList<ReservationDTO> reservationDTOS = new ArrayList<>();
        for(Reservation reservation : PersistenceFacade.getConfirmedReservations()){
            if(reservation.getBooking() != null) {
                BookingDTO bookingDTO = BookingFactory.createBookingDTO(reservation.getBooking(), true, null);
                reservationDTOS.add(createReservationDTO(reservation, bookingDTO, true, null));
            } else {
                reservationDTOS.add(createReservationDTO(reservation,null, true, null));
            }
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

    public static void deleteReservation(ReservationDTO reservationDTO){
        Reservation reservation = createReservation(reservationDTO, true);
        PersistenceFacade.deleteReservation(reservation);
    }

    protected static ReservationDTO createReservationDTO(Reservation reservation, BookingDTO bookingDTO ,boolean includeArrays, ReservedRoom allExcept){
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

        if(bookingDTO != null){
            reservationDTO.setBooking(bookingDTO);
        }

        if(includeArrays){
            for(ReservedRoomCategory reservedRoomCategory : reservation.getReservedRoomCategories()){
                reservedRoomCategoryDTOS.add(ReservedRoomCategoryFactory.createReservedRoomCategoryDTO(reservedRoomCategory, reservationDTO));
            }
            for(ReservedRoom reservedRoom : reservation.getReservedRooms()){
                reservedRoomDTOS.add(ReservedRoomFactory.createReservedRoomDTO(reservedRoom, reservationDTO,false));
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

        //if(reservationDTO.getBooking() == null){ //neu
            reservation = new Reservation(reservationDTO.getNumber(), null,
                    CustomerFactory.createCustomer(reservationDTO.getCustomer()),
                    reservationDTO.getCreationTimestamp(), reservationDTO.getArrivalDate(), reservationDTO.getDepartureDate(),
                    billingAddress.getStreet(), billingAddress.getHouseNumber(), billingAddress.getPostalCode(), billingAddress.getCity(),
                    billingAddress.getCountry(), reservationDTO.getComment(), reservationDTO.getPaymentMethod(), reservationDTO.getCreditCardNumber(),
                    reservationDTO.getExpirationDate(), reservationDTO.getAuthorisationNumber(), BoardFactory.createBoard(reservationDTO.getBoard()),
                    reservationDTO.getPricePerNightForBoard(), reservationDTO.getAmountGuests(), reservedRoomCategories, reservedRooms);
        /*} else {

            reservation = new Reservation(reservationDTO.getNumber(), BookingFactory.createBooking(reservationDTO.getBooking(), true),
                    CustomerFactory.createCustomer(reservationDTO.getCustomer()),
                    reservationDTO.getCreationTimestamp(), reservationDTO.getArrivalDate(), reservationDTO.getDepartureDate(),
                    billingAddress.getStreet(), billingAddress.getHouseNumber(), billingAddress.getPostalCode(), billingAddress.getCity(),
                    billingAddress.getCountry(), reservationDTO.getComment(), reservationDTO.getPaymentMethod(), reservationDTO.getCreditCardNumber(),
                    reservationDTO.getExpirationDate(), reservationDTO.getAuthorisationNumber(), BoardFactory.createBoard(reservationDTO.getBoard()),
                    reservationDTO.getPricePerNightForBoard(), reservationDTO.getAmountGuests(), reservedRoomCategories, reservedRooms);
        }*/

        if(reservation.getBooking() != null) {
            Booking booking = BookingFactory.createBooking(reservationDTO.getBooking(), true);
            reservation.setBooking(booking);
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
            if (reservationDTO.getPaymentMethod().equals("Credit Card")) {
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
