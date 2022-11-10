package com.fhv.hotelmanagement.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.controller.useCaseController.WalkInUseCaseController;
import com.fhv.hotelmanagement.domainModel.Room;
import com.fhv.hotelmanagement.domainModel.RoomCategory;
import com.fhv.hotelmanagement.persistence.dataMapper.RoomCategoryDataMapper;
import com.fhv.hotelmanagement.persistence.dataMapper.RoomDataMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class WalkIn1ViewController implements Initializable {

    @FXML
    CheckComboBox<Integer> singleRoomDropDown;

    @FXML
    CheckComboBox<Integer> doubleRoomDropDown;

    @FXML
    CheckComboBox<Integer> familyRoomDropDown;

    @FXML
    CheckComboBox<Integer> suiteDropDown;

    @FXML
    private Text chooseRoom;

    @FXML
    private DatePicker departureDate;

    @FXML
    private Text room;

    @FXML
    private Text roomPrice;

    private WalkInViewController viewController;

    public void setController(WalkInViewController viewController) {
        this.viewController = viewController;
    }

    protected void fillData() {

    }

    @FXML
    private void onCancelButtonClicked(ActionEvent e) {
        try {
            viewController.cancel();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void onNextButtonClicked(ActionEvent e) {
        try {
            viewController.getUseCaseController().getBooking().setDepartureDate(departureDate.getValue());

            // TODO fill all attributes
            viewController.loadWalkIn2();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Room> allRooms = RoomDataMapper.getAll();
        ObservableList<Integer> numbersSingleRoom = FXCollections.observableArrayList();
        ObservableList<Integer> numbersDoubleRoom = FXCollections.observableArrayList();
        ObservableList<Integer> numbersFamilyRoom = FXCollections.observableArrayList();
        ObservableList<Integer> numbersSuite = FXCollections.observableArrayList();

        for(Room room : allRooms){
            switch(room.getCategory().getName()){
                case "Einzelzimmer":
                    numbersSingleRoom.add(room.getNumber());
                    break;
                case "Doppelzimmer":
                    numbersDoubleRoom.add(room.getNumber());
                    break;
                case "Familienzimmer":
                    numbersFamilyRoom.add(room.getNumber());
                    break;
                case "Suite":
                    numbersSuite.add(room.getNumber());
                    break;
            }
        }

        singleRoomDropDown.getItems().setAll(numbersSingleRoom);
        doubleRoomDropDown.getItems().setAll(numbersDoubleRoom);
        familyRoomDropDown.getItems().setAll(numbersFamilyRoom);
        suiteDropDown.getItems().setAll(numbersSuite);

        System.out.println("single:" + singleRoomDropDown.getItems().toString());
        System.out.println("double:" + doubleRoomDropDown.getItems().toString());
        System.out.println("family:" + familyRoomDropDown.getItems().toString());
        System.out.println("suite:" + suiteDropDown.getItems().toString());
    }
}
