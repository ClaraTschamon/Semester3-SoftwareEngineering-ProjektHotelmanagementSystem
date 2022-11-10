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

//TODO: Package fehlt in Buchung

public class WalkIn1ViewController implements Initializable {

    ObservableList<String> selectedCategoriesList;

    @FXML
    private ComboBox<String> roomCategories;


    //https://stackoverflow.com/questions/41229964/how-to-check-and-uncheck-all-items-when-checking-or-unckeck-some-of-the-items
    private ObservableList<String> selectedRooms;

    @FXML
    private Text chooseRoom;

    @FXML
    private DatePicker departureDate;

    @FXML
    private Text room;

    @FXML
    private Text roomPrice;
    private WalkInUseCaseController useCaseController;



    @FXML
    private void onCancelButtonClicked(ActionEvent e) {
        try {
            useCaseController.cancel();
            MainApplication.getMainController().loadIntoContentArea("home");
        } catch (IOException | URISyntaxException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void onNextButtonClicked(ActionEvent e) {
        try {
            useCaseController.getBooking().setDepartureDate(departureDate.getValue());

            // TODO fill all attributes
            MainApplication.getMainController().loadIntoContentArea("walk-in-2");
            WalkIn2ViewController walkIn2ViewController = MainApplication.getMainController().getCurrentFXMLLoader().getController();
            walkIn2ViewController.setUseCaseController(useCaseController);
        } catch (IOException | URISyntaxException exc) {
            System.out.println(exc.getMessage());
        }
    }

    /*
    @FXML
    private void fillRooms(MouseEvent e){


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



        ObservableList<String> selectedCategoriesList = roomCategoryDropdown.getCheckModel().getCheckedItems();

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
                        rooms.add(roomNumber + " " + allRooms.get(i).getCategory().getName());
                    }
                }
            }
            Collections.sort(rooms);
            roomNumberDropdown.getItems().setAll(rooms);
        }

    }*/


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        useCaseController = new WalkInUseCaseController();

        //fill Room Categories DropDown
        ArrayList<RoomCategory> allRoomCategories = RoomCategoryDataMapper.getAll();
        ObservableList<String> categoryNames = FXCollections.observableArrayList();
        for(RoomCategory roomCategory : allRoomCategories) {
            categoryNames.add(roomCategory.getName());
        }
        //roomCategoryDropdown.getItems().setAll(categoryNames);
    }
}
