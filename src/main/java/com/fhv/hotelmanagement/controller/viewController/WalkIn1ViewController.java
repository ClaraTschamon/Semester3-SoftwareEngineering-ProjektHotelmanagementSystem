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
import java.util.ResourceBundle;

//TODO: Package fehlt in Buchung

public class WalkIn1ViewController implements Initializable {

    @FXML
    private CheckComboBox<String> roomCategoryDropdown;
    private ObservableList selectedCategoriesList;

    @FXML
    private ComboBox<String> roomCategories;

    @FXML
    private CheckComboBox<String> roomNumberDropdown;

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

    @FXML
    public void onCategorySelected(MouseEvent mouseEvent) {
        //fehler!!! HILFE!!!!

        selectedCategoriesList = roomCategoryDropdown.getCheckModel().getCheckedItems();

        /*for(Object o : selectedCategoriesList){
            System.out.println(o.toString());
            selectedCategoriesList.add(o);
        }*/
    }


    private void fillRooms(){
        //set RoomNumber DropDown
        /*TODO: nur die freien Zimmer sollen angezeigt werden und außerdem
        nur die Zimmer der richtigen kategorie
         */
        ArrayList<Room> allRooms = RoomDataMapper.getAll();
        ObservableList<String> rooms = FXCollections.observableArrayList();
        String room;
        String category;

        for(int i = 0; i < allRooms.size(); i++){
            room = allRooms.get(i).getCategory().getName();
            category = selectedCategoriesList.get(i).toString();
            if(room.equals(category)){
                rooms.add(room + " " + category);
            }
        }
        roomNumberDropdown.getItems().setAll(rooms);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        useCaseController = new WalkInUseCaseController();

        //fill Room Categories DropDown
        ArrayList<RoomCategory> allRoomCategories = RoomCategoryDataMapper.getAll();
        ObservableList<String> categoryNames = FXCollections.observableArrayList();
        for(RoomCategory roomCategory : allRoomCategories) {
            categoryNames.add(roomCategory.getName());
        }
        roomCategoryDropdown.getItems().setAll(categoryNames);
    }
}
