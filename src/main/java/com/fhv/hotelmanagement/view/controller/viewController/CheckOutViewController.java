package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.domain.domainModel.Room;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.DTOs.RoomDTO;
import com.fhv.hotelmanagement.view.controller.useCaseController.CheckOutUseCaseController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    public Text paymentMethodText;
    @FXML
    public Text totalPriceText;

    public ComboBox roomComboBox;

    public CheckOutViewController(){
        useCaseController = new CheckOutUseCaseController();
    }

    protected void fillData(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allBookedRoomDTOs = MainApplication.getDomainManager().getAllBookedRoomDTOs();
        ArrayList<RoomDTO> rooms = new ArrayList<>();

        for(BookedRoomDTO bookedRoom : allBookedRoomDTOs){
            rooms.add(bookedRoom.getRoom());
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

    @FXML
    public void onCancelButtonClicked(ActionEvent actionEvent) throws IOException {
        //useCaseController.cancel();
        MainApplication.getMainController().loadIntoContentArea("home");
    }

    @FXML
    public void onConfirmButtonClicked(ActionEvent actionEvent) throws IOException {
        //useCaseController.save();
        MainApplication.getMainController().loadIntoContentArea("home");

    }

    private BookingDTO getBookingFromRoom(RoomDTO room){
        BookedRoomDTO myRoom = new BookedRoomDTO();
        for(BookedRoomDTO bookedRoom : allBookedRoomDTOs){
            if(bookedRoom.getRoom().getNumber() == room.getNumber()){
                myRoom = bookedRoom;
            }
        }
        return myRoom.getBooking();
    }

    private void setTexts(BookingDTO bookingDTO){
        StringBuilder rooms = new StringBuilder();
        rooms.append("Zimmer: ");
        ArrayList<BookedRoomDTO> bookedRoomDTOs = bookingDTO.getBookedRooms();
        for(BookedRoomDTO bookedRoomDTO : bookedRoomDTOs){
            rooms.append(bookedRoomDTO.getRoom().getNumber()).append(" ");
        }
        roomText.setText(rooms.toString());

        firstNameText.setText(bookingDTO.getCustomer().getFirstName());
        lastNameText.setText(bookingDTO.getCustomer().getLastName());
    }
}