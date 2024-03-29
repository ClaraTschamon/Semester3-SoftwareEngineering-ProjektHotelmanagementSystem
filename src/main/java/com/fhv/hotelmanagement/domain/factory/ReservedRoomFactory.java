//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.factory;

import com.fhv.hotelmanagement.domain.domainModel.ReservedRoom;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.DTOs.ReservationDTO;
import com.fhv.hotelmanagement.view.DTOs.ReservedRoomDTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservedRoomFactory {

    public static ArrayList<ReservedRoomDTO> getReservedRoomsBetween(LocalDate minDate, LocalDate maxDate){
        ArrayList<ReservedRoomDTO> reservedRoomDTOS = new ArrayList<>();
        for (ReservedRoom reservedRoom : PersistenceFacade.getReservedRoomsBetween(minDate, maxDate)){
            BookingDTO bookingDTO = null;
            ReservationDTO reservationDTO;
            if(reservedRoom.getReservation().getBooking() != null){
                bookingDTO = BookingFactory.createBookingDTO(reservedRoom.getReservation().getBooking(), true, null);
                reservationDTO = ReservationFactory.createReservationDTO(reservedRoom.getReservation(), bookingDTO, true, reservedRoom);
            } else {
                reservationDTO = ReservationFactory.createReservationDTO(reservedRoom.getReservation(), bookingDTO, true, reservedRoom);
            }
            reservedRoomDTOS.add(createReservedRoomDTO(reservedRoom, reservationDTO,true));
        }
        return reservedRoomDTOS;
    }

    protected static ReservedRoomDTO createReservedRoomDTO(ReservedRoom reservedRoom, ReservationDTO reservationDTO, boolean includeReservationArrays){
        if(!includeReservationArrays){
            if(reservationDTO.getBooking() != null){
                return new ReservedRoomDTO(reservationDTO,
                        RoomFactory.createRoomDTO(reservedRoom.getRoom()),
                        reservedRoom.getFromDate(), reservedRoom.getToDate());
            } else {
                return new ReservedRoomDTO(reservationDTO,
                        RoomFactory.createRoomDTO(reservedRoom.getRoom()),
                        reservedRoom.getFromDate(), reservedRoom.getToDate());
            }

        } else {
            if(reservedRoom.getReservation().getBooking() != null) {
                BookingDTO bookingDTO = BookingFactory.createBookingDTO(reservedRoom.getReservation().getBooking(), true, null);
                return new ReservedRoomDTO(ReservationFactory.createReservationDTO(reservedRoom.getReservation(), bookingDTO, true, reservedRoom),
                        RoomFactory.createRoomDTO(reservedRoom.getRoom()),
                        reservedRoom.getFromDate(), reservedRoom.getToDate());
            } else {
                return new ReservedRoomDTO(ReservationFactory.createReservationDTO(reservedRoom.getReservation(), null, true, reservedRoom),
                        RoomFactory.createRoomDTO(reservedRoom.getRoom()),
                        reservedRoom.getFromDate(), reservedRoom.getToDate());
            }

        }
    }

    protected static ReservedRoom createReservedRoom(ReservedRoomDTO reservedRoomDTO){
        return new ReservedRoom(ReservationFactory.createReservation(reservedRoomDTO.getReservation(), false),
                RoomFactory.createRoom(reservedRoomDTO.getRoom()),
                reservedRoomDTO.getFromDate(), reservedRoomDTO.getToDate());
    }

    protected static boolean checkReservedRoom(ReservedRoomDTO reservedRoomDTO, boolean validateReservation){
        ReservationDTO reservationDTO = reservedRoomDTO.getReservation();

       /* System.out.println( //debug only
                ((!validateReservation) || (validateReservation && ReservationFactory.checkReservation(reservationDTO))) + " " +
                (RoomFactory.getAllRooms().contains(reservedRoomDTO.getRoom())) + " " +
                (reservedRoomDTO.getFromDate().isEqual(reservationDTO.getArrivalDate())
                        || (reservedRoomDTO.getFromDate().isAfter(reservationDTO.getArrivalDate()) &&
                        (reservedRoomDTO.getFromDate().isBefore(reservationDTO.getDepartureDate()) ||
                                reservedRoomDTO.getFromDate().isEqual(reservationDTO.getDepartureDate())))) + " " +
                (reservedRoomDTO.getToDate().isEqual(reservationDTO.getDepartureDate())
                        || (reservedRoomDTO.getToDate().isBefore(reservationDTO.getDepartureDate()) &&
                        (reservedRoomDTO.getToDate().isAfter(reservationDTO.getArrivalDate()) ||
                                reservedRoomDTO.getToDate().isEqual(reservationDTO.getArrivalDate())))));*/

        return((!validateReservation) || (validateReservation && ReservationFactory.checkReservation(reservationDTO))) &&
                (RoomFactory.getAllRooms().contains(reservedRoomDTO.getRoom())) &&
                (reservedRoomDTO.getFromDate().isEqual(reservationDTO.getArrivalDate())
                    || (reservedRoomDTO.getFromDate().isAfter(reservationDTO.getArrivalDate()) &&
                        (reservedRoomDTO.getFromDate().isBefore(reservationDTO.getDepartureDate()) ||
                                reservedRoomDTO.getFromDate().isEqual(reservationDTO.getDepartureDate())))) &&
                (reservedRoomDTO.getToDate().isEqual(reservationDTO.getDepartureDate())
                || (reservedRoomDTO.getToDate().isBefore(reservationDTO.getDepartureDate()) &&
                        (reservedRoomDTO.getToDate().isAfter(reservationDTO.getArrivalDate()) ||
                                reservedRoomDTO.getToDate().isEqual(reservationDTO.getArrivalDate()))));
    }

    protected  static boolean checkReservedRooms(ArrayList<ReservedRoomDTO> reservedRoomDTOS, boolean validateReservation){
        for(ReservedRoomDTO reservedRoomDTO : reservedRoomDTOS){
            if(!checkReservedRoom(reservedRoomDTO, validateReservation)){
                return false;
            }
        }
        return true;
    }

}
