package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.domain.domainController.DomainController;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;

public class CheckOut1ViewController implements Initializable {

    //private CheckOutUseCaseController useCaseController;
    private ArrayList<BookedRoomDTO> allBookedRoomDTOs;
    @FXML
    public Text roomText;
    @FXML
    public Text firstNameText;
    @FXML
    public Text lastNameText;
    @FXML
    public Text nightsText;
    @FXML
    public Text fromDateText;
    @FXML
    public Text toDateText;
    @FXML
    public Text numberPersonsText;
    @FXML
    public Text packageText;
    @FXML
    public Text roomPriceText;
    @FXML
    public Text paymentMethodText;
    @FXML
    public Text totalPriceText;
    @FXML
    public ComboBox roomComboBox;
    @FXML
    public CheckBox printInvoiceCheckBox;

    private CheckOutViewController viewController;

    /*public CheckOut1ViewController(){
        useCaseController = new CheckOutUseCaseController();
    }*/

    public void setController(CheckOutViewController viewController){
        this.viewController = viewController;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allBookedRoomDTOs = DomainController.getBookedRoomsBetween(LocalDate.now(), LocalDate.now());
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
                viewController.getUseCaseController().setBooking(bookingDTO);
                return bookingDTO;
            }
        }
        return null;
    }

    private void setTexts(BookingDTO bookingDTO){
        StringBuilder rooms = new StringBuilder();
        rooms.append("Zimmer:               ");
        ArrayList<BookedRoomDTO> bookedRoomDTOs = bookingDTO.getBookedRooms();
        for(BookedRoomDTO bookedRoomDTO : bookedRoomDTOs){
            rooms.append(bookedRoomDTO.getRoom().getNumber()).append("   ");
        }
        roomText.setText(rooms.toString());

        firstNameText.setText("Vorname:             " + bookingDTO.getCustomer().getFirstName());
        lastNameText.setText("Nachname:           " + bookingDTO.getCustomer().getLastName());

        int nights = (int) DAYS.between(bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate());
        nightsText.setText("Nächte:                " + nights);

        LocalDate arrivalDate = bookingDTO.getArrivalDate();
        String formattedArrivalDate = arrivalDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        fromDateText.setText("Anreisedatum:      " + formattedArrivalDate);

        LocalDate departureDate = bookingDTO.getDepartureDate();
        String formattedDepartureDate = departureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        toDateText.setText("Abreisedatum:      " + formattedDepartureDate);

        numberPersonsText.setText("Personenanzahl:   "+ String.valueOf(bookingDTO.getAmountGuests()));
        packageText.setText("Package:               " + bookingDTO.getBoard().getName());

        if(Objects.equals(bookingDTO.getBookedRoomCategories().get(0).getPricePerNight(), new BigDecimal(0))){
            roomPriceText.setText("Zimmerpreis:        Preis-0");
        }
        else{
            roomPriceText.setText("Zimmerpreis:        Normalpreis");
        }

        paymentMethodText.setText("Zahlungsart:         " + bookingDTO.getPaymentMethod());
        BigDecimal price = calculateTotalPrice(bookingDTO, bookedRoomDTOs);
        totalPriceText.setText("Gesamtbetrag*:    " + price + " €");
    }

    private BigDecimal calculateTotalPrice(BookingDTO bookingDTO, ArrayList<BookedRoomDTO> bookedRoomDTOs){
        int totalNights = (int) DAYS.between(bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate());

        BigDecimal totalPrice = new BigDecimal(0);

        for (BookedRoomCategoryDTO c : bookingDTO.getBookedRoomCategories()) {
            BigDecimal price = c.getPricePerNight().multiply(new BigDecimal(c.getAmount())).multiply(BigDecimal.valueOf(totalNights));
            totalPrice = totalPrice.add(price);
        }
        //TODO: überprüfen ob die nächtezahl mitgerechnet ist

        BigDecimal boardPrice = bookingDTO.getPricePerNightForBoard();
        if (boardPrice != null) {
            boardPrice = boardPrice.multiply(new BigDecimal(totalNights).multiply(new BigDecimal(bookingDTO.getAmountGuests())));
            totalPrice = totalPrice.add(boardPrice);
        }

        return totalPrice;
    }

    @FXML
    public void onCancelButtonClicked(ActionEvent actionEvent) throws IOException {
        MainApplication.getMainController().loadIntoContentArea("home");
    }

    @FXML
    public void onNextButtonClicked(ActionEvent actionEvent) throws IOException {
        //useCaseController.save();
        //MainApplication.getMainController().loadIntoContentArea("home");
        viewController.loadCheckOut2();
    }
}