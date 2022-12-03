//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.view.DTOs.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class WalkIn1ViewController implements Initializable {

    @FXML
    private TextField searchDatabaseTextField;
    @FXML
    private RadioButton fullBoard;
    @FXML
    private RadioButton halfBoard;
    @FXML
    private RadioButton onlyBreakfast;
    @FXML
    private RadioButton noPackage;
    @FXML
    private Text counterSingleRoom;
    @FXML
    private Text counterDoubleRoom;
    @FXML
    private Text counterFamilyRoom;
    @FXML
    private Text counterSuite;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private DatePicker departureDatePicker;
    @FXML
    private ComboBox roomPriceDropDown;
    @FXML
    private ToggleGroup packageToggleGroup;
    @FXML
    private Spinner<Integer> amountGuestsSpinner;
    @FXML
    private Rectangle customerInfoRectangle;
    @FXML
    private Label checkInForLabel;
    @FXML
    private Label lastRoomsLabel;
    private CheckComboBox<RoomDTO> singleRoomDropDown;
    private CheckComboBox<RoomDTO> doubleRoomDropDown;
    private CheckComboBox<RoomDTO> familyRoomDropDown;
    private CheckComboBox<RoomDTO> suiteDropDown;
    private WalkInViewController viewController;
    private RoomProvider roomProvider;
    private ArrayList<CustomerDTO> customers;
    private ListView<CustomerDTO> searchedCustomersListView;
    private boolean searching;

    public void setController(WalkInViewController viewController) {
        this.viewController = viewController;
    }

    private boolean validate() {

        boolean departureDateIsValid = false;
        boolean counterRoomIsValid = false;
        boolean counterSingleRoomIsValid = false;
        boolean counterDoubleRoomIsValid = false;
        boolean counterFamilyRoomIsValid = false;
        boolean counterSuiteRoomIsValid = false;
        boolean boardButtonIsValid = false;
        boolean roomPriceDropDownIsValid = false;
        boolean amountGuestsIsValid = false;

        LocalDate departureDate = departureDatePicker.getValue();
        LocalDate todayDate = LocalDate.now();

        if (validateCheckOutDate(todayDate, departureDate)) {
            departureDateIsValid = true;
        } else {
            departureDatePicker.setStyle("-fx-text-inner-color: red");
        }

        if (singleRoomDropDown.getCheckModel().getCheckedItems().size() > 0) {
            counterSingleRoomIsValid = true;
        }
        else {
            setTextColorRed(counterSingleRoom);
        }

        if (doubleRoomDropDown.getCheckModel().getCheckedItems().size() > 0) {
            counterDoubleRoomIsValid = true;
        }
        else {
            setTextColorRed(counterDoubleRoom);
        }

        if (familyRoomDropDown.getCheckModel().getCheckedItems().size() > 0) {
            counterFamilyRoomIsValid = true;
        }
        else {
            setTextColorRed(counterFamilyRoom);
        }

        if (suiteDropDown.getCheckModel().getCheckedItems().size() > 0) {
            counterSuiteRoomIsValid = true;
        }
        else {
            setTextColorRed(counterSuite);
        }

        if (counterSingleRoomIsValid || counterDoubleRoomIsValid ||
                counterFamilyRoomIsValid || counterSuiteRoomIsValid) {
            counterRoomIsValid = true;
        } else {
            counterRoomIsValid = false;
        }

        if (packageToggleGroup.getSelectedToggle() != null) {
            boardButtonIsValid = true;
        } else {
            boardButtonIsValid = false;
        }

        if (roomPriceDropDown.getValue() != null) {
            roomPriceDropDownIsValid = true;
        } else {
            roomPriceDropDownIsValid = false;
        }

        Integer amountGuests = Integer.valueOf(amountGuestsSpinner.getValue());
        if (amountGuests > 0 &&
                (amountGuests
                        - singleRoomDropDown.getCheckModel().getCheckedItems().size()
                        - doubleRoomDropDown.getCheckModel().getCheckedItems().size() * 2
                        - familyRoomDropDown.getCheckModel().getCheckedItems().size() * 4
                        - suiteDropDown.getCheckModel().getCheckedItems().size() * 4
                        <= 0)
        ) {
            amountGuestsIsValid = true;
        } else {
            amountGuestsIsValid = false;
            amountGuestsSpinner.setStyle("-fx-text-inner-color: red");


            amountGuestsSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
                @Override
                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                    amountGuestsSpinner.setStyle("-fx-text-inner-color: black");
                }
            });
        }

        if (departureDateIsValid && counterRoomIsValid &&
                boardButtonIsValid && roomPriceDropDownIsValid && amountGuestsIsValid) {
            return true;
        }
        else return false;
    }

    private void setTextColorRed(Text text) {
        text.setFill(Color.RED);
    }

    private void setCounterRoomColor(Text text) {
        if (Integer.parseInt(text.getText()) > 0) {
        } else {
            setTextColorRed(text);
        }
    }

    public boolean validateCheckOutDate(LocalDate dateOfToday, LocalDate dateOfCheckOut) {
        if (Period.between(dateOfToday, dateOfCheckOut).getDays() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @FXML
    private void onCancelButtonClicked(ActionEvent e) {
        try {
            viewController.cancel();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void onNextButtonClicked(ActionEvent e) {
        try {
            if (validate()) {
                saveData();
                viewController.loadWalkIn2();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected void fillData() {
        amountGuestsSpinner.setValueFactory(new SpinnerValueFactory<Integer>() {
            @Override
            public void decrement(int steps) {
                if (amountGuestsSpinner.getValue()-steps > 0) {
                    amountGuestsSpinner.getValueFactory().setValue(amountGuestsSpinner.getValue() - steps);
                }
            }

            @Override
            public void increment(int steps) {
                amountGuestsSpinner.getValueFactory().setValue(amountGuestsSpinner.getValue()+steps);
            }
        });

        BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();

        if(bookingDTO.getDepartureDate() != null){
            departureDatePicker.setValue(bookingDTO.getDepartureDate());
        }
        else{
            LocalDate defaultDepartureDate = LocalDate.now().plusDays(1);
            departureDatePicker.setValue(defaultDepartureDate);
        }

        int amountBookedSingleRooms = 0;
        int amountBookedDoubleRooms = 0;
        int amountBookedFamilyRooms = 0;
        int amountBookedSuites = 0;
        if (bookingDTO.getBookedRooms() != null) {
            for (BookedRoomDTO bookedRoomDTO : bookingDTO.getBookedRooms()) {
                String categoryName = bookedRoomDTO.getRoom().getCategory().getName();
                if (categoryName.equals("Single room")) {
                    singleRoomDropDown.getCheckModel().check(bookedRoomDTO.getRoom());
                    amountBookedSingleRooms++;
                } else if (categoryName.equals("Double room")) {
                    doubleRoomDropDown.getCheckModel().check(bookedRoomDTO.getRoom());
                    amountBookedDoubleRooms++;
                } else if (categoryName.equals("Family room")) {
                    familyRoomDropDown.getCheckModel().check(bookedRoomDTO.getRoom());
                    amountBookedFamilyRooms++;
                } else if (categoryName.equals("Suite")) {
                    suiteDropDown.getCheckModel().check(bookedRoomDTO.getRoom());
                    amountBookedSuites++;
                }
            }
        }
        counterSingleRoom.setText(String.valueOf(amountBookedSingleRooms));
        counterDoubleRoom.setText(String.valueOf(amountBookedDoubleRooms));
        counterFamilyRoom.setText(String.valueOf(amountBookedFamilyRooms));
        counterSuite.setText(String.valueOf(amountBookedSuites));

        String roomPrice = viewController.getUseCaseController().getRoomPrice();
        if (roomPrice != null) {
            roomPriceDropDown.setValue(roomPrice);
        }

        if (bookingDTO.getBoard() != null) {
            String selectedBoardName = bookingDTO.getBoard().getName();
            if (selectedBoardName.equals("Full Board")) {
                fullBoard.setSelected(true);
            } else if (selectedBoardName.equals("Half Board")) {
                halfBoard.setSelected(true);
            } else if (selectedBoardName.equals("Just Breakfast")) {
                onlyBreakfast.setSelected(true);
            } else if (selectedBoardName.equals("No Package")) {
                noPackage.setSelected(true);
            } else {
                fullBoard.setSelected(true);
            }
        } else {
            fullBoard.setSelected(true);
        }

        if (bookingDTO.getAmountGuests() != null) {
            amountGuestsSpinner.getValueFactory().setValue(bookingDTO.getAmountGuests());
        } else {
            amountGuestsSpinner.getValueFactory().setValue(1);
        }

        setCustomerInfo();
    }

    private void setCustomerInfo() {
        CustomerDTO customer = viewController.getUseCaseController().getCustomer();
        if (customer != null && customer.getNumber() != null) {
            checkInForLabel.setText("Check-In for: " + customer.getFirstName() + " " + customer.getLastName());
            customerInfoRectangle.setVisible(true);
            checkInForLabel.setVisible(true);
            lastRoomsLabel.setVisible(false); // TODO fill former rooms
        } else {
            customerInfoRectangle.setVisible(false);
            checkInForLabel.setVisible(false);
            lastRoomsLabel.setVisible(false);
        }
    }

    protected void saveData(){
        BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();
        bookingDTO.setDepartureDate(departureDatePicker.getValue());

        //save all package radio buttons
        RadioButton selectedBoardButton = (RadioButton) packageToggleGroup.getSelectedToggle();
        BoardDTO selectedBoard = getBoardByName(selectedBoardButton.getText());
        bookingDTO.setBoard(selectedBoard);
        if (selectedBoard != null) {
            bookingDTO.setPricePerNightForBoard(selectedBoard.getPricePerNight());
        } else {
            bookingDTO.setPricePerNightForBoard(null);
        }

        // save booked rooms (TODO save other prices)
        ArrayList<RoomDTO> bookedSingleRooms = new ArrayList<>(singleRoomDropDown.getCheckModel().getCheckedItems());
        ArrayList<RoomDTO> bookedDoubleRooms = new ArrayList<>(doubleRoomDropDown.getCheckModel().getCheckedItems());
        ArrayList<RoomDTO> bookedFamilyRooms = new ArrayList<>(familyRoomDropDown.getCheckModel().getCheckedItems());
        ArrayList<RoomDTO> bookedSuites = new ArrayList<>(suiteDropDown.getCheckModel().getCheckedItems());

        ArrayList<BookedRoomDTO> bookedRooms = new ArrayList<>();
        for (RoomDTO r : bookedSingleRooms) {
            bookedRooms.add(new BookedRoomDTO(bookingDTO, r, bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate()));
        }
        for (RoomDTO r : bookedDoubleRooms) {
            bookedRooms.add(new BookedRoomDTO(bookingDTO, r, bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate()));
        }
        for (RoomDTO r : bookedFamilyRooms) {
            bookedRooms.add(new BookedRoomDTO(bookingDTO, r, bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate()));
        }
        for (RoomDTO r : bookedSuites) {
            bookedRooms.add(new BookedRoomDTO(bookingDTO, r, bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate()));
        }
        bookingDTO.setBookedRooms(bookedRooms);

        HashMap<String, RoomCategoryDTO> roomCategories = DomainController.getAllRoomCategories();
        ArrayList<BookedRoomCategoryDTO> bookedRoomCategories = new ArrayList<>();
        if (bookedSingleRooms.size() > 0) {
            RoomCategoryDTO roomCategoryDTO = roomCategories.get("Single room");
            BigDecimal price = roomCategoryDTO.getPricePerNight();
            if(roomPriceDropDown.getSelectionModel().getSelectedItem().equals("Price-0")){
                price = new BigDecimal(0);
            }
            bookedRoomCategories.add(new BookedRoomCategoryDTO(bookingDTO, roomCategoryDTO, price, bookedSingleRooms.size()));
        }
        if (bookedDoubleRooms.size() > 0) {
            RoomCategoryDTO roomCategoryDTO = roomCategories.get("Double room");
            BigDecimal price = roomCategoryDTO.getPricePerNight();
            if(roomPriceDropDown.getSelectionModel().getSelectedItem().equals("Price-0")){
                price = new BigDecimal(0);
            }
            bookedRoomCategories.add(new BookedRoomCategoryDTO(bookingDTO, roomCategoryDTO, price, bookedDoubleRooms.size()));
        }
        if (bookedFamilyRooms.size() > 0) {
            RoomCategoryDTO roomCategoryDTO = roomCategories.get("Family room");
            BigDecimal price = roomCategoryDTO.getPricePerNight();
            if(roomPriceDropDown.getSelectionModel().getSelectedItem().equals("Price-0")){
                price = new BigDecimal(0);
            }
            bookedRoomCategories.add(new BookedRoomCategoryDTO(bookingDTO, roomCategoryDTO, price, bookedFamilyRooms.size()));
        }
        if (bookedSuites.size() > 0) {
            RoomCategoryDTO roomCategoryDTO = roomCategories.get("Suite");
            BigDecimal price = roomCategoryDTO.getPricePerNight();
            if(roomPriceDropDown.getSelectionModel().getSelectedItem().equals("Price-0")){
                price = new BigDecimal(0);
            }
            bookedRoomCategories.add(new BookedRoomCategoryDTO(bookingDTO, roomCategoryDTO, price, bookedSuites.size()));
        }
        bookingDTO.setBookedRoomCategories(bookedRoomCategories);

        viewController.getUseCaseController().setRoomPrice(roomPriceDropDown.getSelectionModel().getSelectedItem().toString());
        bookingDTO.setAmountGuests(amountGuestsSpinner.getValue());
    }

    private void refreshFreeRoomsInDropDowns () {
        singleRoomDropDown.getItems().setAll(roomProvider.getAllRoomsFromCategory("Single room"));
        doubleRoomDropDown.getItems().setAll(roomProvider.getAllRoomsFromCategory("Double room"));
        familyRoomDropDown.getItems().setAll(roomProvider.getAllRoomsFromCategory("Family room"));
        suiteDropDown.getItems().setAll(roomProvider.getAllRoomsFromCategory("Suite"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customers = DomainController.getSavedCustomers();
        roomProvider = new RoomProvider();
        searching = false;

        singleRoomDropDown = new CheckComboBox<>(roomProvider.getAllRoomsFromCategory("Single room"));
        doubleRoomDropDown = new CheckComboBox<>(roomProvider.getAllRoomsFromCategory("Double room"));
        familyRoomDropDown = new CheckComboBox<>(roomProvider.getAllRoomsFromCategory("Family room"));
        suiteDropDown = new CheckComboBox<>(roomProvider.getAllRoomsFromCategory("Suite"));

        singleRoomDropDown.getCheckModel().getCheckedItems().addListener(new ListChangeListener<RoomDTO>() {
            @Override
            public void onChanged(Change<? extends RoomDTO> c) {
                counterSingleRoom.setText(String.valueOf(singleRoomDropDown.getCheckModel().getCheckedItems().size()));
            }
        });
        doubleRoomDropDown.getCheckModel().getCheckedItems().addListener(new ListChangeListener<RoomDTO>() {
            @Override
            public void onChanged(Change<? extends RoomDTO> c) {
                counterDoubleRoom.setText(String.valueOf(doubleRoomDropDown.getCheckModel().getCheckedItems().size()));
            }
        });
        familyRoomDropDown.getCheckModel().getCheckedItems().addListener(new ListChangeListener<RoomDTO>() {
            @Override
            public void onChanged(Change<? extends RoomDTO> c) {
                counterFamilyRoom.setText(String.valueOf(familyRoomDropDown.getCheckModel().getCheckedItems().size()));
            }
        });
        suiteDropDown.getCheckModel().getCheckedItems().addListener(new ListChangeListener<RoomDTO>() {
            @Override
            public void onChanged(Change<? extends RoomDTO> c) {
                counterSuite.setText(String.valueOf(suiteDropDown.getCheckModel().getCheckedItems().size()));
            }
        });

        singleRoomDropDown.setLayoutX(680);
        singleRoomDropDown.setLayoutY(200);
        singleRoomDropDown.setPrefHeight(40);
        singleRoomDropDown.setPrefWidth(100);

        doubleRoomDropDown.setLayoutX(680);
        doubleRoomDropDown.setLayoutY(250);
        doubleRoomDropDown.setPrefHeight(40);
        doubleRoomDropDown.setPrefWidth(100);

        familyRoomDropDown.setLayoutX(680);
        familyRoomDropDown.setLayoutY(300);
        familyRoomDropDown.setPrefHeight(40);
        familyRoomDropDown.setPrefWidth(100);

        suiteDropDown.setLayoutX(680);
        suiteDropDown.setLayoutY(350);
        suiteDropDown.setPrefHeight(40);
        suiteDropDown.setPrefWidth(100);


        singleRoomDropDown.setConverter(new RoomNumberConverter<>(roomProvider));
        doubleRoomDropDown.setConverter(new RoomNumberConverter<>(roomProvider));
        familyRoomDropDown.setConverter(new RoomNumberConverter<>(roomProvider));
        suiteDropDown.setConverter(new RoomNumberConverter<>(roomProvider));

        contentPane.getChildren().add(singleRoomDropDown);
        contentPane.getChildren().add(doubleRoomDropDown);
        contentPane.getChildren().add(familyRoomDropDown);
        contentPane.getChildren().add(suiteDropDown);

        searchDatabaseTextField.textProperty().addListener((observable, oldValue, newValue) ->
                searchDatabaseTextFieldChanged());

        searchedCustomersListView = new ListView<>();
        searchedCustomersListView.setStyle("-fx-border-radius: 8px;");
        searchedCustomersListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(CustomerDTO item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getFirstName() + " " + item.getLastName() + " (" + item.getDateOfBirth() + ")");
                }
            }
        });
        searchedCustomersListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                searchedCustomersListViewChanged());

        searchDatabaseTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                searching = true;
            } else {
                searching = false;
                contentPane.getChildren().remove(searchedCustomersListView);
            }
        });
    }

    private BoardDTO getBoardByName(String name) {
        for (BoardDTO boardDTO : DomainController.getAllBoards()) {
            if (boardDTO.getName().equals(name)) {
                return boardDTO;
            }
        }
        return null;
    }

    @FXML
    private void departureDatePickerOnAction() {
        roomProvider.refreshFreeRooms(departureDatePicker.getValue());
        refreshFreeRoomsInDropDowns();
    }

    private void searchDatabaseTextFieldChanged() {
        if (!searching) {
            return;
        }

        String searchText = searchDatabaseTextField.getText().toLowerCase();

        if (!searchedCustomersListView.getSelectionModel().getSelectedItems().isEmpty()) {
            searchedCustomersListView.getSelectionModel().clearSelection();
        }

        if (searchText == null || searchText.equals("")) {
            contentPane.getChildren().remove(searchedCustomersListView);

        } else {
            ArrayList<CustomerDTO> searchedCustomers = new ArrayList<>();
            for (CustomerDTO c : customers) {
                String firstName = c.getFirstName().toLowerCase();
                String lastName = c.getLastName().toLowerCase();
                String fullName = firstName.concat(" ").concat(lastName);
                String reversedFullName = lastName.concat(" ").concat(firstName);
                if (
                        firstName.contains(searchText) ||
                        lastName.contains(searchText) ||
                        fullName.contains(searchText) ||
                        reversedFullName.contains(searchText)
                ) {
                    searchedCustomers.add(c);
                }
            }
            searchedCustomersListView.setItems(FXCollections.observableArrayList(searchedCustomers));

            if (!contentPane.getChildren().contains(searchedCustomersListView)) {
                searchedCustomersListView.setMinWidth(searchDatabaseTextField.getWidth());
                searchedCustomersListView.setMaxWidth(searchDatabaseTextField.getWidth());
                searchedCustomersListView.setMaxHeight(200);
                searchedCustomersListView.setLayoutX(searchDatabaseTextField.getLayoutX());
                searchedCustomersListView.setLayoutY(searchDatabaseTextField.getLayoutY() + searchDatabaseTextField.getHeight() + 4);
                contentPane.getChildren().add(searchedCustomersListView);
            }
        }
    }

    private void searchedCustomersListViewChanged() {
        CustomerDTO selectedCustomer = searchedCustomersListView.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            searching = false;
            searchDatabaseTextField.setText(selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName());
            viewController.getUseCaseController().setCustomer(selectedCustomer);
            contentPane.getChildren().remove(searchedCustomersListView);
            departureDatePicker.requestFocus();
        }
    }
}

