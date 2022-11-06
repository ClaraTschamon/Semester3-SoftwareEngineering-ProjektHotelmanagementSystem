package com.fhv.hotelmanagement.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class WalkIn1ViewController {

    @FXML
    private Text chooseRoom;

    @FXML
    private DatePicker departureDate;

    @FXML
    private Text room;

    @FXML
    private Text roomPrice;
    private WalkInUseCaseController useCaseController;

    //package fehlt

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        useCaseController = new WalkInUseCaseController();
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
