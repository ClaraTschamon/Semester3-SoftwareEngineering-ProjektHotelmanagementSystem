package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomCategoryDTO;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.DTOs.RoomDTO;
import com.fhv.hotelmanagement.view.controller.useCaseController.CheckOutUseCaseController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;

public class CheckOutViewController implements Initializable {

    private CheckOutUseCaseController useCaseController;

    private ArrayList<BookedRoomDTO> allBookedRoomDTOs;
    @FXML
    public Text roomText;
    @FXML
    public Text firstNameText;
    @FXML
    public Text lastNameText;
    @FXML
    public Text packageText;
    @FXML
    public Text numberPersonsText;
    @FXML
    public Text paymentMethodText;
    @FXML
    public Text totalPriceText;

    public ComboBox roomComboBox;

    public CheckOutViewController(){
        useCaseController = new CheckOutUseCaseController();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allBookedRoomDTOs = MainApplication.getDomainManager().getBookedRoomsBetween(LocalDate.now(), LocalDate.now());
        ArrayList<RoomDTO> rooms = new ArrayList<>();

        for(BookedRoomDTO bookedRoom : allBookedRoomDTOs){
            if (bookedRoom.getBooking().getCheckOutDatetime() == null) {
                rooms.add(bookedRoom.getRoom());
            }
        }
        ObservableList<RoomDTO> roomDTOS = FXCollections.observableList(rooms);
        roomComboBox.setConverter(new RoomNumberConverter(new RoomProvider()));
        roomComboBox.getItems().addAll(roomDTOS);

        roomComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                RoomDTO selectedRoom = (RoomDTO) roomComboBox.getSelectionModel().getSelectedItem();
                BookingDTO bookingDTO = getBookingFromRoom(selectedRoom);
                setTexts(bookingDTO);
            }
        });
    }

    private BookingDTO getBookingFromRoom(RoomDTO room){
        for(BookedRoomDTO bookedRoom : allBookedRoomDTOs){
            if (bookedRoom.getRoom().equals(room)){
                BookingDTO bookingDTO = bookedRoom.getBooking();
                useCaseController.setBooking(bookingDTO);
                return bookingDTO;
            }
        }
        return null;
    }

    private void setTexts(BookingDTO bookingDTO){
        StringBuilder rooms = new StringBuilder();
        rooms.append("Zimmer: ");
        ArrayList<BookedRoomDTO> bookedRoomDTOs = bookingDTO.getBookedRooms();
        for(BookedRoomDTO bookedRoomDTO : bookedRoomDTOs){
            rooms.append(bookedRoomDTO.getRoom().getNumber()).append(" ");
        }
        roomText.setText(rooms.toString());

        firstNameText.setText("Vorname: " + bookingDTO.getCustomer().getFirstName());
        lastNameText.setText("Nachname: " + bookingDTO.getCustomer().getLastName());
        packageText.setText("Package: " + bookingDTO.getBoard().getName());
        numberPersonsText.setText("Personenanzahl: "+ String.valueOf(bookingDTO.getAmountGuests()));
        paymentMethodText.setText("Zahlungsart: " + bookingDTO.getPaymentMethod());

        BigDecimal price = calculateTotalPrice(bookingDTO, bookedRoomDTOs);
        totalPriceText.setText("Gesamtbetrag: " + price);
    }

    private BigDecimal calculateTotalPrice(BookingDTO bookingDTO, ArrayList<BookedRoomDTO> bookedRoomDTOs){
        int totalNights = (int) DAYS.between(bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate());

        BigDecimal totalPrice = new BigDecimal(0);

        for (BookedRoomCategoryDTO c : bookingDTO.getBookedRoomCategories()) {
            BigDecimal price = c.getPricePerNight().multiply(new BigDecimal(c.getAmount()));
            totalPrice = totalPrice.add(price);
        }

        BigDecimal boardPrice = bookingDTO.getPricePerNightForBoard();
        if (boardPrice != null) {
            boardPrice = boardPrice.multiply(new BigDecimal(totalNights).multiply(new BigDecimal(bookingDTO.getAmountGuests())));
            totalPrice = totalPrice.add(boardPrice);
        }

        return totalPrice;
    }

    @FXML
    public void onCancelButtonClicked(ActionEvent actionEvent) throws IOException {
        //useCaseController.cancel();
        MainApplication.getMainController().loadIntoContentArea("home");
    }

    @FXML
    public void onConfirmButtonClicked(ActionEvent actionEvent) throws IOException {
        useCaseController.save();
        MainApplication.getMainController().loadIntoContentArea("home");
    }
}