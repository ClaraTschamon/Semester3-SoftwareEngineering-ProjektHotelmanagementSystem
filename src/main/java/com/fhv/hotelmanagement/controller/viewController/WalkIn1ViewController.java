package com.fhv.hotelmanagement.controller.viewController;

import com.fhv.hotelmanagement.DTOs.RoomCategoryDTO;
import com.fhv.hotelmanagement.DTOs.RoomDTO;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WalkIn1ViewController implements Initializable {
    public AnchorPane contentPane;
    public Text counterSingleRoom;
    public Text counterDoubleRoom;
    public Text counterFamilyRoom;
    public Text counterSuite;
    public Button NextButton;
    public Button CancelButton;
    public ComboBox roomPriceDropDown;

    @FXML
    private DatePicker departureDate;

    private WalkInViewController viewController;

    public void setController(WalkInViewController viewController) {
        this.viewController = viewController;
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

        doubleRoomDropDown.setLayoutX(680);
        doubleRoomDropDown.setLayoutY(250);
        doubleRoomDropDown.setPrefHeight(40);
        doubleRoomDropDown.setPrefWidth(100);

        familyRoomDropDown.setLayoutX(680);
        familyRoomDropDown.setLayoutY(300);
        familyRoomDropDown.setPrefHeight(40);
        familyRoomDropDown.setPrefWidth(100);

        suiteDropDown.setLayoutX(680);
        suiteDropDown.setLayoutY(350);
        suiteDropDown.setPrefHeight(40);
        suiteDropDown.setPrefWidth(100);


        singleRoomDropDown.setConverter(new RoomNumberConverter<>(roomProvider));
        doubleRoomDropDown.setConverter(new RoomNumberConverter<>(roomProvider));
        familyRoomDropDown.setConverter(new RoomNumberConverter<>(roomProvider));
        suiteDropDown.setConverter(new RoomNumberConverter<>(roomProvider));

        contentPane.getChildren().add(singleRoomDropDown);
        contentPane.getChildren().add(doubleRoomDropDown);
        contentPane.getChildren().add(familyRoomDropDown);
        contentPane.getChildren().add(suiteDropDown);
    }

    public void fillData() {
        
    }
}
class RoomProvider{
    static ArrayList<RoomDTO> allRooms;

    public RoomProvider(ArrayList<RoomDTO> allRooms){
        RoomProvider.allRooms = allRooms;
    }

    public RoomDTO getRoomFromNumber(int number){
        for(RoomDTO room : allRooms){
            if(room.getNumber() == number){
                return room;
            }
        }
        return null;
    }

    public ObservableList<RoomDTO> getAllRoomsFromCategory(String category) {
        ObservableList<RoomDTO> rooms = null;

        for(RoomDTO room : allRooms){
            if(room.getCategory().getName().equals(category)){
                rooms.add(room);
            }
        }
        return rooms;
    }
}



class RoomNumberConverter<T> extends StringConverter<RoomDTO>{

    RoomProvider provider;
    public RoomNumberConverter(RoomProvider provider){
        this.provider = provider;
    }
    @Override
    public RoomDTO fromString(final String number) {
        return provider.getRoomFromNumber(Integer.valueOf(number));
    }

    @Override
    public String toString(final RoomDTO room) {
        if (room == null) {
            return null;
        }
        return String.valueOf(room.getNumber());
    }
}
