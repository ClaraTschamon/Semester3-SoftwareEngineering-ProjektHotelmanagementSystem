//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.factory;

import com.fhv.hotelmanagement.domain.domainModel.BookedRoom;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookedRoomFactory {

    public static ArrayList<BookedRoomDTO> getBookedRoomsBetween(LocalDate minDate, LocalDate maxDate) {
        ArrayList<BookedRoomDTO> bookedRoomDTOS = new ArrayList<>();
        for (BookedRoom bookedRoom : PersistenceFacade.getBookedRoomsBetween(minDate, maxDate)) {
            bookedRoomDTOS.add(createBookedRoomDTO(bookedRoom, true));
        }
        return bookedRoomDTOS;
    }

    protected static BookedRoomDTO createBookedRoomDTO(BookedRoom bookedRoom, boolean includeBookingArrays) {
        if (!includeBookingArrays) {
            return new BookedRoomDTO(BookingFactory.createBookingDTO(bookedRoom.getBooking(), false, null),
                    RoomFactory.createRoomDTO(bookedRoom.getRoom()),
                    bookedRoom.getFromDate(), bookedRoom.getToDate());
        } else {
            return new BookedRoomDTO(BookingFactory.createBookingDTO(bookedRoom.getBooking(), true, bookedRoom),
                    RoomFactory.createRoomDTO(bookedRoom.getRoom()),
                    bookedRoom.getFromDate(), bookedRoom.getToDate());
        }
    }

    protected static BookedRoom createBookedRoom(BookedRoomDTO bookedRoomDTO) {
        return new BookedRoom(BookingFactory.createBooking(bookedRoomDTO.getBooking(), false),
                RoomFactory.createRoom(bookedRoomDTO.getRoom()),
                bookedRoomDTO.getFromDate(), bookedRoomDTO.getToDate());
    }

    protected static boolean checkBookedRoom(BookedRoomDTO bookedRoomDTO, boolean validateBooking) {
        BookingDTO bookingDTO = bookedRoomDTO.getBooking();
//        System.out.println( // debug only
//                ((!validateBooking) || (validateBooking && BookingFactory.checkBooking(bookingDTO))) +" "+
//                        (RoomFactory.getAllRooms().contains(bookedRoomDTO.getRoom())) +" "+
//                        (bookedRoomDTO.getFromDate().isEqual(bookingDTO.getArrivalDate())
//                                || (bookedRoomDTO.getFromDate().isAfter(bookingDTO.getArrivalDate()) &&
//                                    (bookedRoomDTO.getFromDate().isBefore(bookingDTO.getDepartureDate()) ||
//                                            bookedRoomDTO.getFromDate().isEqual(bookingDTO.getDepartureDate())))) +" "+
//                        (bookedRoomDTO.getToDate().isEqual(bookingDTO.getDepartureDate())
//                                || (bookedRoomDTO.getToDate().isBefore(bookingDTO.getDepartureDate()) &&
//                                    (bookedRoomDTO.getToDate().isAfter(bookingDTO.getArrivalDate()) ||
//                                            bookedRoomDTO.getToDate().isEqual(bookingDTO.getArrivalDate()))))
//        );
        return ((!validateBooking) || (validateBooking && BookingFactory.checkBooking(bookingDTO))) &&
                (RoomFactory.getAllRooms().contains(bookedRoomDTO.getRoom())) &&
                (bookedRoomDTO.getFromDate().isEqual(bookingDTO.getArrivalDate())
                        || (bookedRoomDTO.getFromDate().isAfter(bookingDTO.getArrivalDate()) &&
                        (bookedRoomDTO.getFromDate().isBefore(bookingDTO.getDepartureDate()) ||
                                bookedRoomDTO.getFromDate().isEqual(bookingDTO.getDepartureDate())))) &&
                (bookedRoomDTO.getToDate().isEqual(bookingDTO.getDepartureDate())
                        || (bookedRoomDTO.getToDate().isBefore(bookingDTO.getDepartureDate()) &&
                            (bookedRoomDTO.getToDate().isAfter(bookingDTO.getArrivalDate()) ||
                                bookedRoomDTO.getToDate().isEqual(bookingDTO.getArrivalDate()))));
    }

    protected static boolean checkBookedRooms(ArrayList<BookedRoomDTO> bookedRoomDTOS, boolean validateBooking) {
        for (BookedRoomDTO bookedRoomDTO : bookedRoomDTOS) {
            if (!checkBookedRoom(bookedRoomDTO, validateBooking)) {
                return false;
            }
        }
        return true;
    }
}
