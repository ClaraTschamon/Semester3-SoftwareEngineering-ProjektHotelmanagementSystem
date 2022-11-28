package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.domain.domainModel.Booking;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomCategoryDTO;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.DTOs.RoomDTO;
import com.fhv.hotelmanagement.view.controller.useCaseController.CheckOutUseCaseController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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

public class CheckOutViewController implements Initializable {

    private CheckOutUseCaseController useCaseController;
    private ArrayList<BookedRoomDTO> allBookedRoomDTOs;
    @FXML
    public Text roomText;
    @FXML
    public Text firstNameText;
    @FXML
    public Text lastNameText;
    @FXML
    public Text fromDateText;
    @FXML
    public Text toDateText;
    @FXML
    public Text numberPersonsText;
    @FXML
    public Text packageText;
    @FXML
    public Text roomPriceText;
    @FXML
    public Text paymentMethodText;
    @FXML
    public Text totalPriceText;
    @FXML
    public ComboBox roomComboBox;
    @FXML
    public CheckBox printInvoiceCheckBox;

    public CheckOutViewController(){
        useCaseController = new CheckOutUseCaseController();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allBookedRoomDTOs = DomainController.getBookedRoomsBetween(LocalDate.now(), LocalDate.now());
        ArrayList<BookingDTO> bookings = new ArrayList<>();
        ArrayList<Long> bookingNumbers = new ArrayList<>();

        for(BookedRoomDTO bookedRoom : allBookedRoomDTOs) {
            BookingDTO booking = bookedRoom.getBooking();
            if (booking.getCheckOutDatetime() == null) {
                if (!bookingNumbers.contains(booking.getNumber())) {
                    bookings.add(booking);
                    bookingNumbers.add(booking.getNumber());
                }
            }
        }

        roomComboBox.setConverter(new RoomNumberCustomerNameConverter(bookings));
        roomComboBox.getItems().setAll(FXCollections.observableArrayList(bookings));

        roomComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                BookingDTO selectedBooking = (BookingDTO) roomComboBox.getSelectionModel().getSelectedItem();
                setTexts(selectedBooking);
                useCaseController.setBooking(selectedBooking);
            }
        });
    }

    private void setTexts(BookingDTO bookingDTO){
        StringBuilder rooms = new StringBuilder();
        rooms.append("Zimmer:               ");
        ArrayList<BookedRoomDTO> bookedRoomDTOs = bookingDTO.getBookedRooms();
        for(BookedRoomDTO bookedRoomDTO : bookedRoomDTOs){
            rooms.append(bookedRoomDTO.getRoom().getNumber()).append("   ");
        }
        roomText.setText(rooms.toString());

        firstNameText.setText("Vorname:             " + bookingDTO.getCustomer().getFirstName());
        lastNameText.setText("Nachname:           " + bookingDTO.getCustomer().getLastName());

        LocalDate arrivalDate = bookingDTO.getArrivalDate();
        String formattedArrivalDate = arrivalDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        fromDateText.setText("Anreisedatum:      " + formattedArrivalDate);

        LocalDate departureDate = bookingDTO.getDepartureDate();
        String formattedDepartureDate = departureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        toDateText.setText("Abreisedatum:      " + formattedDepartureDate);

        numberPersonsText.setText("Personenanzahl:   "+ String.valueOf(bookingDTO.getAmountGuests()));
        packageText.setText("Package:               " + bookingDTO.getBoard().getName());

        if(Objects.equals(bookingDTO.getBookedRoomCategories().get(0).getPricePerNight(), new BigDecimal(0))){
            roomPriceText.setText("Zimmerpreis:        Preis-0");
        }
        else{
            roomPriceText.setText("Zimmerpreis:        Normalpreis");
        }

        paymentMethodText.setText("Zahlungsart:         " + bookingDTO.getPaymentMethod());
        BigDecimal price = calculateTotalPrice(bookingDTO, bookedRoomDTOs);
        totalPriceText.setText("Gesamtbetrag*:    " + price + " €");
    }

    private BigDecimal calculateTotalPrice(BookingDTO bookingDTO, ArrayList<BookedRoomDTO> bookedRoomDTOs){
        int totalNights = (int) DAYS.between(bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate());

        BigDecimal totalPrice = new BigDecimal(0);

        for (BookedRoomCategoryDTO c : bookingDTO.getBookedRoomCategories()) {
            BigDecimal price = c.getPricePerNight().multiply(new BigDecimal(totalNights)).multiply(new BigDecimal(c.getAmount()));
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
    public void onCancelButtonClicked(ActionEvent actionEvent) throws IOException {
        MainApplication.getMainController().loadIntoContentArea("home");
    }

    @FXML
    public void onConfirmButtonClicked(ActionEvent actionEvent) throws IOException {
        useCaseController.save();
        MainApplication.getMainController().loadIntoContentArea("home");
    }
}

class RoomNumberCustomerNameConverter<T> extends StringConverter<BookingDTO> {
    ArrayList<BookingDTO> bookings;

    RoomNumberCustomerNameConverter(ArrayList<BookingDTO> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString(BookingDTO booking) {
        if (
                booking == null ||
                booking.getBookedRooms() == null
        ) {
            return "";
        }

        String string = "";
        for (BookedRoomDTO bookedRoom : booking.getBookedRooms()) {
            if (bookedRoomIsCurrent(bookedRoom)) {
                string = string.concat(bookedRoom.getRoom().getNumber() + " ");
            }
        }
        if (booking.getCustomer() != null) {
            string = string.concat(booking.getCustomer().getLastName());
        }
        return string;
    }

    @Override
    public BookingDTO fromString(String string) {
        ArrayList<String> strings = (ArrayList<String>) Arrays.asList(string.split(" "));
        ArrayList<Integer> numbers = new ArrayList<>();
        String lastName = "";

        for (String s : strings) {
            try {
                numbers.add(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                lastName = lastName.concat(s + " ");
            }
        }

        for (BookingDTO booking : bookings) {
            if (
                    booking.getCustomer() != null &&
                    booking.getCustomer().getLastName().equals(lastName) &&
                    bookingContrainsRooms(booking, numbers)
            ) {
                return booking;
            }
        }
        return null;
    }

    private boolean bookedRoomIsCurrent(BookedRoomDTO bookedRoom) {
        LocalDate toDate = bookedRoom.getToDate();
        return toDate.isAfter(LocalDate.now()) || toDate.equals(LocalDate.now());
    }

    private boolean bookingContrainsRooms(BookingDTO bookingDTO, ArrayList<Integer> numbers) {
        if (bookingDTO.getBookedRooms() == null) {
            return false;
        }
        for (BookedRoomDTO bookedRoom : bookingDTO.getBookedRooms()) {
            LocalDate toDate = bookedRoom.getToDate();
            if (
                    bookedRoomIsCurrent(bookedRoom) &&
                    !numbers.contains(bookedRoom.getRoom().getNumber())
            ) {
                return false;
            }
        }
        return true;
    }
}