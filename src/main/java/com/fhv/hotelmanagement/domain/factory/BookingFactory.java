package com.fhv.hotelmanagement.domain.factory;

import com.fhv.hotelmanagement.domain.domainModel.BookedRoom;
import com.fhv.hotelmanagement.domain.domainModel.BookedRoomCategory;
import com.fhv.hotelmanagement.domain.domainModel.Booking;
import com.fhv.hotelmanagement.domain.exceptions.BookingIsInvalidException;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.services.StringValidator;
import com.fhv.hotelmanagement.view.DTOs.AddressDTO;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomCategoryDTO;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;

import java.util.ArrayList;

public class BookingFactory {

    public static BookingDTO getBooking(Long number) {
        Booking booking = PersistenceFacade.getBooking(number).get();
        if (booking != null) {
            return createBookingDTO(booking, true, null);
        }
        return null;
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

        BookingDTO bookingDTO = new BookingDTO(booking.getNumber(),
                CustomerFactory.createCustomerDTO(booking.getCustomer()), booking.getArrivalDate(),
                booking.getCheckInDatetime(), booking.getDepartureDate(), booking.getCheckOutDatetime(),
                AddressFactory.createAddressDTO(booking.getBillingAddress()), booking.getPaymentMethod(), booking.getCreditCardNumber(),
                booking.getExpirationDate(), booking.getAuthorisationNumber(), BoardFactory.createBoardDTO(booking.getBoard()),
                booking.getPricePerNightForBoard(), booking.getComment(), booking.getAmountGuests(),
                bookedRoomCategoryDTOS, bookedRoomDTOS);
        if (includeArrays) {
            for (BookedRoomCategory bookedRoomCategory : booking.getBookedRoomCategories()) {
                bookedRoomCategoryDTOS.add(BookedRoomCategoryFactory.createRoomCategoryDTO(bookedRoomCategory));
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

        Booking booking = new Booking(bookingDTO.getNumber(), CustomerFactory.createCustomer(bookingDTO.getCustomer()), bookingDTO.getArrivalDate(),
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
        return (bookingDTO != null) &&
                (CustomerFactory.checkCustomer(bookingDTO.getCustomer())) &&
                (bookingDTO.getArrivalDate() != null) &&
                (bookingDTO.getDepartureDate() != null) &&
                (bookingDTO.getArrivalDate().isBefore(bookingDTO.getDepartureDate())) &&
                (AddressFactory.checkAddress(bookingDTO.getBillingAddress())) &&
                (StringValidator.checkString(bookingDTO.getPaymentMethod())) &&
                (StringValidator.checkString(bookingDTO.getCreditCardNumber())) &&
                (StringValidator.checkString(bookingDTO.getExpirationDate()) && StringValidator.checkValidExpirationDate(bookingDTO.getExpirationDate())) &&
                (StringValidator.checkString(bookingDTO.getAuthorisationNumber())) &&
                (bookingDTO.getBookedRooms() != null && !bookingDTO.getBookedRooms().isEmpty()) &&
                (bookingDTO.getBookedRoomCategories() != null && !bookingDTO.getBookedRoomCategories().isEmpty()) &&
                (BookedRoomCategoryFactory.checkBookedRoomCategories(bookingDTO.getBookedRoomCategories(), false)) &&
                (BookedRoomFactory.checkBookedRooms(bookingDTO.getBookedRooms(), false)) &&
                ((bookingDTO.getBoard() == null && bookingDTO.getPricePerNightForBoard() == null)
                        || (BoardFactory.checkBoard(bookingDTO.getBoard()) && bookingDTO.getPricePerNightForBoard() != null && bookingDTO.getPricePerNightForBoard().intValue() >= 0));
    }
}
