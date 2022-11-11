package com.fhv.hotelmanagement.view.viewController.viewController;

import com.fhv.hotelmanagement.persistence.dataMapper.RoomDataMapper;
import com.fhv.hotelmanagement.view.DTOs.RoomCategoryDTO;
import com.fhv.hotelmanagement.view.DTOs.RoomDTO;
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
        //ArrayList<RoomDTO> allRooms = RoomDataMapper.getAll();

        //nur zum testen
        ArrayList<RoomDTO> allRooms = new ArrayList<>();
        RoomCategoryDTO singleroomcategoy = new RoomCategoryDTO();
        singleroomcategoy.setName("Einzelzimmer");
        RoomDTO room1 = new RoomDTO();
        room1.setNumber(1);
        room1.setCategory(singleroomcategoy);
        allRooms.add(room1);
        //

        RoomProvider roomProvider = new RoomProvider(allRooms);


        final CheckComboBox<RoomDTO> singleRoomDropDown = new CheckComboBox<>(roomProvider.getAllRoomsFromCategory("Einzelzimmer"));
        final CheckComboBox<RoomDTO> doubleRoomDropDown = new CheckComboBox<>(roomProvider.getAllRoomsFromCategory("Doppelzimmer"));
        final CheckComboBox<RoomDTO> familyRoomDropDown = new CheckComboBox<>(roomProvider.getAllRoomsFromCategory("Familienzimmer"));
        final CheckComboBox<RoomDTO> suiteDropDown = new CheckComboBox<>(roomProvider.getAllRoomsFromCategory("Suite"));

        singleRoomDropDown.getCheckModel().getCheckedItems().addListener(new ListChangeListener<RoomDTO>() {
            @Override
            public void onChanged(Change<? extends RoomDTO> c) {
                counterSingleRoom.setText(String.valueOf(singleRoomDropDown.getCheckModel().getCheckedItems().size()));
            }
        });
        doubleRoomDropDown.getCheckModel().getCheckedItems().addListener(new ListChangeListener<RoomDTO>() {
            @Override
            public void onChanged(Change<? extends RoomDTO> c) {
                counterDoubleRoom.setText(String.valueOf(doubleRoomDropDown.getCheckModel().getCheckedItems().size()));
            }
        });
        familyRoomDropDown.getCheckModel().getCheckedItems().addListener(new ListChangeListener<RoomDTO>() {
            @Override
            public void onChanged(Change<? extends RoomDTO> c) {
                counterFamilyRoom.setText(String.valueOf(familyRoomDropDown.getCheckModel().getCheckedItems().size()));
            }
        });
        suiteDropDown.getCheckModel().getCheckedItems().addListener(new ListChangeListener<RoomDTO>() {
            @Override
            public void onChanged(Change<? extends RoomDTO> c) {
                counterSuite.setText(String.valueOf(suiteDropDown.getCheckModel().getCheckedItems().size()));
            }
        });

        singleRoomDropDown.setLayoutX(680);
        singleRoomDropDown.setLayoutY(200);
        singleRoomDropDown.setPrefHeight(40);
        singleRoomDropDown.setPrefWidth(100);

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
