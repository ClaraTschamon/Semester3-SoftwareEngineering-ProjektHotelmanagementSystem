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
import java.util.HashMap;
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
    private Text chooseRoom;

    @FXML
    private DatePicker departureDatePicker;
    @FXML
    private Text room;

    @FXML
    private ComboBox roomPriceDropDown;

    private CheckComboBox<RoomDTO> singleRoomDropDown;
    private CheckComboBox<RoomDTO> doubleRoomDropDown;
    private CheckComboBox<RoomDTO> familyRoomDropDown;
    private CheckComboBox<RoomDTO> suiteDropDown;


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

        ArrayList<RoomDTO> bookedSingleRooms = new ArrayList<>(singleRoomDropDown.getCheckModel().getCheckedItems());
        ArrayList<RoomDTO> bookedDoubleRooms = new ArrayList<>(doubleRoomDropDown.getCheckModel().getCheckedItems());
        ArrayList<RoomDTO> bookedFamilyRooms = new ArrayList<>(familyRoomDropDown.getCheckModel().getCheckedItems());
        ArrayList<RoomDTO> bookedSuites = new ArrayList<>(suiteDropDown.getCheckModel().getCheckedItems());

        //save all roomno
        ArrayList<BookedRoomDTO> bookedRooms = new ArrayList<>();
        for (RoomDTO r : bookedSingleRooms) {
            bookedRooms.add(new BookedRoomDTO(bookingDTO, r, bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate()));
        }
        for (RoomDTO r : bookedDoubleRooms) {
            bookedRooms.add(new BookedRoomDTO(bookingDTO, r, bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate()));
        }
        for (RoomDTO r : bookedFamilyRooms) {
            bookedRooms.add(new BookedRoomDTO(bookingDTO, r, bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate()));
        }
        for (RoomDTO r : bookedSuites) {
            bookedRooms.add(new BookedRoomDTO(bookingDTO, r, bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate()));
        }
        bookingDTO.setBookedRooms(bookedRooms);

        HashMap<String, RoomCategoryDTO> roomCategories = MainApplication.getDomainManager().getAllRoomCategoryDTOs();
        ArrayList<BookedRoomCategoryDTO> bookedRoomCategories = new ArrayList<>();
        if (bookedSingleRooms.size() > 0) {
            RoomCategoryDTO roomCategoryDTO = roomCategories.get("Einzelzimmer");
            bookedRoomCategories.add(new BookedRoomCategoryDTO(bookingDTO, roomCategoryDTO, roomCategoryDTO.getPricePerNight(), bookedSingleRooms.size()));
        }
        if (bookedDoubleRooms.size() > 0) {
            RoomCategoryDTO roomCategoryDTO = roomCategories.get("Doppelzimmer");
            bookedRoomCategories.add(new BookedRoomCategoryDTO(bookingDTO, roomCategoryDTO, roomCategoryDTO.getPricePerNight(), bookedSingleRooms.size()));
        }
        if (bookedFamilyRooms.size() > 0) {
            RoomCategoryDTO roomCategoryDTO = roomCategories.get("Familienzimmer");
            bookedRoomCategories.add(new BookedRoomCategoryDTO(bookingDTO, roomCategoryDTO, roomCategoryDTO.getPricePerNight(), bookedSingleRooms.size()));
        }
        if (bookedSuites.size() > 0) {
            RoomCategoryDTO roomCategoryDTO = roomCategories.get("Suite");
            bookedRoomCategories.add(new BookedRoomCategoryDTO(bookingDTO, roomCategoryDTO, roomCategoryDTO.getPricePerNight(), bookedSingleRooms.size()));
        }
        bookingDTO.setBookedRoomCategories(bookedRoomCategories);

        //save all room prices
        roomDTO.setRoomPrice((String) roomPriceDropDown.getSelectionModel().getSelectedItem());
