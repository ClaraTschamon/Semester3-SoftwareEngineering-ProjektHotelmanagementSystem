package com.fhv.hotelmanagement.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
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
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WalkIn1ViewController implements Initializable {

    @FXML
    private ComboBox<String> roomCategoryDropdown;

    @FXML
    private FXCollections roomCategories;

    @FXML
    private ComboBox<String> roomNumberDropdown;

    @FXML
    public ComboBox<String> roomPriceDD;

    @FXML
    private Text chooseRoom;

    @FXML
    private Text departureDate;

    @FXML
    private Text room;

    @FXML
    private Text roomPrice;

    //package fehlt

    @FXML
    private void onRoomCategoryDDClicked(ActionEvent e){
        roomCategoryDropdown.commitValue();
    }

    @FXML
    private void onCancelButtonClicked(ActionEvent e){
        try{
            MainApplication.getMainController().loadIntoContentArea("home");
        }catch(IOException | URISyntaxException exc){
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void onNextButtonClicked(ActionEvent e){
        try{
            MainApplication.getMainController().loadIntoContentArea("walk-in-2");
        }catch(IOException | URISyntaxException exc){
            System.out.println(exc.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set RoomCategory DropDown
        ArrayList<RoomCategory> allRoomCategories = RoomCategoryDataMapper.getAll();
        ObservableList<String> names = FXCollections.observableArrayList();
        for(RoomCategory roomCategory : allRoomCategories) {
            names.add(roomCategory.getName());
        }
        roomCategoryDropdown.setItems(names);


        //set RoomNumber DropDown
        /*TODO: nur die freien Zimmer sollen angezeigt werden und außerdem
        nur die Zimmer der richtigen kategorie
         */


        ArrayList<Room> allRooms = RoomDataMapper.getAll();
        ObservableList<String> rooms = FXCollections.observableArrayList();
        String selectedRoomCategory = roomCategories.toString();
        System.out.println("Selected Category: " + selectedRoomCategory);
        for(Room room : allRooms) {
            if(room.getCategory().getName().equals(selectedRoomCategory)){
                rooms.add(Integer.toString(room.getNumber()));
            }
        }
        roomNumberDropdown.setItems(rooms);
    }
}
