//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.domain.exceptions.BookingIsInvalidException;
import com.fhv.hotelmanagement.domain.exceptions.ReservationIsInvalidException;
import com.fhv.hotelmanagement.view.DTOs.*;
import com.fhv.hotelmanagement.view.viewServices.DepositService;
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
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
    public TableColumn<ReservationViewBean, Button> commentCol;
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

    @FXML
    private Button checkInButton;

    private String currentState = new String();


    public ReservationOverviewViewController() {
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
        //disable checkInButton on default
        checkInButton.setDisable(true);

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

    public void fillTable(String state) {
        currentState = state;
        ObservableList<ReservationDTO> reservationDTOs = FXCollections.observableArrayList();
        if (state.equals("all")) {
            reservationDTOs = FXCollections.observableArrayList(DomainController.getAllReservations());
        } else if (state.equals("confirmed")) {
            reservationDTOs = FXCollections.observableArrayList(DomainController.getConfirmedReservations());
        } else if (state.equals("not confirmed")) {
            //reservationDTOs = FXCollections.observableArrayList(DomainController.getNotConfirmedReservations()); //TODO fixing in ReservationDataMapper
            ObservableList<ReservationDTO> allreservationDTOs = FXCollections.observableArrayList(DomainController.getAllReservations());
            ArrayList<ReservationDTO> notConfirmedReservations = new ArrayList<>();
            for (ReservationDTO reservationDTO : allreservationDTOs) {
                if (reservationDTO.getBooking() == null) {
                    notConfirmedReservations.add(reservationDTO);
                }
            }
            reservationDTOs = FXCollections.observableArrayList(notConfirmedReservations);
        } else if (state.equals("all between")) {
            LocalDate minDate = fromDateDatePicker.getValue();
            LocalDate maxDate = toDateDatePicker.getValue();
            reservationDTOs = FXCollections.observableArrayList(DomainController.getAllReservationsBetween(minDate, maxDate));
        }
        ArrayList<ReservationViewBean> allReservationViewBeans = new ArrayList<>();
        for (ReservationDTO reservationDTO : reservationDTOs) {
            ReservationViewBean reservation = new ReservationViewBean(reservationDTO);
            allReservationViewBeans.add(reservation);
        }
        ObservableList<ReservationViewBean> allReservations = FXCollections.observableArrayList(allReservationViewBeans);
        Comparator<ReservationViewBean> comparator = Comparator.comparing(ReservationViewBean::getReservationNumber);
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
        commentCol.setCellValueFactory(new PropertyValueFactory<ReservationViewBean, Button>("commentButton"));
        commentCol.setStyle("-fx-alignment: CENTER");

        if (allReservations.size() == 0) {
            reservationTableView.setPlaceholder(new Label("No reservations"));
            reservationTableView.getItems().clear();
            hidePlaceholderTexts();
        } else {
            reservationTableView.setItems(allReservations);
        }

        if (reservationTableView.getSelectionModel().getTableView().getColumns().get(0) != null) {
            reservationTableView.getSelectionModel().select(0); //per default erstes Item auswählen
            ReservationViewBean reservationViewBean = (ReservationViewBean) reservationTableView.getSelectionModel().getSelectedItem();
            if (reservationViewBean != null) {
                setTexts(reservationViewBean.getReservationDTO());
            }
        }

        reservationTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (reservationTableView.getSelectionModel().getSelectedItem() != null) {
                ReservationViewBean selectedItem = (ReservationViewBean) reservationTableView.getSelectionModel().getSelectedItem();
                ReservationDTO reservationDTO = selectedItem.getReservationDTO();
                setTexts(reservationDTO);

                //disable check-in button if booking is not confirmed
                Period period = Period.between(reservationDTO.getCreationTimestamp().toLocalDate(), reservationDTO.getArrivalDate());
                int daysDiff = Math.abs(period.getDays());
                if (daysDiff > 3) {
                    if (reservationDTO.getBooking() == null) {
                        checkInButton.setDisable(true); //disable
                    } else if (reservationDTO.getBooking().getCheckInDatetime() != null) {
                        checkInButton.setDisable(true); //disable
                    } else {
                        checkInButton.setDisable(false); //enable
                    }
                } else {
                    if(reservationDTO.getBooking() != null){
                        if(reservationDTO.getBooking().getCheckInDatetime() != null) {
                            checkInButton.setDisable(true);
                        } else {
                            checkInButton.setDisable(false); //enable
                        }
                    } else {
                        checkInButton.setDisable(false);
                    }
                }
            }
        });

    }

    private void setTexts(ReservationDTO reservationDTO) {
        CustomerDTO customerDTO = reservationDTO.getCustomer();
        AddressDTO addressDTO = customerDTO.getAddress();
        phReservationNumberText.setText(String.valueOf(reservationDTO.getNumber()));

        LocalDate arrivalDate = reservationDTO.getArrivalDate();
        String formattedArrivalDate = arrivalDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        arrivalDateText.setText(formattedArrivalDate);

        LocalDate departureDate = reservationDTO.getDepartureDate();
        String formattedDepartureDate = departureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        departureDateText.setText(formattedDepartureDate);

        if (reservationDTO.getBooking() == null) {
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
        phPaymentMethodText.setText(reservationDTO.getPaymentMethod());
    }

    @FXML
    public void onFromDateDatePickerClicked(ActionEvent actionEvent) {
        if (toDateDatePicker.getValue() != null && fromDateDatePicker.getValue() != null) {
            fillTable("all between");
        }
    }

    @FXML
    public void onToDateDatePickerClicked(ActionEvent actionEvent) {
        if (toDateDatePicker.getValue() != null && fromDateDatePicker.getValue() != null) {
            fillTable("all between");
        }
    }

    @FXML
    public void stateComboBoxAction(ActionEvent actionEvent) {
        if (stateComboBox.getValue().equals("all between")) {
            fromDateDatePicker.setDisable(false);
            toDateDatePicker.setDisable(false);
        } else {
            fromDateDatePicker.setDisable(true);
            toDateDatePicker.setDisable(true);
        }
    }

    @FXML
    public void onRefreshButtonClicked(ActionEvent actionEvent) {
        //ArrayList<ReservationDTO> reservations = DomainController.getNotConfirmedReservations(); //TODO fixing in ReservationDataMapper
        ArrayList<ReservationDTO> allReservationDTOs = DomainController.getAllReservations();
        //filter not confirmed reservations
        ArrayList<ReservationDTO> notConfirmedReservations = new ArrayList<>();
        for (ReservationDTO reservationDTO : allReservationDTOs) {
            if (reservationDTO.getBooking() == null) {
                notConfirmedReservations.add(reservationDTO);
            }
        }

        for (ReservationDTO reservation : notConfirmedReservations) {
            Period period = Period.between(reservation.getCreationTimestamp().toLocalDate(), reservation.getArrivalDate());
            int daysDiff = Math.abs(period.getDays());
            if (daysDiff > 3) { //3 tage vor geplantem check-in ist keine anzahlung notwendig
                //wenn zeit bis einchecken <= 3 tage ist wird reservierung gelöscht
                period = Period.between(LocalDate.now(), reservation.getArrivalDate());
                daysDiff = Math.abs(period.getDays());
                if (daysDiff < 3) {
                    DomainController.deleteReservation(reservation);
                    fillTable(currentState);
                }
            }
        }

        DepositService depositService = new DepositService();
        try {
            ArrayList<Long> reservationNumbers = depositService.parseData(depositService.convertData());
            for (Long l : reservationNumbers) {
                ReservationDTO reservation;
                try {
                    reservation = DomainController.getReservation(l);
                    if (reservation.getBooking() == null) {
                        BookingDTO bookingDTO = createBookingDTO(reservation);
                        long number = DomainController.saveBooking(bookingDTO);
                        bookingDTO.setNumber(number);
                        reservation.setBooking(bookingDTO);
                        DomainController.saveReservation(reservation);
                        fillTable(currentState);
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("There is no resevation with the number: " + l.toString());
                }
            }
        } catch (IOException | ReservationIsInvalidException | BookingIsInvalidException e) {
            e.printStackTrace();
        }
    }

    private void hidePlaceholderTexts() {
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

    private BookingDTO createBookingDTO(ReservationDTO reservationDTO){ //gehört in BookingFactory... createBookingDTOFromReservation
        BookingDTO bookingDTO = new BookingDTO(null, reservationDTO, reservationDTO.getCustomer(), reservationDTO.getArrivalDate(), null,
                reservationDTO.getDepartureDate(), null, reservationDTO.getBillingAddress(), reservationDTO.getPaymentMethod(),
                reservationDTO.getCreditCardNumber(), reservationDTO.getExpirationDate(), reservationDTO.getAuthorisationNumber(), reservationDTO.getBoard(),
                reservationDTO.getPricePerNightForBoard(), reservationDTO.getComment(), reservationDTO.getAmountGuests(), null, null);


        ArrayList<BookedRoomCategoryDTO> bookedRoomCategoryDTOS = new ArrayList<>();

        for (ReservedRoomCategoryDTO reservedRoomCategoryDTO : reservationDTO.getReservedRoomCategories()) {
            BookedRoomCategoryDTO bookedRoomCategoryDTO = new BookedRoomCategoryDTO();
            bookedRoomCategoryDTO.setRoomCategory(reservedRoomCategoryDTO.getRoomCategory());
            bookedRoomCategoryDTO.setAmount(reservedRoomCategoryDTO.getAmount());
            bookedRoomCategoryDTO.setPricePerNight(reservedRoomCategoryDTO.getPricePerNight());
            bookedRoomCategoryDTO.setBooking(bookingDTO);
            bookedRoomCategoryDTOS.add(bookedRoomCategoryDTO);
        }

        ArrayList<BookedRoomDTO> bookedRoomDTOS = new ArrayList<>();

        for (ReservedRoomDTO reservedRoomDTO : reservationDTO.getReservedRooms()) {
            BookedRoomDTO bookedRoomDTO = new BookedRoomDTO();
            bookedRoomDTO.setRoom(reservedRoomDTO.getRoom());
            bookedRoomDTO.getRoom().setNumber(reservedRoomDTO.getRoom().getNumber());
            bookedRoomDTO.setFromDate(reservedRoomDTO.getFromDate());
            bookedRoomDTO.setToDate(reservedRoomDTO.getToDate());
            bookedRoomDTO.setBooking(bookingDTO);
            bookedRoomDTOS.add(bookedRoomDTO);
        }

        bookingDTO.setBookedRoomCategories(bookedRoomCategoryDTOS);
        bookingDTO.setBookedRooms(bookedRoomDTOS);

        return bookingDTO;
    }

    @FXML
    private void onCheckInClicked(ActionEvent actionEvent) throws IOException {
        CheckInViewController checkInViewController = new CheckInViewController();
        ReservationViewBean selectedItem = (ReservationViewBean) reservationTableView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            ReservationDTO reservationDTO = selectedItem.getReservationDTO();
            if(reservationDTO.getBooking() == null){
                BookingDTO bookingDTO = createBookingDTO(reservationDTO);
                reservationDTO.setBooking(bookingDTO);
                try{
                    DomainController.saveReservation(reservationDTO);
                    DomainController.saveBooking(bookingDTO);
                } catch(ReservationIsInvalidException | BookingIsInvalidException e){
                    e.printStackTrace();
                }
            }

            checkInViewController.getUseCaseController().setBooking(reservationDTO.getBooking());
            checkInViewController.getUseCaseController().setCustomer(reservationDTO.getCustomer());
            checkInViewController.setIsCheckIn(true);
            checkInViewController.loadCheckIn1();
        }
    }
}
