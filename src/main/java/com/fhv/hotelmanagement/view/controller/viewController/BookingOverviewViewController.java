//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.view.DTOs.AddressDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.viewServices.BookingViewBean;
import com.fhv.hotelmanagement.view.DTOs.CustomerDTO;
import com.fhv.hotelmanagement.view.controller.useCaseController.BookingOverviewUseCaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import net.bytebuddy.asm.Advice;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingOverviewViewController implements Initializable {

    @FXML
    private TableView bookingTableView;
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
    public TableColumn<BookingViewBean, String> stateCol;
    @FXML
    private TableColumn<BookingViewBean, ArrayList<Integer>> roomNrCol;
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
        fromDateDatePicker.setShowWeekNumbers(false);
        toDateDatePicker.setShowWeekNumbers(false);

        ObservableList<BookingDTO> allBookingDTOs = FXCollections.observableArrayList(DomainController.getAllBookings());
        ArrayList<BookingViewBean> allBookingViewBeans = new ArrayList<>();
        for(BookingDTO bookingDTO : allBookingDTOs){
            BookingViewBean booking = new BookingViewBean(bookingDTO);
            allBookingViewBeans.add(booking);
        }
        ObservableList<BookingViewBean> allBookings = FXCollections.observableArrayList(allBookingViewBeans);
        bookingTableView.setItems(allBookings);
        bookingNrCol.setCellValueFactory(new PropertyValueFactory<BookingViewBean, Long>("bookingNumber"));
        nameCol.setCellValueFactory(new PropertyValueFactory<BookingViewBean, String>("lastName"));
        arrivalDateCol.setCellValueFactory(new PropertyValueFactory<BookingViewBean, LocalDate>("arrivalDate"));
        departureDateCol.setCellValueFactory(new PropertyValueFactory<BookingViewBean, LocalDate>("departureDate"));
        //stateCol.setCellValueFactory(new PropertyValueFactory<BookingViewBean, String>("symbol"));
        roomNrCol.setCellValueFactory(new PropertyValueFactory<BookingViewBean, ArrayList<Integer>>("roomNumbers"));

        //TODO: Kopfzeile ausblenden
        /*
        //hide header //Fehler: header = null
        Pane header = (Pane) bookingTableView.lookup("TableHeaderRow");
        header.setVisible(false);
        bookingTableView.setLayoutY(-header.getHeight());
        bookingTableView.autosize();

         */

        if(bookingTableView.getSelectionModel().getTableView().getColumns().get(0) != null){
            bookingTableView.getSelectionModel().select(0); //per default erstes Item auswählen
            BookingViewBean bookingViewBean = (BookingViewBean) bookingTableView.getSelectionModel().getSelectedItem();
            setTexts(bookingViewBean.getBookingDTO());
        }

        bookingTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            BookingViewBean selectedItem = (BookingViewBean) bookingTableView.getSelectionModel().getSelectedItem();
            BookingDTO bookingDTO = selectedItem.getBookingDTO();
            setTexts(bookingDTO);
        });


    }

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
}
