//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.view.DTOs.*;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;

public class CheckOut1ViewController implements Initializable {
    @FXML
    private ComboBox roomComboBox;
    @FXML
    public Text phRoomText;
    @FXML
    public Text phFirstNameText;
    @FXML
    public Text phLastNameText;
    @FXML
    public Text phNightsText;
    @FXML
    public Text phArrivalDateText;
    @FXML
    public Text phDepartureDateText;
    @FXML
    public Text phPersonNrText;
    @FXML
    public Text phBoardText;
    @FXML
    public Text phRoomPriceText;
    @FXML
    public Text phPaymentMethodText;
    @FXML
    public Text phTotalAmountText;
    private ArrayList<BookedRoomDTO> allBookedRoomDTOs;
    private ArrayList<CustomerDTO> currentCustomerDTOs;


    private CheckOutViewController viewController;

    public void setController(CheckOutViewController viewController){
        this.viewController = viewController;
    }

    protected void fillData(){
        if(viewController.getUseCaseController().getBooking() != null){
            BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();
            RoomDTO selectedRoom = bookingDTO.getBookedRooms().get(0).getRoom();
            roomComboBox.setConverter(new RoomNumberConverter(new RoomProvider()));
            roomComboBox.setValue(selectedRoom);
            setTexts(bookingDTO);
        }
    }

    private void saveData(){
        RoomDTO roomDTO = (RoomDTO) roomComboBox.getSelectionModel().getSelectedItem();

        BookingDTO myBookingDTO = getBookingFromRoom(roomDTO);
        viewController.getUseCaseController().setBooking(myBookingDTO);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //hide Placeholder Text
        phRoomText.setText("");
        phFirstNameText.setText("");
        phLastNameText.setText("");
        phNightsText.setText("");
        phArrivalDateText.setText("");
        phDepartureDateText.setText("");
        phPersonNrText.setText("");
        phBoardText.setText("");
        phRoomPriceText.setText("");
        phPaymentMethodText.setText("");
        phTotalAmountText.setText("");

        allBookedRoomDTOs = DomainController.getBookedRoomsBetween(LocalDate.now(), LocalDate.now());
        currentCustomerDTOs = new ArrayList<>();
        ArrayList<RoomDTO> rooms = new ArrayList<>();

        for (BookedRoomDTO bookedRoom : allBookedRoomDTOs) {
            BookingDTO bookingDTO = bookedRoom.getBooking();
            if (bookingDTO.getCheckOutDatetime() == null) {
                currentCustomerDTOs.add(bookingDTO.getCustomer());
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
        ArrayList<BookedRoomDTO> bookedRoomDTOs = bookingDTO.getBookedRooms();
        for(BookedRoomDTO bookedRoomDTO : bookedRoomDTOs){
            rooms.append(bookedRoomDTO.getRoom().getNumber()).append("   ");
        }
        phRoomText.setText(rooms.toString());

        phFirstNameText.setText(bookingDTO.getCustomer().getFirstName());
        phLastNameText.setText(bookingDTO.getCustomer().getLastName());

        int nights = (int) DAYS.between(bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate());
        phNightsText.setText(String.valueOf(nights));

        LocalDate arrivalDate = bookingDTO.getArrivalDate();
        String formattedArrivalDate = arrivalDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        phArrivalDateText.setText(formattedArrivalDate);

        LocalDate departureDate = bookingDTO.getDepartureDate();
        String formattedDepartureDate = departureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        phDepartureDateText.setText(formattedDepartureDate);

        phPersonNrText.setText(String.valueOf(bookingDTO.getAmountGuests()));
        phBoardText.setText(bookingDTO.getBoard().getName());

        if(Objects.equals(bookingDTO.getBookedRoomCategories().get(0).getPricePerNight(), new BigDecimal(0))){
            phRoomPriceText.setText("Price-0");
        }
        else{
            phRoomPriceText.setText("Standard price");
        }

        phPaymentMethodText.setText(bookingDTO.getPaymentMethod());
        BigDecimal price = calculateTotalPrice(bookingDTO, bookedRoomDTOs);
        phTotalAmountText.setText(price + " €");
    }

    private BigDecimal calculateTotalPrice(BookingDTO bookingDTO, ArrayList<BookedRoomDTO> bookedRoomDTOs){
        int totalNights = (int) DAYS.between(bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate());

        BigDecimal totalPrice = new BigDecimal(0);

        for (BookedRoomCategoryDTO c : bookingDTO.getBookedRoomCategories()) {
            BigDecimal price = c.getPricePerNight().multiply(new BigDecimal(c.getAmount())).multiply(BigDecimal.valueOf(totalNights));
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
    private void onCancelButtonClicked(ActionEvent actionEvent) throws IOException {
        MainApplication.getMainController().loadIntoContentArea("home");
    }

    @FXML
    private void onNextButtonClicked(ActionEvent actionEvent) throws IOException {
        saveData();
        viewController.loadCheckOut2();
    }
}