//        singleRoomDropDown.setConverter();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<RoomDTO> allRooms = MainApplication.getDomainManager().getAllRooms();
        ArrayList<BookedRoomDTO> allBookedRooms = MainApplication.getDomainManager().getAllBookedRooms();

        RoomProvider roomProvider = new RoomProvider(allRooms, allBookedRooms);


        singleRoomDropDown = new CheckComboBox<>(roomProvider.getAllRoomsFromCategory("Einzelzimmer"));
        doubleRoomDropDown = new CheckComboBox<>(roomProvider.getAllRoomsFromCategory("Doppelzimmer"));
        familyRoomDropDown = new CheckComboBox<>(roomProvider.getAllRoomsFromCategory("Familienzimmer"));
        suiteDropDown = new CheckComboBox<>(roomProvider.getAllRoomsFromCategory("Suite"));

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

    private ArrayList<RoomDTO> allRooms;
    private ArrayList<BookedRoomDTO> allBookedRooms; //darf ich das in den roomprovider???

    private ArrayList<BookedRoomDTO> freeBookedRooms;

    private LocalDate minDate = LocalDate.now().minusDays(1); //was nimmt man als minDate???
    private LocalDate maxDate = LocalDate.now();



    public RoomProvider(ArrayList<RoomDTO> allRooms, ArrayList<BookedRoomDTO> allBookedRooms){
        this.allRooms = allRooms;
        this.allBookedRooms = allBookedRooms;
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

        freeBookedRooms = getCheckoutDateToday(maxDate);

        ObservableList<RoomDTO> freeRooms = FXCollections.observableArrayList(new ArrayList<>());

        for(RoomDTO room : allRooms){
            if(room.getCategory().getName().equals(category)){
                if(room.getIsFree()){
                    freeRooms.add(room);
                }
                else{
                    for(BookedRoomDTO bookedRoom : freeBookedRooms){
                        if(bookedRoom.getRoom().getNumber() == room.getNumber()){
                            freeRooms.add(room);
                        }
                    }
                }
            }
        }
        return freeRooms;
    }

    public ArrayList<BookedRoomDTO> getCheckoutDateToday(LocalDate maxDate){
        ArrayList<BookedRoomDTO> bookedRooms = new ArrayList<>();
        for(BookedRoomDTO bookedRoom : allBookedRooms){
            if(bookedRoom.getToDate().isEqual(maxDate) || bookedRoom.getToDate().isBefore(maxDate)){
                bookedRooms.add(bookedRoom);
            }
        }
        return bookedRooms;
    }

    //diese Methode funktioniert nicht!!!
    public ArrayList<BookedRoomDTO> getBookedRoomsBetween(LocalDate minDate, LocalDate maxDate){
        ArrayList<BookedRoomDTO> bookedRooms = new ArrayList<>();
        for(BookedRoomDTO bookedRoom : allBookedRooms){
            if((bookedRoom.getToDate().isEqual(maxDate) || bookedRoom.getToDate().isBefore(maxDate)) &&
                    (bookedRoom.getFromDate().isEqual(minDate) || bookedRoom.getFromDate().isAfter(minDate) )){ //TODO: fehler in dieser Zeile!!!
                bookedRooms.add(bookedRoom);
            }
        }
        return bookedRooms;
    }


}



class RoomNumberConverter<T> extends StringConverter<RoomDTO> {

    //javax.swing.ImageIcon icon = new ImageIcon("resources/Broom.png");

    LocalDate minDate = LocalDate.now(); //was nimmt man als minDate???
    LocalDate maxDate = LocalDate.now();
    ArrayList<BookedRoomDTO> bookedRooms = getBookedRoomsBetween(minDate, maxDate);
    ArrayList<RoomDTO> rooms = new ArrayList<>();

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

        if(!room.getIsClean()){
            return String.valueOf(room.getNumber() + " not clean!");
        }
        return String.valueOf(room.getNumber());
    }

    public ArrayList<BookedRoomDTO> getBookedRoomsBetween(LocalDate minDate, LocalDate maxDate){
        ArrayList<BookedRoomDTO> bookedRooms = new ArrayList<>();
        for(BookedRoomDTO bookedRoom : RoomProvider.allBookedRooms){
            if(bookedRoom.getFromDate().isAfter(minDate.minusDays(1)) && bookedRoom.getToDate().isBefore(maxDate.plusDays(1))){
                bookedRooms.add(bookedRoom);
            }
        }
        return bookedRooms;
    }
}
