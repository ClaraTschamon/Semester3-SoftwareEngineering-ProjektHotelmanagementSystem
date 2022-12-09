//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.view.controller.useCaseController.ReservationOverviewUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReservationOverviewViewController implements Initializable {

    @FXML
    private DatePicker toDateDatePicker;
    @FXML
    private DatePicker fromDateDatePicker;
    @FXML
    private ComboBox stateComboBox;
    @FXML
    private TableView reservationTableView;
    @FXML
    private TableColumn reservationNrCol;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn arrivalDateCol;
    @FXML
    private TableColumn departureDateCol;
    @FXML
    private TableColumn stateCol;
    @FXML
    private TableColumn roomNrCol;
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
    private Text phReservationNumberText;

    private ReservationOverviewUseCaseController useCaseController;

    public ReservationOverviewViewController(){
        useCaseController = new ReservationOverviewUseCaseController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //hide weekNumbers in DatePicker
        fromDateDatePicker.setShowWeekNumbers(false);
        toDateDatePicker.setShowWeekNumbers(false);
        //set default Value on DatePicker
        fromDateDatePicker.setValue(LocalDate.now());
        toDateDatePicker.setValue(LocalDate.now().plusDays(1));
        //disable DatePicker on default because default state is "checked-in"
        fromDateDatePicker.setDisable(true);
        toDateDatePicker.setDisable(true);

    }

    public void onToDateDatePickerClicked(ActionEvent actionEvent) {
    }

    public void stateComboBoxAction(ActionEvent actionEvent) {
    }

    public void onFromDateDatePickerClicked(ActionEvent actionEvent) {
    }

    private void hidePlaceholderTexts(){
        phReservationNumberText.setText("");
        phStateText.setText("");
        phRoomsText.setText("");
        phFirstnameText.setText("");
        phLastnameText.setText("");
        phStreetText.setText("");
        phHouseNrText.setText("");
        phPostalCodeText.setText("");
        phCityText.setText("");
        phCountryText.setText("");
        phPhoneNrText.setText("");
        phPaymentMethodText.setText("");
    }
}
