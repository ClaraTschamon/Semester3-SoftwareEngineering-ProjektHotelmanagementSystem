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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

//TODO: Package fehlt in Buchung

public class WalkIn1ViewController implements Initializable {

    @FXML
    private CheckComboBox<String> roomCategoryDropdown;

    ObservableList<String> selectedCategoriesList;

    @FXML
    private ComboBox<String> roomCategories;

    @FXML
    private CheckComboBox<String> roomNumberDropdown;


    //https://stackoverflow.com/questions/41229964/how-to-check-and-uncheck-all-items-when-checking-or-unckeck-some-of-the-items
    private ObservableList<String> selectedRooms;

    @FXML
    private Text chooseRoom;

    @FXML
    private DatePicker departureDatePicker;

    @FXML
    private Text room;

    @FXML
    private Text roomPrice;
    private WalkInUseCaseController useCaseController;

    public void setUseCaseController(WalkInUseCaseController useCaseController) {
        this.useCaseController = useCaseController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //fill Room Categories DropDown
        ArrayList<RoomCategory> allRoomCategories = RoomCategoryDataMapper.getAll();
        ObservableList<String> categoryNames = FXCollections.observableArrayList();
        for(RoomCategory roomCategory : allRoomCategories) {
            categoryNames.add(roomCategory.getName());
        }
        roomCategoryDropdown.getItems().setAll(categoryNames);
    }

    public void fillData() {
        LocalDate departureDate = useCaseController.getBooking().getDepartureDate();
        if (departureDate != null) {
            departureDatePicker.setValue(departureDate);
        } else {
            departureDatePicker.setValue(LocalDate.now());
        }
    }

    @FXML
    private void onNextButtonClicked(ActionEvent e) {
        try {
            useCaseController.getBooking().setDepartureDate(departureDatePicker.getValue());

            // TODO fill all attributes
            useCaseController.loadWalkIn2();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void onCancelButtonClicked(ActionEvent e) {
        try {
            useCaseController.cancel();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void fillRooms(MouseEvent e){
        /*ich brauche hier hilfe!!!*/
        System.out.println("fill rooms event");
        selectedRooms = roomNumberDropdown.getCheckModel().getCheckedItems();
        roomNumberDropdown.getItems().setAll(selectedRooms);
        roomNumberDropdown.getCheckModel().checkAll();
        //problem ist dass room unten wieder neu eingefügt wird

        selectedCategoriesList = roomCategoryDropdown.getCheckModel().getCheckedItems();

        if(!selectedCategoriesList.isEmpty()) {

            ArrayList<Room> allRooms = RoomDataMapper.getAll();
            ObservableList<String> rooms = FXCollections.observableArrayList();
            String room;
            int roomNumber;

            for (int i = 0; i < allRooms.size(); i++) {
                Room current = allRooms.get(i);
                room = current.getCategory().getName();
                roomNumber = allRooms.get(i).getNumber();
                if(selectedCategoriesList.contains(room)){
                    if(current.getIsFree()){
                        if(!roomNumberDropdown.getItems().contains(current.getNumber() + current.getCategory().getName())){
                            rooms.add(roomNumber + " " + allRooms.get(i).getCategory().getName());
                        }
                    }
                }
            }
            Collections.sort(rooms);
            roomNumberDropdown.getItems().setAll(rooms); //ist setAll() das Problem?
            //wahrscheinlich wird durch setAll() alles überschrieben
        }

    }
}
