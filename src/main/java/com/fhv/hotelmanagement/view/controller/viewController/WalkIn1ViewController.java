package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.domain.domainModel.BookedRoom;
import com.fhv.hotelmanagement.domain.domainModel.Room;
import com.fhv.hotelmanagement.persistence.dataMapper.BookedRoomDataMapper;
import com.fhv.hotelmanagement.persistence.dataMapper.RoomDataMapper;
import com.fhv.hotelmanagement.view.DTOs.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.controlsfx.control.CheckComboBox;

//import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class WalkIn1ViewController implements Initializable {

    @FXML
    RadioButton fullBoard;
    @FXML
    RadioButton halfBoard;
    @FXML
    RadioButton onlyBreakfast;
    @FXML
    RadioButton noPackage;
    @FXML
    public Text counterSingleRoom;
    @FXML
    public Text counterDoubleRoom;
    @FXML
    public Text counterFamilyRoom;
    @FXML
    public Text counterSuite;
    @FXML
    public AnchorPane contentPane;
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
    private DatePicker departureDatePicker;
    @FXML
    private Text room;

    @FXML
    private ComboBox roomPriceDropDown;

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
            saveData();
            viewController.loadWalkIn2();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected void fillData(){
        BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();

        LocalDate departureDate = bookingDTO.getDepartureDate();
        departureDatePicker.setValue(departureDate);

        //fill all package radio buttons
        BoardDTO packageDTO = viewController.getUseCaseController().getPackage();
        boolean fullboard = packageDTO.isFullboard();
        fullBoard.setSelected(fullboard);
        boolean halfboard = packageDTO.isHalfboard();
        halfBoard.setSelected(halfboard);
        boolean nopackage = packageDTO.isNoPackage();
        noPackage.setSelected(nopackage);
        boolean onlybreakfast = packageDTO.isOnlyBreakfast();
        onlyBreakfast.setSelected(onlybreakfast);

        //fill all room counters
        RoomDTO roomDTO = viewController.getUseCaseController().getRoomDTO();
        int singleRoom =  roomDTO.getCounterSingleRoom();
        counterSingleRoom.setText(String.valueOf(singleRoom));
        int doubleRoom =  roomDTO.getCounterDoubleRoom();
        counterDoubleRoom.setText(String.valueOf(doubleRoom));
        int familyRoom =  roomDTO.getCounterFamilyRoom();
        counterFamilyRoom.setText(String.valueOf(familyRoom));
        int suite =  roomDTO.getCounterSuite();
        counterSuite.setText(String.valueOf(suite));

        //fill all roomno
//        int roomno =roomDTO.getNumber();
        //fill room price
        String roomPrice = roomDTO.getRoomPrice();
        roomPriceDropDown.setValue(roomPrice);

        System.out.println(roomPriceDropDown.getEditor().getText());
    }

    protected void saveData(){
        BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();
        bookingDTO.setDepartureDate(departureDatePicker.getValue());

        //save all package radio buttons
        BoardDTO packageDTO = viewController.getUseCaseController().getPackage();
        packageDTO.setFullboard(fullBoard.isSelected());
        packageDTO.setHalfboard(halfBoard.isSelected());
        packageDTO.setOnlyBreakfast(onlyBreakfast.isSelected());
        packageDTO.setNoPackage(noPackage.isSelected());

        //save all room counters
        RoomDTO roomDTO = viewController.getUseCaseController().getRoomDTO();
        roomDTO.setCounterSingleRoom(Integer.parseInt(counterSingleRoom.getText()));
        roomDTO.setCounterDoubleRoom(Integer.parseInt(counterDoubleRoom.getText()));
        roomDTO.setCounterFamilyRoom(Integer.parseInt(counterFamilyRoom.getText()));
        roomDTO.setCounterSuite(Integer.parseInt(counterSuite.getText()));

        //save all roomno


        //save all room prices
        roomDTO.setRoomPrice((String) roomPriceDropDown.getSelectionModel().getSelectedItem());
//        singleRoomDropDown.setConverter();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //woher hat roomDTO die informationen???
        /*
        ArrayList<RoomDTO> allRooms = viewController.getUseCaseController().getRoomDTO().getAllRooms();
        System.out.println(allRooms);
        */

        //nur zum testen
        ArrayList<RoomDTO> allRooms = new ArrayList<>();
        RoomCategoryDTO singleroomcategoy = new RoomCategoryDTO();
        singleroomcategoy.setName("Einzelzimmer");
        RoomDTO room1 = new RoomDTO();
        room1.setNumber(1);
        room1.setClean(false);
        room1.setCategory(singleroomcategoy);
        allRooms.add(room1);


//        System.out.println(singleRoomDropDown.());
        RoomProvider roomProvider = new RoomProvider(allRooms);


        final CheckComboBox<RoomDTO> singleRoomDropDown = new CheckComboBox<>(roomProvider.getAllRoomsFromCategory("Einzelzimmer"));
        final CheckComboBox<RoomDTO> doubleRoomDropDown = new CheckComboBox<>(roomProvider.getAllRoomsFromCategory("Doppelzimmer"));
        final CheckComboBox<RoomDTO> familyRoomDropDown = new CheckComboBox<>(roomProvider.getAllRoomsFromCategory("Familienzimmer"));
        final CheckComboBox<RoomDTO> suiteDropDown = new CheckComboBox<>(roomProvider.getAllRoomsFromCategory("Suite"));

        singleRoomDropDown.getCheckModel().getCheckedItems().addListener(new ListChangeListener<RoomDTO>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends RoomDTO> c) {
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

    private boolean validateFields() {
        return true;
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
        ObservableList<RoomDTO> rooms = FXCollections.observableArrayList(new ArrayList<>());

        for(RoomDTO room : allRooms){
            if(room.getCategory().getName().equals(category)){
                rooms.add(room);
            }
        }
        return rooms;
    }
}



class RoomNumberConverter<T> extends StringConverter<RoomDTO> {

    //javax.swing.ImageIcon icon = new ImageIcon("resources/Broom.png");

    LocalDate minDate = LocalDate.now(); //was nimmt man als minDate???
    LocalDate maxDate = LocalDate.now();
//    ArrayList<BookedRoom> bookedRooms = BookedRoomDataMapper.instance().getBookedRoomsBetween(minDate, maxDate);
    ArrayList<BookedRoom> bookedRooms = new ArrayList<>();
    ArrayList<Room> rooms = new ArrayList<>();

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
        for(BookedRoom bookedRoom : bookedRooms){
            rooms.add(bookedRoom.getRoom());
            if(bookedRoom.getFromDate().equals(LocalDate.now())){
                if(!room.getIsClean()){
                    return String.valueOf(room.getNumber() + " not clean!");
                }
                return String.valueOf(room.getNumber());
            }
        }
        if(!rooms.contains(room)){
            if(!room.getIsClean()){
                return String.valueOf(room.getNumber() + " not clean!");
            }
            return String.valueOf(room.getNumber());
        }
        return String.valueOf(room.getNumber());
    }
}
