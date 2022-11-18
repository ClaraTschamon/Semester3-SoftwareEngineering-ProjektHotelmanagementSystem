package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomDTO;
import com.fhv.hotelmanagement.view.DTOs.RoomDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CheckOutViewController implements Initializable {

    public ComboBox roomComboBox;

    public void loadCheckOut() throws IOException {
        FXMLLoader checkOutLoader = MainApplication.getMainController().loadIntoContentArea("check-out");
        fillData();
    }

    protected void fillData(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<BookedRoomDTO> items = MainApplication.getDomainManager().getAllBookedRoomDTOs();
        ObservableList<BookedRoomDTO> bookedRoomDTOS = FXCollections.observableList(items);
        roomComboBox.getItems().addAll(bookedRoomDTOS);
        //roomComboBox.setConverter(new RoomNumberConverter());

    }
}