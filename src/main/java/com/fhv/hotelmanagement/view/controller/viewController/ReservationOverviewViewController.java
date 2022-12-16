//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.view.DTOs.AddressDTO;
import com.fhv.hotelmanagement.view.DTOs.CustomerDTO;
import com.fhv.hotelmanagement.view.DTOs.ReservationDTO;
import com.fhv.hotelmanagement.view.controller.useCaseController.ReservationOverviewUseCaseController;
import com.fhv.hotelmanagement.view.viewServices.ReservationViewBean;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
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
    private TableColumn<ReservationViewBean, Long> reservationNrCol;
    @FXML
    private TableColumn<ReservationViewBean, String> nameCol;
    @FXML
    private TableColumn<ReservationViewBean, LocalDate> arrivalDateCol;
    @FXML
    private TableColumn<ReservationViewBean, LocalDate> departureDateCol;
    @FXML
    private TableColumn<ReservationViewBean, Button> stateCol;
    @FXML
    private TableColumn<ReservationViewBean, ArrayList<Integer>> roomNrCol;
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

        //default state is set in .fxml to: 'all'
        String state = stateComboBox.getSelectionModel().getSelectedItem().toString();
        fillTable(state);

        stateComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String state = stateComboBox.getSelectionModel().getSelectedItem().toString();
                fillTable(state);
            }
        });
    }

    public void fillTable(String state){
        ObservableList<ReservationDTO> reservationDTOs = FXCollections.observableArrayList();
        if(state.equals("all")){
            reservationDTOs = FXCollections.observableArrayList(DomainController.getAllReservations());
        } else if(state.equals("confirmed")){
            //reservationDTOs = FXCollections.observableArrayList(DomainController.getConfirmedReservations());
            ObservableList<ReservationDTO> allreservationDTOs = FXCollections.observableArrayList(DomainController.getAllReservations());
            ArrayList<ReservationDTO> confirmedReservations = new ArrayList<>();
            for(ReservationDTO reservationDTO : allreservationDTOs){
                if(reservationDTO.getBooking() != null){
                    confirmedReservations.add(reservationDTO);
                }
            }
            reservationDTOs = FXCollections.observableArrayList(confirmedReservations);

        } else if(state.equals("not confirmed")){
            //reservationDTOs = FXCollections.observableArrayList(DomainController.getNotConfirmedReservations());
            ObservableList<ReservationDTO> allreservationDTOs = FXCollections.observableArrayList(DomainController.getAllReservations());
            ArrayList<ReservationDTO> notConfirmedReservations = new ArrayList<>();
            for(ReservationDTO reservationDTO : allreservationDTOs){
                if(reservationDTO.getBooking() == null){
                    notConfirmedReservations.add(reservationDTO);
                }
            }
            reservationDTOs = FXCollections.observableArrayList(notConfirmedReservations);
        } else if(state.equals("all between")){
            LocalDate minDate = fromDateDatePicker.getValue();
            LocalDate maxDate = toDateDatePicker.getValue();
            reservationDTOs = FXCollections.observableArrayList(DomainController.getAllReservationsBetween(minDate, maxDate));
        }
        ArrayList<ReservationViewBean> allReservationViewBeans = new ArrayList<>();
        for(ReservationDTO reservationDTO : reservationDTOs){
            ReservationViewBean reservation = new ReservationViewBean(reservationDTO);
            allReservationViewBeans.add(reservation);
        }
        ObservableList<ReservationViewBean> allReservations = FXCollections.observableArrayList(allReservationViewBeans);
        Comparator<ReservationViewBean> comparator = Comparator.comparing(ReservationViewBean :: getReservationNumber);
        comparator = comparator.reversed();
        allReservations.sort(comparator);

        reservationNrCol.setCellValueFactory(new PropertyValueFactory<ReservationViewBean, Long>("reservationNumber"));
        nameCol.setCellValueFactory(new PropertyValueFactory<ReservationViewBean, String>("lastName"));
        arrivalDateCol.setCellValueFactory(new PropertyValueFactory<ReservationViewBean, LocalDate>("arrivalDate"));
        arrivalDateCol.setStyle("-fx-alignment: CENTER");
        departureDateCol.setCellValueFactory(new PropertyValueFactory<ReservationViewBean, LocalDate>("departureDate"));
        departureDateCol.setStyle("-fx-alignment: CENTER");
        stateCol.setCellValueFactory(new PropertyValueFactory<ReservationViewBean, Button>("imageButton"));
        stateCol.setStyle("-fx-alignment: CENTER");
        roomNrCol.setCellValueFactory(new PropertyValueFactory<ReservationViewBean, ArrayList<Integer>>("roomNumbers"));

        if(allReservations.size() == 0){
            reservationTableView.setPlaceholder(new Label("No reservations"));
            reservationTableView.getItems().clear();
            hidePlaceholderTexts();
        } else {
            reservationTableView.setItems(allReservations);
        }

        if(reservationTableView.getSelectionModel().getTableView().getColumns().get(0) != null){
            reservationTableView.getSelectionModel().select(0); //per default erstes Item auswählen
            ReservationViewBean reservationViewBean = (ReservationViewBean) reservationTableView.getSelectionModel().getSelectedItem();
            if(reservationViewBean != null){
                setTexts(reservationViewBean.getReservationDTO());
            }
        }

        reservationTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(reservationTableView.getSelectionModel().getSelectedItem() != null){
                ReservationViewBean selectedItem = (ReservationViewBean) reservationTableView.getSelectionModel().getSelectedItem();
                ReservationDTO reservationDTO = selectedItem.getReservationDTO();
                setTexts(reservationDTO);
            }
        });

    }

    private void setTexts(ReservationDTO reservationDTO){
        CustomerDTO customerDTO = reservationDTO.getCustomer();
        AddressDTO addressDTO = customerDTO.getAddress();
        phReservationNumberText.setText(String.valueOf(reservationDTO.getNumber()));

        LocalDate arrivalDate = reservationDTO.getArrivalDate();
        String formattedArrivalDate = arrivalDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        arrivalDateText.setText(formattedArrivalDate);

        LocalDate departureDate = reservationDTO.getDepartureDate();
        String formattedDepartureDate = departureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        departureDateText.setText(formattedDepartureDate);

        if(reservationDTO.getBooking() == null){
            phStateText.setText("not confirmed");
        } else {
            phStateText.setText("confirmed");
        }

        phRoomsText.setText(reservationDTO.getReservedRooms().toString());
        phFirstnameText.setText(customerDTO.getFirstName());
        phLastnameText.setText(customerDTO.getLastName());
        phStreetText.setText(addressDTO.getStreet());
        phHouseNrText.setText(addressDTO.getHouseNumber());
        phPostalCodeText.setText(addressDTO.getPostalCode());
        phCityText.setText(addressDTO.getCity());
        phCountryText.setText(addressDTO.getCountry());
        phPhoneNrText.setText(customerDTO.getPhoneNumber());
        //TODO: eventuell email addresse dazu?
        phPaymentMethodText.setText(reservationDTO.getPaymentMethod());
    }

    @FXML
    public void onFromDateDatePickerClicked(ActionEvent actionEvent){
        if(toDateDatePicker.getValue() != null && fromDateDatePicker.getValue() != null){
            fillTable("all between");
        }
    }

    @FXML
    public void onToDateDatePickerClicked(ActionEvent actionEvent) {
        if(toDateDatePicker.getValue() != null && fromDateDatePicker.getValue() != null){
            fillTable("all between");
        }
    }

    @FXML
    public void stateComboBoxAction(ActionEvent actionEvent) {
        if(stateComboBox.getValue().equals("all between")){
            fromDateDatePicker.setDisable(false);
            toDateDatePicker.setDisable(false);
        } else {
            fromDateDatePicker.setDisable(true);
            toDateDatePicker.setDisable(true);
        }
    }

    @FXML
    public void onRefreshButtonClicked(ActionEvent actionEvent) {
        //ArrayList<ReservationDTO> reservations = DomainController.getNotConfirmedReservations();
        ArrayList<ReservationDTO> allReservationDTOs = DomainController.getAllReservations();
        //filter not confirmed reservations
        ArrayList<ReservationDTO> notConfirmedReservations = new ArrayList<>();
        for(ReservationDTO reservationDTO : allReservationDTOs){
            if(reservationDTO.getBooking() == null){
                notConfirmedReservations.add(reservationDTO);
            }
        }

        for(ReservationDTO reservation : notConfirmedReservations) {
            Period period = Period.between(reservation.getCreationTimestamp().toLocalDate(), reservation.getArrivalDate());
            int daysDiff = Math.abs(period.getDays());
            if(daysDiff > 3){ //3 tage vor geplantem check-in ist keine anzahlung notwendi
                //wenn zeit bis einchecken <= 3 tage ist wird reservierung gelöscht
                period = Period.between(LocalDate.now(), reservation.getArrivalDate());
                daysDiff = Math.abs(period.getDays());
                if(daysDiff < 3){
                    System.out.println("here");
                    DomainController.deleteReservation(reservation);
                }
            }
        }
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
