//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.domain.domainModel.BookedRoom;
import com.fhv.hotelmanagement.view.DTOs.*;
import com.fhv.hotelmanagement.view.viewServices.WarningType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;

public class CheckOut1ViewController implements Initializable {
    @FXML
    private ComboBox roomComboBox;
    @FXML
    public Text phRoomText;
    @FXML
    public Text phFirstNameText;
    @FXML
    public Text phLastNameText;
    @FXML
    public Text phNightsText;
    @FXML
    public Text phArrivalDateText;
    @FXML
    public Text phDepartureDateText;
    @FXML
    public Text phPersonNrText;
    @FXML
    public Text phBoardText;
    @FXML
    public Text phRoomPriceText;
    @FXML
    public Text phPaymentMethodText;
    @FXML
    public Text phTotalAmountText;
    @FXML
    private TextField searchDatabaseTextField;
    @FXML
    private ListView<BookingDTO> searchedDatabaseListView;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private Button nextButton;
    private ArrayList<BookedRoomDTO> allBookedRoomDTOs;
    private ArrayList<BookingDTO> currentBookingDTOs;
    private boolean searching;


    private CheckOutViewController viewController;

    public void setController(CheckOutViewController viewController){
        this.viewController = viewController;
    }

    protected void fillData(){
        if (viewController.getUseCaseController().getBooking() != null) {
            BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();
            BookedRoomDTO bookedRoom = bookingDTO.getBookedRooms().get(0);
            roomComboBox.setConverter(new BookedRoomConverter(allBookedRoomDTOs));
            roomComboBox.setValue(bookedRoom);
            setTexts(bookingDTO);

            if (bookingDTO.getNumber() != null) {
                searchDatabaseTextField.setText("Booking: " + bookingDTO.getNumber());
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searching = false;

        //hide Placeholder Text
        phRoomText.setText("");
        phFirstNameText.setText("");
        phLastNameText.setText("");
        phNightsText.setText("");
        phArrivalDateText.setText("");
        phDepartureDateText.setText("");
        phPersonNrText.setText("");
        phBoardText.setText("");
        phRoomPriceText.setText("");
        phPaymentMethodText.setText("");
        phTotalAmountText.setText("");

        ArrayList<BookedRoomDTO> bookedRoomDTOS = DomainController.getBookedRoomsBetween(LocalDate.now(), LocalDate.now());
        allBookedRoomDTOs = new ArrayList<>();
        currentBookingDTOs = new ArrayList<>();
        ArrayList<RoomDTO> rooms = new ArrayList<>();

        for (BookedRoomDTO bookedRoom : bookedRoomDTOS) {
            BookingDTO bookingDTO = bookedRoom.getBooking();
            if (bookingDTO.getCheckOutDatetime() == null) {
                currentBookingDTOs.add(bookingDTO);
                allBookedRoomDTOs.add(bookedRoom);
            }
        }

        roomComboBox.setConverter(new BookedRoomConverter(allBookedRoomDTOs));
        roomComboBox.getItems().addAll(FXCollections.observableList(allBookedRoomDTOs));

        roomComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                BookedRoomDTO selectedBookedRoom = (BookedRoomDTO) roomComboBox.getSelectionModel().getSelectedItem();
                BookingDTO bookingDTO = selectedBookedRoom.getBooking();
                viewController.getUseCaseController().setBooking(bookingDTO);
                setTexts(bookingDTO);
            }
        });

        // search
        searchDatabaseTextField.textProperty().addListener((observable, oldValue, newValue) ->
                searchDatabaseTextFieldChanged());