class RoomProvider{
    private ArrayList<RoomDTO> freeRooms;

    public RoomProvider() {
        refreshFreeRooms(LocalDate.now().plusDays(1));
    }

    public RoomDTO getRoomFromNumber(int number) {
        for (RoomDTO room : DomainController.getAllRooms()) {
            if (room.getNumber() == number) {
                return room;
            }
        }
        return null;
    }

    public ObservableList<RoomDTO> getAllRoomsFromCategory(String category) {
        ObservableList<RoomDTO> freeRoomsFromCategory = FXCollections.observableArrayList(new ArrayList<>());

        for (RoomDTO room : freeRooms) {
            if (room.getCategory().getName().equals(category)) {
                freeRoomsFromCategory.add(room);
            }
        }
        return freeRoomsFromCategory;
    }

    public void refreshFreeRooms(LocalDate maxDate) {
        freeRooms = DomainController.getAllRooms();
        ArrayList<RoomDTO> bookedRooms = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (BookedRoomDTO bookedRoom : DomainController.getBookedRoomsBetween(today, maxDate)) {
            LocalDate toDate = bookedRoom.getToDate();
            LocalDate fromDate = bookedRoom.getFromDate();
            RoomDTO room = bookedRoom.getRoom();
            if (
                    !(toDate.isEqual(today) || toDate.isBefore(today)) &&
                    !(fromDate.isEqual(maxDate) || fromDate.isAfter(maxDate))
            ) {
                if (!bookedRooms.contains(room)) {
                    bookedRooms.add(room);
                }
            }
        }

        for (RoomDTO room : bookedRooms) {
            freeRooms.remove(room);
        }
    }
}


class RoomNumberConverter<T> extends StringConverter<RoomDTO> {
    RoomProvider provider;

    public RoomNumberConverter(RoomProvider provider){
        this.provider = provider;
    }

    @Override
    public RoomDTO fromString(final String number) {
        return provider.getRoomFromNumber(Integer.parseInt(number));
    }

    @Override
    public String toString(final RoomDTO room) {
        if (room == null) {
            return null;
        }
        return String.valueOf(room.getNumber());
    }
}



