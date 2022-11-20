package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.view.DTOs.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.controlsfx.control.CheckComboBox;

//import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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

    @FXML
    private ToggleGroup packageToggleGroup;

    private CheckComboBox<RoomDTO> singleRoomDropDown;
    private CheckComboBox<RoomDTO> doubleRoomDropDown;
    private CheckComboBox<RoomDTO> familyRoomDropDown;
    private CheckComboBox<RoomDTO> suiteDropDown;
    private WalkInViewController viewController;

    public void setController(WalkInViewController viewController) {
        this.viewController = viewController;
    }

    public boolean validate() {

        RadioButton selectedBoardButton =  (RadioButton) packageToggleGroup.getSelectedToggle();

        boolean departureDateIsValid = false;
        boolean counterRoomIsValid = false;
        boolean counterSingleRoomIsValid = false;
        boolean counterDoubleRoomIsValid = false;
        boolean counterFamilyRoomIsValid = false;
        boolean counterSuiteRoomIsValid = false;
        boolean boardButtonIsValid = false;
        boolean roomPriceDropDownIsValid = false;

        LocalDate departureDate = departureDatePicker.getValue();
        LocalDate todayDate = LocalDate.now();

        if (checkOutDate(todayDate, departureDate)) {
            departureDateIsValid = true;
        } else {
            departureDatePicker.setStyle("-fx-text-inner-color: red");
        }

        if (Integer.parseInt(counterSingleRoom.getText()) > 0) {
            counterSingleRoomIsValid = true;
        }
        else {
            setTextColorRed(counterSingleRoom);
        }

        if (Integer.parseInt(counterDoubleRoom.getText()) > 0) {
            counterDoubleRoomIsValid = true;
        } else {
            setTextColorRed(counterDoubleRoom);
        }

        if (Integer.parseInt(counterFamilyRoom.getText()) > 0) {
            counterFamilyRoomIsValid = true;
        } else {
            setTextColorRed(counterFamilyRoom);
        }

        if (Integer.parseInt(counterSuite.getText()) > 0) {
            counterSuiteRoomIsValid = true;
        } else {
            setTextColorRed(counterSuite);
        }

        if (counterSingleRoomIsValid || counterDoubleRoomIsValid ||
                counterFamilyRoomIsValid || counterSuiteRoomIsValid) {
            counterRoomIsValid = true;
        } else {
            counterRoomIsValid = false;
        }

        if (selectedBoardButton.isSelected()) {
            boardButtonIsValid = true;
        } else {
            boardButtonIsValid = false;
        }

        if (roomPriceDropDown.getValue() != null) {
            roomPriceDropDownIsValid = true;
        } else {
            roomPriceDropDownIsValid = false;
        }

        if (departureDateIsValid && counterRoomIsValid &&
                boardButtonIsValid && roomPriceDropDownIsValid) {
            return true;
        }
        else return false;
    }

    private void setTextColorRed(Text text) {
        text.setFill(Color.RED);
    }

    private void setCounterRoomColor(Text text) {
        if (Integer.parseInt(text.getText()) > 0) {
        } else {
            setTextColorRed(text);
        }
    }

    public boolean checkOutDate(LocalDate dateOfToday, LocalDate dateOfCheckOut) {
        if (Period.between(dateOfToday, dateOfCheckOut).getDays() > 0) {
            return true;
        }
        else {
            return false;
        }
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
            if (validate()) {
                saveData();
                viewController.loadWalkIn2();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected void fillData(){

        BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();

        if(bookingDTO.getDepartureDate() != null){
            departureDatePicker.setValue(bookingDTO.getDepartureDate());
        }
        else{
            LocalDate defaultDepartureDate = LocalDate.now().plusDays(1);
            departureDatePicker.setValue(defaultDepartureDate);
        }

        int amountBookedSingleRooms = 0;
        int amountBookedDoubleRooms = 0;
        int amountBookedFamilyRooms = 0;
        int amountBookedSuites = 0;
        if (bookingDTO.getBookedRooms() != null) {
            for (BookedRoomDTO bookedRoomDTO : bookingDTO.getBookedRooms()) {
                String categoryName = bookedRoomDTO.getRoom().getCategory().getName();
                if (categoryName.equals("Einzelzimmer")) {
                    singleRoomDropDown.getCheckModel().check(bookedRoomDTO.getRoom());
                    amountBookedSingleRooms++;
                } else if (categoryName.equals("Doppelzimmer")) {
                    doubleRoomDropDown.getCheckModel().check(bookedRoomDTO.getRoom());
                    amountBookedDoubleRooms++;
                } else if (categoryName.equals("Familienzimmer")) {
                    familyRoomDropDown.getCheckModel().check(bookedRoomDTO.getRoom());
                    amountBookedFamilyRooms++;
                } else if (categoryName.equals("Suite")) {
                    suiteDropDown.getCheckModel().check(bookedRoomDTO.getRoom());
                    amountBookedSuites++;
                }
            }
        }
        counterSingleRoom.setText(String.valueOf(amountBookedSingleRooms));
        counterDoubleRoom.setText(String.valueOf(amountBookedDoubleRooms));
        counterFamilyRoom.setText(String.valueOf(amountBookedFamilyRooms));
        counterSuite.setText(String.valueOf(amountBookedSuites));

        String roomPrice = viewController.getUseCaseController().getRoomPrice();
        if (roomPrice != null) {
            roomPriceDropDown.setValue(roomPrice);
        }

        if (bookingDTO.getBoard() != null) {
            String selectedBoardName = bookingDTO.getBoard().getName();
            if (selectedBoardName.equals("Vollpension")) {
                fullBoard.setSelected(true);
            } else if (selectedBoardName.equals("Halbpension")) {
                halfBoard.setSelected(true);
            } else if (selectedBoardName.equals("Nur Frühstück")) {
                onlyBreakfast.setSelected(true);
            } else if (selectedBoardName.equals("Ohne Package")) {
                noPackage.setSelected(true);
            } else {
                fullBoard.setSelected(true);
            }
        } else {
            fullBoard.setSelected(true);
        }
    }

    protected void saveData(){
        BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();
        bookingDTO.setDepartureDate(departureDatePicker.getValue());

        //save all package radio buttons
        RadioButton selectedBoardButton = (RadioButton) packageToggleGroup.getSelectedToggle();
        BoardDTO selectedBoard = getBoardByName(selectedBoardButton.getText());
        bookingDTO.setBoard(selectedBoard);
        if (selectedBoard != null) {
            bookingDTO.setPricePerNightForBoard(selectedBoard.getPricePerNight());
        } else {
            bookingDTO.setPricePerNightForBoard(null);
        }

        // save booked rooms (TODO save other prices)
        ArrayList<RoomDTO> bookedSingleRooms = new ArrayList<>(singleRoomDropDown.getCheckModel().getCheckedItems());
        ArrayList<RoomDTO> bookedDoubleRooms = new ArrayList<>(doubleRoomDropDown.getCheckModel().getCheckedItems());
        ArrayList<RoomDTO> bookedFamilyRooms = new ArrayList<>(familyRoomDropDown.getCheckModel().getCheckedItems());
        ArrayList<RoomDTO> bookedSuites = new ArrayList<>(suiteDropDown.getCheckModel().getCheckedItems());

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
            bookedRoomCategories.add(new BookedRoomCategoryDTO(bookingDTO, roomCategoryDTO, roomCategoryDTO.getPricePerNight(), bookedDoubleRooms.size()));
        }
        if (bookedFamilyRooms.size() > 0) {
            RoomCategoryDTO roomCategoryDTO = roomCategories.get("Familienzimmer");
            bookedRoomCategories.add(new BookedRoomCategoryDTO(bookingDTO, roomCategoryDTO, roomCategoryDTO.getPricePerNight(), bookedFamilyRooms.size()));
        }
        if (bookedSuites.size() > 0) {
            RoomCategoryDTO roomCategoryDTO = roomCategories.get("Suite");
            bookedRoomCategories.add(new BookedRoomCategoryDTO(bookingDTO, roomCategoryDTO, roomCategoryDTO.getPricePerNight(), bookedSuites.size()));
        }
        bookingDTO.setBookedRoomCategories(bookedRoomCategories);

        viewController.getUseCaseController().setRoomPrice(roomPriceDropDown.getSelectionModel().getSelectedItem().toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<RoomDTO> allRooms = MainApplication.getDomainManager().getAllRoomDTOs();
        ArrayList<BookedRoomDTO> allBookedRooms = MainApplication.getDomainManager().getAllBookedRoomDTOs();

        RoomProvider roomProvider = new RoomProvider();

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

    private BoardDTO getBoardByName(String name) {
        for (BoardDTO boardDTO : MainApplication.getDomainManager().getAllBoardDTOs()) {
            if (boardDTO.getName().equals(name)) {
                return boardDTO;
            }
        }
        return null;
    }
}

class RoomProvider{

    private LocalDate minDate = LocalDate.now().minusDays(1); //was nimmt man als minDate???
    private LocalDate maxDate = LocalDate.now();
    private ArrayList<BookedRoomDTO> freeBookedRooms = getCheckoutDateToday(maxDate);

    public RoomDTO getRoomFromNumber(int number){
        for(RoomDTO room : MainApplication.getDomainManager().getAllRoomDTOs()){
            if(room.getNumber() == number){
                return room;
            }
        }
        return null;
    }

    public ObservableList<RoomDTO> getAllRoomsFromCategory(String category) {

        freeBookedRooms = getCheckoutDateToday(maxDate);

        ObservableList<RoomDTO> freeRooms = FXCollections.observableArrayList(new ArrayList<>());

        for(RoomDTO room : MainApplication.getDomainManager().getAllRoomDTOs()){
            if(room.getCategory().getName().equals(category)){
                if(room.getIsFree()){
                    freeRooms.add(room);
                }
                else{
                    for(BookedRoomDTO bookedRoom : freeBookedRooms){
                        if(bookedRoom.getRoom().equals(room)){
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
        for(BookedRoomDTO bookedRoom : MainApplication.getDomainManager().getBookedRoomsBetween(LocalDate.now(), maxDate)){
            if(bookedRoom.getToDate().isEqual(maxDate) || bookedRoom.getToDate().isBefore(maxDate)){
                bookedRooms.add(bookedRoom);
            }
        }
        return bookedRooms;
    }
}


class RoomNumberConverter<T> extends StringConverter<RoomDTO> {
    RoomProvider provider;



    public RoomNumberConverter(RoomProvider provider){
        this.provider = provider;
    }

    @Override
    public RoomDTO fromString(final String number) {
        return provider.getRoomFromNumber(Integer.parseInt(number));
    }

    @Override
    public String toString(final RoomDTO room) {
        if (room == null) {
            return null;
        }

        if(!room.getIsClean()){
            return String.valueOf(room.getNumber() + " " + "\u2757"); //TODO: warum wird raum 13 nicht angezeigt??
        }
        return String.valueOf(room.getNumber());
    }
}



