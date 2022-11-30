//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.domain.domainModel.Booking;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.controller.useCaseController.BookingOverviewUseCaseController;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingOverviewViewController implements Initializable {

    @FXML
    private ListView <BookingDTO> bookingsListView;
    @FXML
    public Text phBookingNumberText;
    @FXML
    private DatePicker toDateDatePicker;
    @FXML
    private DatePicker fromDateDatePicker;
    @FXML
    private Text arrivalDateText;
    @FXML
    private Text departureDateText;
    @FXML
    private Text phFirstnameText;
    @FXML
    private Text phLastnameText;
    @FXML
    private Text phStreetText;
    @FXML
    private Text phCityText;
    @FXML
    private Text phCountryText;
    @FXML
    private Text phPhoneNrText;
    @FXML
    private Text phPostalCodeText;
    @FXML
    private Text phHouseNrText;
    @FXML
    private Text phPaymentMethodText;
    @FXML
    private Text phStateText;
    @FXML
    private Text phRoomsText;
    @FXML
    private ComboBox stateComboBox;
    private BookingOverviewUseCaseController useCaseController;
    public BookingOverviewViewController(){
        useCaseController = new BookingOverviewUseCaseController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //TODO: Tabelle verwenden und nicht liste

        ObservableList<BookingDTO> allBookings = FXCollections.observableArrayList(DomainController.getAllBookings());
        for(BookingDTO bookingDTO : allBookings){
            bookingsListView.getItems().add(bookingDTO);
        }

        bookingsListView.getSelectionModel().select(0); //per default erstes Item auswählen
        System.out.println("selected Item = " + bookingsListView.getSelectionModel().getSelectedItem());
        setTexts(bookingsListView.getSelectionModel().getSelectedItem());

        bookingsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            BookingDTO selectedItem = (BookingDTO) bookingsListView.getSelectionModel().getSelectedItem();
            System.out.println("In Methode");
            setTexts(selectedItem);
        });
    }

    private void setTexts(BookingDTO bookingDTO){
        phBookingNumberText.setText(String.valueOf(bookingDTO.getNumber()));
    }
}
