//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.domain.factory.ReservationFactory;
import com.fhv.hotelmanagement.view.DTOs.*;
import com.fhv.hotelmanagement.view.viewServices.WarningType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ReservationNumberViewController {

    @FXML
    private TextField searchDatabaseTextField;
    private CheckInViewController viewController;

    public void setController(CheckInViewController viewController){
        this.viewController = viewController;
    }

    public void onNextButtonClicked(ActionEvent actionEvent) {
        if(searchDatabaseTextField.getText().equals("")){
            searchDatabaseTextField.setPromptText("RequiredField");
            searchDatabaseTextField.setStyle("-fx-prompt-text-fill: red");
            //TODO mit REGEX validieren ob wirklich nur zahlen drinnen stehen
        } else{
            long number = Integer.parseInt(searchDatabaseTextField.getText());
            try {
                ReservationDTO reservationDTO = ReservationFactory.getReservation(number);
                BookingDTO bookingDTO = null;
                if(reservationDTO.getBooking() == null){
                    bookingDTO = new BookingDTO(null, reservationDTO, reservationDTO.getCustomer(), reservationDTO.getArrivalDate(), null,
                            reservationDTO.getDepartureDate(), null, reservationDTO.getBillingAddress(), reservationDTO.getPaymentMethod(),
                            reservationDTO.getCreditCardNumber(), reservationDTO.getExpirationDate(), reservationDTO.getAuthorisationNumber(), reservationDTO.getBoard(),
                            reservationDTO.getPricePerNightForBoard(), reservationDTO.getComment(), reservationDTO.getAmountGuests(), null, null);


                    ArrayList<BookedRoomCategoryDTO> bookedRoomCategoryDTOS = new ArrayList<>();

                    for (ReservedRoomCategoryDTO reservedRoomCategoryDTO : reservationDTO.getReservedRoomCategories()) {
                        BookedRoomCategoryDTO bookedRoomCategoryDTO = new BookedRoomCategoryDTO();
                        bookedRoomCategoryDTO.setRoomCategory(reservedRoomCategoryDTO.getRoomCategory());
                        bookedRoomCategoryDTO.setAmount(reservedRoomCategoryDTO.getAmount());
                        bookedRoomCategoryDTO.setPricePerNight(reservedRoomCategoryDTO.getPricePerNight());
                        bookedRoomCategoryDTO.setBooking(bookingDTO);
                        bookedRoomCategoryDTOS.add(bookedRoomCategoryDTO);
                    }

                    ArrayList<BookedRoomDTO> bookedRoomDTOS = new ArrayList<>();

                    for (ReservedRoomDTO reservedRoomDTO : reservationDTO.getReservedRooms()) {
                        BookedRoomDTO bookedRoomDTO = new BookedRoomDTO();
                        bookedRoomDTO.setRoom(reservedRoomDTO.getRoom());
                        bookedRoomDTO.getRoom().setNumber(reservedRoomDTO.getRoom().getNumber());
                        bookedRoomDTO.setFromDate(reservedRoomDTO.getFromDate());
                        bookedRoomDTO.setToDate(reservedRoomDTO.getToDate());
                        bookedRoomDTO.setBooking(bookingDTO);
                        bookedRoomDTOS.add(bookedRoomDTO);
                    }

                    bookingDTO.setBookedRoomCategories(bookedRoomCategoryDTOS);
                    bookingDTO.setBookedRooms(bookedRoomDTOS);
                    bookingDTO.setReservation(reservationDTO);
                    reservationDTO.setBooking(bookingDTO);

                    for(BookedRoomCategoryDTO bookedRoomCategoryDTO : bookingDTO.getBookedRoomCategories()){
                        System.out.println(bookedRoomCategoryDTO.toString());
                    }

                    for(BookedRoomDTO bookedRoomDTO : bookingDTO.getBookedRooms()){
                        System.out.println(bookedRoomDTO.toString());
                    }

                } else {
                    if(reservationDTO.getBooking().getCheckInDatetime() != null){
                        //wenn die Reservierung bereits eingechecked ist sollte das nicht nochmal möglich sein.
                        throw new NoSuchElementException();
                    }
                }
                viewController.getUseCaseController().setBooking(bookingDTO);
                viewController.getUseCaseController().setCustomer(reservationDTO.getCustomer());
                viewController.loadCheckIn1();
            }catch(NoSuchElementException e){
                searchDatabaseTextField.setStyle("-fx-text-inner-color: red");
                System.out.println("This Reservation Number is wrong.");
                MainApplication.getMainController().alert("This Reservation Number is wrong.", WarningType.WARNING);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void onCancelButtonClicked(ActionEvent actionEvent) {
        try {
            viewController.cancel();
        } catch (IOException exc) {
            System.out.println("Error clicking cancel button: " + exc.getMessage());
            MainApplication.getMainController().alert("Error cancelling, please try again.", WarningType.WARNING);
        }
    }

}
