//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.factory;

import com.fhv.hotelmanagement.domain.domainModel.BookedRoom;
import com.fhv.hotelmanagement.domain.domainModel.BookedRoomCategory;
import com.fhv.hotelmanagement.domain.domainModel.Booking;
import com.fhv.hotelmanagement.domain.exceptions.BookingIsInvalidException;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.services.StringValidator;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookingFactory {

    private static ArrayList<Booking> bookings;

    public static BookingDTO getBooking(Long number) {
        Booking booking = PersistenceFacade.getBooking(number).get();
        if (booking != null) {
            return createBookingDTO(booking, true, null);
        }
        return null;
    }

    public static ArrayList<BookingDTO> getAllBookings(){
        if(bookings == null){
            refreshBookings();
        }

        ArrayList<BookingDTO> bookingDTOs = new ArrayList<>();
        for(Booking b : bookings){
            bookingDTOs.add(createBookingDTO(b, true, null)); //ist das richtig so???
        }
        return bookingDTOs;
    }

    public static ArrayList<BookingDTO> getAllBookingsBetween(LocalDate minDate, LocalDate maxDate){
        ArrayList<BookingDTO> bookingDTOS = new ArrayList<>();
        for(Booking booking : PersistenceFacade.getAllBookingsBetween(minDate, maxDate)){
            bookingDTOS.add(createBookingDTO(booking, true, null));
        }
        return bookingDTOS;
    }

    public static ArrayList<BookingDTO> getCurrentBookings(){
        ArrayList<BookingDTO> bookingDTOS = new ArrayList<>();
        for(Booking booking : PersistenceFacade.getCurrentBookings()){
            bookingDTOS.add(createBookingDTO(booking, true, null));
        }
        return bookingDTOS;
    }

    private static void refreshBookings(){
        bookings = new ArrayList<>();
        for(Booking b : PersistenceFacade.getAllBookings()){
            bookings.add(b);
        }
    }

    public static Long saveBooking(BookingDTO bookingDTO) throws BookingIsInvalidException {
        Long bookingNumber = bookingDTO.getNumber();
        if (checkBooking(bookingDTO)) {
            Booking booking = createBooking(bookingDTO, true);
            if (bookingNumber == null) {
                bookingNumber = PersistenceFacade.insertBooking(booking);
            } else {
                PersistenceFacade.storeBooking(booking);
            }
        } else {
            throw new BookingIsInvalidException();
        }
        return bookingNumber;
    }

    protected static BookingDTO createBookingDTO(Booking booking, boolean includeArrays, BookedRoom allExcept) {
        ArrayList<BookedRoomCategoryDTO> bookedRoomCategoryDTOS = new ArrayList<>();
        ArrayList<BookedRoomDTO> bookedRoomDTOS = new ArrayList<>();

        BookingDTO bookingDTO = new BookingDTO(booking.getNumber(), null,
                CustomerFactory.createCustomerDTO(booking.getCustomer()), booking.getArrivalDate(),
                booking.getCheckInDatetime(), booking.getDepartureDate(), booking.getCheckOutDatetime(),
                AddressFactory.createAddressDTO(booking.getBillingAddress()), booking.getPaymentMethod(), booking.getCreditCardNumber(),
                booking.getExpirationDate(), booking.getAuthorisationNumber(), BoardFactory.createBoardDTO(booking.getBoard()),
                booking.getPricePerNightForBoard(), booking.getComment(), booking.getAmountGuests(),
                bookedRoomCategoryDTOS, bookedRoomDTOS);

        if(booking.getReservation() != null){
            ReservationDTO reservationDTO = ReservationFactory.createReservationDTO(booking.getReservation(), bookingDTO, true, null);
            bookingDTO.setReservation(reservationDTO);
        }

        if (includeArrays) {
            for (BookedRoomCategory bookedRoomCategory : booking.getBookedRoomCategories()) {
                bookedRoomCategoryDTOS.add(BookedRoomCategoryFactory.createBookedRoomCategoryDTO(bookedRoomCategory));
            }
            for (BookedRoom bookedRoom : booking.getBookedRooms()) {
                bookedRoomDTOS.add(BookedRoomFactory.createBookedRoomDTO(bookedRoom, false));
            }
        }
        return bookingDTO;
    }

    protected static Booking createBooking(BookingDTO bookingDTO, boolean fillArrays) {
        AddressDTO billingAddress = bookingDTO.getBillingAddress();

        ArrayList<BookedRoomCategory> bookedRoomCategories = new ArrayList<>();
        ArrayList<BookedRoom> bookedRooms = new ArrayList<>();

        Booking booking = new Booking(bookingDTO.getNumber(), ReservationFactory.createReservation(bookingDTO.getReservation(), true),
                CustomerFactory.createCustomer(bookingDTO.getCustomer()), bookingDTO.getArrivalDate(),
                bookingDTO.getCheckInDatetime(), bookingDTO.getDepartureDate(), bookingDTO.getCheckOutDatetime(),
                billingAddress.getStreet(), billingAddress.getHouseNumber(), billingAddress.getPostalCode(),
                billingAddress.getCity(), billingAddress.getCountry(), bookingDTO.getComment(), bookingDTO.getPaymentMethod(),
                bookingDTO.getCreditCardNumber(), bookingDTO.getExpirationDate(), bookingDTO.getAuthorisationNumber(),
                BoardFactory.createBoard(bookingDTO.getBoard()), bookingDTO.getPricePerNightForBoard(), bookingDTO.getAmountGuests(),
                bookedRoomCategories, bookedRooms);

        if (fillArrays) {
            for (BookedRoomCategoryDTO bookedRoomCategoryDTO : bookingDTO.getBookedRoomCategories()) {
                bookedRoomCategories.add(BookedRoomCategoryFactory.createBookedRoomCategory(bookedRoomCategoryDTO));
            }
            for (BookedRoomDTO bookedRoomDTO : bookingDTO.getBookedRooms()) {
                bookedRooms.add(BookedRoomFactory.createBookedRoom(bookedRoomDTO));
            }
        }

        return booking;
    }

    protected static boolean checkBooking(BookingDTO bookingDTO) {
//        System.out.println( // debug only
//                (bookingDTO != null) + " " +
//                        checkReservation(bookingDTO) + " " +
//                        (CustomerFactory.checkCustomer(bookingDTO.getCustomer())) + " " +
//                        (bookingDTO.getArrivalDate() != null) + " " +
//                        (bookingDTO.getDepartureDate() != null) + " " +
//                        (bookingDTO.getArrivalDate().isBefore(bookingDTO.getDepartureDate())) + " " +
//                        (AddressFactory.checkAddress(bookingDTO.getBillingAddress())) + " " +
//                        (checkPaymentMethod(bookingDTO)) + " " +
//                        (bookingDTO.getBookedRooms() != null && !bookingDTO.getBookedRooms().isEmpty()) + " " +
//                        (bookingDTO.getBookedRoomCategories() != null && !bookingDTO.getBookedRoomCategories().isEmpty()) + " " +
//                        (BookedRoomCategoryFactory.checkBookedRoomCategories(bookingDTO.getBookedRoomCategories(), false)) + " " +
//                        (BookedRoomFactory.checkBookedRooms(bookingDTO.getBookedRooms(), false)) + " " +
//                        ((bookingDTO.getBoard() == null && bookingDTO.getPricePerNightForBoard() == null)
//                                || (BoardFactory.checkBoard(bookingDTO.getBoard()) && bookingDTO.getPricePerNightForBoard() != null && bookingDTO.getPricePerNightForBoard().intValue() >= 0))
//        );
        return (bookingDTO != null) && checkReservation(bookingDTO) &&
                (CustomerFactory.checkCustomer(bookingDTO.getCustomer())) &&
                (bookingDTO.getArrivalDate() != null) &&
                (bookingDTO.getDepartureDate() != null) &&
                (bookingDTO.getArrivalDate().isBefore(bookingDTO.getDepartureDate())) &&
                (AddressFactory.checkAddress(bookingDTO.getBillingAddress())) &&
                (checkPaymentMethod(bookingDTO)) &&
                (bookingDTO.getBookedRooms() != null && !bookingDTO.getBookedRooms().isEmpty()) &&
                (bookingDTO.getBookedRoomCategories() != null && !bookingDTO.getBookedRoomCategories().isEmpty()) &&
                (BookedRoomCategoryFactory.checkBookedRoomCategories(bookingDTO.getBookedRoomCategories(), false)) &&
                (BookedRoomFactory.checkBookedRooms(bookingDTO.getBookedRooms(), false)) &&
                ((bookingDTO.getBoard() == null && bookingDTO.getPricePerNightForBoard() == null)
                        || (BoardFactory.checkBoard(bookingDTO.getBoard()) && bookingDTO.getPricePerNightForBoard() != null && bookingDTO.getPricePerNightForBoard().intValue() >= 0));
    }

    private static boolean checkPaymentMethod(BookingDTO bookingDTO) {
        if (StringValidator.checkString(bookingDTO.getPaymentMethod())) {
            if (bookingDTO.getPaymentMethod().equals("Credit card")) {
                return (StringValidator.checkString(bookingDTO.getCreditCardNumber())) &&
                        (StringValidator.checkString(bookingDTO.getExpirationDate()) && StringValidator.checkValidExpirationDate(bookingDTO.getExpirationDate())) &&
                        (StringValidator.checkString(bookingDTO.getAuthorisationNumber()));
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private static boolean checkReservation(BookingDTO bookingDTO){
        if(bookingDTO.getReservation() == null){
            return true;
        } else {
            if(ReservationFactory.checkReservation(bookingDTO.getReservation())){
                return true;
            }
            return  false;
        }
    }
}
