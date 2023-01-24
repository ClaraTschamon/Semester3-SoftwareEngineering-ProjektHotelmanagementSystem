//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.view.DTOs.AddressDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.viewServices.BookingViewBean;
import com.fhv.hotelmanagement.view.DTOs.CustomerDTO;
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class BookingOverviewViewController implements Initializable {

    @FXML
    private TableView bookingTableView;
    @FXML
    private ComboBox stateComboBox;
    @FXML
    private DatePicker toDateDatePicker;
    @FXML
    private DatePicker fromDateDatePicker;
    @FXML
    private Text phBookingNumberText;
    @FXML
    private TableColumn<BookingViewBean, Long> bookingNrCol;
    @FXML
    private TableColumn<BookingViewBean, String> nameCol;
    @FXML
    private TableColumn<BookingViewBean, LocalDate> arrivalDateCol;
    @FXML
    private TableColumn<BookingViewBean, LocalDate> departureDateCol;
    @FXML
    public TableColumn<BookingViewBean, Button> stateCol;
    @FXML
    private TableColumn<BookingViewBean, ArrayList<Integer>> roomNrCol;
    @FXML
    private TableColumn<BookingViewBean, Button> commentCol;
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
    public Button checkOutButton;
    @FXML
    public Button checkInButton;

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

        //disable CheckInButton on default to prevent mistakes
        checkInButton.setDisable(true);

        //default state is set in .fxml to: 'checked-in'
        String state = stateComboBox.getSelectionModel().getSelectedItem().toString();
        fillTable(state);

        //listener auf stateComboBox. Wenn ein anderer State ausgewählt wird, muss die Tabelle aktualisiert werden
        stateComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String state = stateComboBox.getSelectionModel().getSelectedItem().toString();
                fillTable(state);
            }
        });
    }


    /**
     * Funktion befüllt die Tabelle mit Daten. Wenn in ComboBox checked-in ausgewählt ist,
     * werden nur die eingecheckten Buchungen angezeigt usw.
     * Um Bookings in Tabelle anzeigen zu können musste die Zwischenklasse "BookingViewBean"
     * erstellt werden, die die nötigen Informationen der Booking schön aufbereitet.
     * @param state is the state of the booking
     */
    public void fillTable(String state){
        ObservableList<BookingDTO> bookingDTOs = FXCollections.observableArrayList();
        if(state.equals("all")){
            bookingDTOs = FXCollections.observableArrayList(DomainController.getAllBookings());
        }else if(state.equals("checked-in")) {
            bookingDTOs = FXCollections.observableArrayList(DomainController.getCurrentBookings());
        }else if(state.equals("all between")){
            LocalDate minDate = fromDateDatePicker.getValue();
            LocalDate maxDate = toDateDatePicker.getValue();
            bookingDTOs = FXCollections.observableArrayList(DomainController.getAllBookingsBetween(minDate, maxDate));
        }
        ArrayList<BookingViewBean> allBookingViewBeans = new ArrayList<>();
        for(BookingDTO bookingDTO : bookingDTOs){
            BookingViewBean booking = new BookingViewBean(bookingDTO);
            allBookingViewBeans.add(booking);
        }
        ObservableList<BookingViewBean> allBookings = FXCollections.observableArrayList(allBookingViewBeans);
        Comparator<BookingViewBean> comparator = Comparator.comparingLong(BookingViewBean :: getBookingNumber);
        comparator = comparator.reversed();
        allBookings.sort(comparator);

        //greift auf Attribute in BookingViewBean zu
        bookingNrCol.setCellValueFactory(new PropertyValueFactory<BookingViewBean, Long>("bookingNumber"));
        nameCol.setCellValueFactory(new PropertyValueFactory<BookingViewBean, String>("lastName"));
        arrivalDateCol.setCellValueFactory(new PropertyValueFactory<BookingViewBean, LocalDate>("arrivalDate"));
        arrivalDateCol.setStyle("-fx-alignment: CENTER");
        departureDateCol.setCellValueFactory(new PropertyValueFactory<BookingViewBean, LocalDate>("departureDate"));
        departureDateCol.setStyle("-fx-alignment: CENTER");
        stateCol.setCellValueFactory(new PropertyValueFactory<BookingViewBean, Button>("imageButton"));
        stateCol.setStyle("-fx-alignment: CENTER");
        roomNrCol.setCellValueFactory(new PropertyValueFactory<BookingViewBean, ArrayList<Integer>>("roomNumbers"));
        commentCol.setCellValueFactory(new PropertyValueFactory<BookingViewBean, Button>("commentButton"));
        commentCol.setStyle("-fx-alignment: CENTER");

        if(allBookings.size() == 0){
            bookingTableView.setPlaceholder(new Label("No bookings"));
            bookingTableView.getItems().clear();
            hidePlaceholderTexts();
        }else{
            bookingTableView.setItems(allBookings);
        }

        if(bookingTableView.getSelectionModel().getTableView().getColumns().get(0) != null){
            bookingTableView.getSelectionModel().select(0); //per default erstes Item auswählen
            BookingViewBean bookingViewBean = (BookingViewBean) bookingTableView.getSelectionModel().getSelectedItem();
            if(bookingViewBean != null){
                setTexts(bookingViewBean.getBookingDTO());
            }
        }

        bookingTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(bookingTableView.getSelectionModel().getSelectedItem() != null){
                BookingViewBean selectedItem = (BookingViewBean) bookingTableView.getSelectionModel().getSelectedItem();
                BookingDTO bookingDTO = selectedItem.getBookingDTO();
                setTexts(bookingDTO);

                //disable check-out button if state of booking is checked-out
                if(bookingDTO.getCheckOutDatetime() == null){
                    checkOutButton.setDisable(false); //enable
                }else{
                    checkOutButton.setDisable(true);
                }

                //disable check-in button if booking is already checked-in
                if(bookingDTO.getCheckInDatetime() == null){
                    checkInButton.setDisable(false); //enable
                }else{
                    checkInButton.setDisable(true); //disable
                }
            }
        });
    }

    /**
     * füllt die platzhaltertexte auf der rechten Seite mit den informationen aus dem bookingDTO
     */
    private void setTexts(BookingDTO bookingDTO){
        CustomerDTO customerDTO = bookingDTO.getCustomer();
        AddressDTO addressDTO = customerDTO.getAddress();
        phBookingNumberText.setText(String.valueOf(bookingDTO.getNumber()));

        LocalDate arrivalDate = bookingDTO.getArrivalDate();
        String formattedArrivalDate = arrivalDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        arrivalDateText.setText(formattedArrivalDate);

        LocalDate departureDate = bookingDTO.getDepartureDate();
        String formattedDepartureDate = departureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        departureDateText.setText(formattedDepartureDate);

        if(bookingDTO.getCheckInDatetime() == null){
            phStateText.setText("booked");
        }
        else if(bookingDTO.getCheckInDatetime() != null && bookingDTO.getCheckOutDatetime() == null){
            phStateText.setText("checked in");
        }
        else if(bookingDTO.getCheckInDatetime() != null && bookingDTO.getCheckOutDatetime() != null){
            phStateText.setText("checked out");
        }
        phRoomsText.setText(bookingDTO.getBookedRooms().toString());
        phFirstnameText.setText(customerDTO.getFirstName());
        phLastnameText.setText(customerDTO.getLastName());
        phStreetText.setText(addressDTO.getStreet());
        phHouseNrText.setText(addressDTO.getHouseNumber());
        phPostalCodeText.setText(addressDTO.getPostalCode());
        phCityText.setText(addressDTO.getCity());
        phCountryText.setText(addressDTO.getCountry());
        phPhoneNrText.setText(customerDTO.getPhoneNumber());
        //TODO: eventuell email addresse dazu?
        phPaymentMethodText.setText(bookingDTO.getPaymentMethod());
    }

    /**
     * wenn der State "all between" angezeigt wird, dürfen die DatePicker nichtmehr ausgegraut sein
     */
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

    /**
     * wenn im DatePicker ein Value verändert wird, muss die Tabelle neu befüllt werden mit den richtigen Daten
     */
    @FXML
    public void onFromDateDatePickerClicked(ActionEvent actionEvent) {
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

    /**
     * Wenn check-out geklickt wird, wird die ausgewählte buchung an den checkOutUseCaseController
     * weiter gegeben. Dann wird der CheckOut geladen.
     */
    @FXML
    private void onCheckInClicked(ActionEvent actionEvent) throws IOException {
        CheckInViewController checkInViewController = new CheckInViewController();
        BookingViewBean selectedItem = (BookingViewBean) bookingTableView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            BookingDTO bookingDTO = selectedItem.getBookingDTO();
            checkInViewController.getUseCaseController().setBooking(bookingDTO);
            checkInViewController.getUseCaseController().setCustomer(bookingDTO.getCustomer());
            checkInViewController.setIsCheckIn(true);
            checkInViewController.loadCheckIn1();
        }
    }

    @FXML
    public void onCheckedOutClicked(ActionEvent actionEvent) throws IOException {
        CheckOutViewController checkOutViewController = new CheckOutViewController();
        BookingViewBean selectedItem = (BookingViewBean) bookingTableView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            BookingDTO bookingDTO = selectedItem.getBookingDTO();
            checkOutViewController.getUseCaseController().setBooking(bookingDTO);
            checkOutViewController.setIsFromBookingOverview(true);
            checkOutViewController.loadCheckOut1();
        }
    }

    private void hidePlaceholderTexts(){
        phBookingNumberText.setText("");
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