        searchedDatabaseListView = new ListView<>();
        searchedDatabaseListView.setStyle("-fx-border-radius: 8px;");
        searchedDatabaseListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(BookingDTO item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    CustomerDTO customer = item.getCustomer();
                    setText(item.getNumber() + ": " + customer.getFirstName() + " " + customer.getLastName() +
                            " (" + customer.getDateOfBirth() + ")");
                }
            }
        });
        searchedDatabaseListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                searchedDatabaseListViewChanged());

        searchDatabaseTextField.focusedProperty().addListener((observable, oldValue, newValue) ->
                searchDatabaseTextFieldFocusChanged(newValue));
    }

    private void setTexts(BookingDTO bookingDTO){
        StringBuilder rooms = new StringBuilder();
        ArrayList<BookedRoomDTO> bookedRoomDTOs = bookingDTO.getBookedRooms();
        for(BookedRoomDTO bookedRoomDTO : bookedRoomDTOs){
            rooms.append(bookedRoomDTO.getRoom().getNumber()).append("   ");
        }
        phRoomText.setText(rooms.toString());

        phFirstNameText.setText(bookingDTO.getCustomer().getFirstName());
        phLastNameText.setText(bookingDTO.getCustomer().getLastName());

        int nights = (int) DAYS.between(bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate());
        phNightsText.setText(String.valueOf(nights));

        LocalDate arrivalDate = bookingDTO.getArrivalDate();
        String formattedArrivalDate = arrivalDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        phArrivalDateText.setText(formattedArrivalDate);

        LocalDate departureDate = bookingDTO.getDepartureDate();
        String formattedDepartureDate = departureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        phDepartureDateText.setText(formattedDepartureDate);

        phPersonNrText.setText(String.valueOf(bookingDTO.getAmountGuests()));
        phBoardText.setText(bookingDTO.getBoard().getName());

        if(Objects.equals(bookingDTO.getBookedRoomCategories().get(0).getPricePerNight(), new BigDecimal(0))){
            phRoomPriceText.setText("Price-0");
        }
        else{
            phRoomPriceText.setText("Standard price");
        }

        phPaymentMethodText.setText(bookingDTO.getPaymentMethod());
        BigDecimal price = calculateTotalPrice(bookingDTO, bookedRoomDTOs);
        phTotalAmountText.setText(price + " €");
    }

    private BigDecimal calculateTotalPrice(BookingDTO bookingDTO, ArrayList<BookedRoomDTO> bookedRoomDTOs){
        int totalNights = (int) DAYS.between(bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate());

        BigDecimal totalPrice = new BigDecimal(0);

        for (BookedRoomCategoryDTO c : bookingDTO.getBookedRoomCategories()) {
            BigDecimal price = c.getPricePerNight().multiply(new BigDecimal(c.getAmount())).multiply(BigDecimal.valueOf(totalNights));
            totalPrice = totalPrice.add(price);
        }

        BigDecimal boardPrice = bookingDTO.getPricePerNightForBoard();
        if (boardPrice != null) {
            boardPrice = boardPrice.multiply(new BigDecimal(totalNights).multiply(new BigDecimal(bookingDTO.getAmountGuests())));
            totalPrice = totalPrice.add(boardPrice);
        }

        return totalPrice;
    }

    @FXML
    private void onCancelButtonClicked(ActionEvent actionEvent) {
        try {
            MainApplication.getMainController().loadIntoContentArea("home");
        } catch (IOException e) {
            MainApplication.getMainController().alert("Could cancel transaction.", WarningType.WARNING);
            System.out.println("Error clicking cancel button: " + e.getMessage());
        }
    }

    @FXML
    private void onNextButtonClicked(ActionEvent actionEvent) {
        try {
            viewController.loadCheckOut2();
        } catch (IOException e) {
            MainApplication.getMainController().alert("Could not switch page.", WarningType.WARNING);
            System.out.println("Error clicking next button: " + e.getMessage());
        }
    }

    private void searchDatabaseTextFieldChanged() {
        if (!searching) {
            return;
        }

        String searchText = searchDatabaseTextField.getText().toLowerCase();

        if (!searchedDatabaseListView.getSelectionModel().getSelectedItems().isEmpty()) {
            searchedDatabaseListView.getSelectionModel().clearSelection();
        }

        if (searchText == null || searchText.equals("")) {
            contentPane.getChildren().remove(searchedDatabaseListView);

        } else {
            ArrayList<BookingDTO> searchedBookings = new ArrayList<>();
            ArrayList<Long> searchedBookingNumbers = new ArrayList<>();
            for (BookingDTO b : currentBookingDTOs) {
                if (!searchedBookingNumbers.contains(b.getNumber())) {
                    CustomerDTO c = b.getCustomer();
                    String firstName = c.getFirstName().toLowerCase();
                    String lastName = c.getLastName().toLowerCase();
                    String fullName = firstName.concat(" ").concat(lastName);
                    String reversedFullName = lastName.concat(" ").concat(firstName);
                    String bookingNumber = b.getNumber().toString();
                    if (
                            firstName.contains(searchText) ||
                                    lastName.contains(searchText) ||
                                    fullName.contains(searchText) ||
                                    reversedFullName.contains(searchText) ||
                                    bookingNumber.startsWith(searchText)
                    ) {
                        searchedBookings.add(b);
                        searchedBookingNumbers.add(b.getNumber());
                    }
                }
            }
            searchedDatabaseListView.setItems(FXCollections.observableArrayList(searchedBookings));

            if (!contentPane.getChildren().contains(searchedDatabaseListView)) {
                searchedDatabaseListView.setMinWidth(searchDatabaseTextField.getWidth());
                searchedDatabaseListView.setMaxWidth(searchDatabaseTextField.getWidth());
                searchedDatabaseListView.setMaxHeight(200);
                searchedDatabaseListView.setLayoutX(searchDatabaseTextField.getLayoutX());
                searchedDatabaseListView.setLayoutY(searchDatabaseTextField.getLayoutY() + searchDatabaseTextField.getHeight() + 4);
                contentPane.getChildren().add(searchedDatabaseListView);
            }
        }
    }

    private void searchedDatabaseListViewChanged() {
        BookingDTO selectedBooking = searchedDatabaseListView.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            searching = false;
            viewController.getUseCaseController().setBooking(selectedBooking);
            fillData();
            contentPane.getChildren().remove(searchedDatabaseListView);
            nextButton.requestFocus();
        }
    }

    private void searchDatabaseTextFieldFocusChanged(boolean newValue) {
        if (newValue) {
            searching = true;
        } else {
            searching = false;
            contentPane.getChildren().remove(searchedDatabaseListView);
        }
    }
}

class BookedRoomConverter<T> extends StringConverter<BookedRoomDTO> {
    ArrayList<BookedRoomDTO> bookedRoomDTOS;

    public BookedRoomConverter(ArrayList<BookedRoomDTO> bookedRoomDTOS) {
        this.bookedRoomDTOS = bookedRoomDTOS;
    }

    @Override
    public String toString(BookedRoomDTO object) {
        if (object == null) {
            return null;
        } else {
            CustomerDTO customerDTO = object.getBooking().getCustomer();
            return object.getRoom().getNumber() + " " +
                    customerDTO.getLastName();
        }
    }

    @Override
    public BookedRoomDTO fromString(String string) {
        String[] strings = string.split(" ");

        if (strings.length == 0) {
            return null;
        } else {
            int roomNumber = Integer.parseInt(strings[0]);
            String lastName = String.join(" ", Arrays.copyOfRange(strings, 1, strings.length));
            for (BookedRoomDTO b : bookedRoomDTOS) {
                if (
                        b.getRoom().getNumber() == roomNumber &&
                        b.getBooking().getCustomer().getLastName().equals(lastName)
                ) {
                    return b;
                }
            }
            return null;
        }
    }
}