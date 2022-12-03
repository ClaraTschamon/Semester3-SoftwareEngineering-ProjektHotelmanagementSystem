//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.services.StringValidator;
import com.fhv.hotelmanagement.services.TextFunction;
import com.fhv.hotelmanagement.view.DTOs.CustomerDTO;

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
import org.controlsfx.control.SearchableComboBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WalkIn2ViewController implements Initializable {

    @FXML
    private AnchorPane contentPane;
    @FXML
    private DatePicker birthdayDatePicker;
    @FXML
    private TextField countryTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private SearchableComboBox nationalityComboBox;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private TextField houseNumberTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField postalCodeTextField;
    @FXML
    private CheckBox customerSavedCheckBox;
    @FXML
    private TextField searchDatabaseTextField;
    private ListView<CustomerDTO> searchedCustomersListView;
    private boolean searching;
    private WalkInViewController viewController;


    public void setController(WalkInViewController viewController) {
        this.viewController = viewController;
    }
    protected void saveData() {
        CustomerDTO customer = viewController.getUseCaseController().getCustomer();
        customer.setFirstName(firstNameTextField.getText());
        customer.setLastName(lastNameTextField.getText());
        if(nationalityComboBox.getSelectionModel().getSelectedItem() != null){
            customer.setNationality(nationalityComboBox.getSelectionModel().getSelectedItem().toString());
        }
        customer.setPhoneNumber(phoneNumberTextField.getText());
        customer.setEmail(emailTextField.getText());
        customer.setSaved(customerSavedCheckBox.isSelected());
        customer.getAddress().setStreet(streetTextField.getText());
        customer.getAddress().setCity(cityTextField.getText());
        customer.getAddress().setHouseNumber(houseNumberTextField.getText());
        customer.getAddress().setPostalCode(postalCodeTextField.getText());
        customer.getAddress().setCountry(countryTextField.getText());

        customer.setDateOfBirth(birthdayDatePicker.getValue());
        try {
            viewController.loadWalkIn3();
        } catch (IOException e) {
            System.out.println("Error loading walk in 3: " + e.getMessage());
            MainApplication.getMainController().alert("Error loading next page, please try again.", WarningType.WARNING);
        }
    }

    protected void fillData() {
        CustomerDTO customer = viewController.getUseCaseController().getCustomer();
        String firstname= customer.getFirstName();
        firstNameTextField.setText(firstname);
        String lastname=customer.getLastName();
        lastNameTextField.setText(lastname);

        String nationality= customer.getNationality();
        if(nationality != null){
            nationalityComboBox.setValue(nationality);
        }

        String phonenumber =customer.getPhoneNumber();
        phoneNumberTextField.setText(phonenumber);
        String email =customer.getEmail();
        emailTextField.setText(email);
        String city=customer.getAddress().getCity();
        cityTextField.setText(city);
        String street=customer.getAddress().getStreet();
        streetTextField.setText(street);
        String housenumber =customer.getAddress().getHouseNumber();
        houseNumberTextField.setText(housenumber);
        String postalcode=customer.getAddress().getPostalCode();
        postalCodeTextField.setText(postalcode);

        String country = customer.getAddress().getCountry();
        countryTextField.setText(country);

        if(customer.getDateOfBirth() != null){
            LocalDate birthday = customer.getDateOfBirth();
            birthdayDatePicker.setValue(birthday);
        }
        else{
            LocalDate defaultBirthday = LocalDate.of(2000, 1, 1);
            birthdayDatePicker.setValue(defaultBirthday);
        }

        if(customer.getSaved() != null){
            boolean saved = customer.getSaved();
            customerSavedCheckBox.setSelected(saved);
        }else{
            boolean defaultSaved = true;
            customerSavedCheckBox.setSelected(defaultSaved);
        }

        if (customer != null && customer.getNumber() != null) {
            String name = customer.getFirstName() + " " + customer.getLastName();
            searchDatabaseTextField.setText("Guest: " + name);
        } else {
            searchDatabaseTextField.setText("");
            searchDatabaseTextField.setPromptText("Search guest in guest file...");
        }
    }

    @FXML
    private void onBackButtonClicked(ActionEvent e) {
        try {
            saveData();
            viewController.loadWalkIn1();
        } catch (IOException exc) {
            System.out.println("Error clicking back button: " + exc.getMessage());
            MainApplication.getMainController().alert("Error loading next page, please try again.", WarningType.WARNING);
        }
    }

    @FXML
    private void onNextButtonClicked(ActionEvent e) {
        try{
            if (validate()) {
                saveData();
                viewController.loadWalkIn3();
            }
        }catch(IOException exc){
            System.out.println("Error clicking next button: " + exc.getMessage());
            MainApplication.getMainController().alert("Error loading next page, please try again.", WarningType.WARNING);
        }
    }

    @FXML
    private void onCancelButtonClicked(ActionEvent e){
        try{
            viewController.cancel();
        }catch(IOException exc){
            System.out.println("Error clicking cancel button: " + exc.getMessage());
            MainApplication.getMainController().alert("Error cancelling, please try again.", WarningType.WARNING);
        }
    }

    private boolean validate() throws IOException {
        firstNameTextField.getText();
        lastNameTextField.getText();
        phoneNumberTextField.getText();
        emailTextField.getText();
        streetTextField.getText();
        houseNumberTextField.getText();
        cityTextField.getText();
        postalCodeTextField.getText();
        countryTextField.getText();

        boolean firstNameIsValid = false;
        boolean lastNameIsValid = false;
        boolean nationalityIsValid = false;
        boolean phoneNumberIsValid = false;
        boolean emailIsValid = false;
        boolean streetIsValid = false;
        boolean houseNumberIsValid = false;
        boolean cityIsValid = false;
        boolean postalCodeIsValid = false;
        boolean countryIsValid = false;
        boolean birthdayIsValid = false;
        //warum alles außer zahlen?
        if (!StringValidator.checkString(firstNameTextField.getText())) {
            TextFunction.setRequieredTextField(firstNameTextField);
        } else if (StringValidator.checkRegex(firstNameTextField.getText(), "[^0-9]+$")) {
            firstNameIsValid = true;
        } else {
            TextFunction.setTextFieldColor(firstNameTextField, "red");
            TextFunction.setEventHandler(firstNameTextField);
        }

        if (!StringValidator.checkString(lastNameTextField.getText())) {
            TextFunction.setRequieredTextField(lastNameTextField);
        } else if (StringValidator.checkRegex(lastNameTextField.getText(), "[^0-9]+$")) {
            lastNameIsValid = true;
        } else {
            TextFunction.setTextFieldColor(lastNameTextField, "red");
            TextFunction.setEventHandler(lastNameTextField);
        }

        /*
        if (nationalityComboBox.getSelectionModel().getSelectedItem() != null) {
             nationalityIsValid = true;
        }*/

        if (!nationalityComboBox.getSelectionModel().getSelectedItem().equals("")) {
            nationalityIsValid = true;
        }else{
            nationalityComboBox.setStyle("-fx-border-color: red");
        }

        if (!StringValidator.checkString(emailTextField.getText())) {
            TextFunction.setRequieredTextField(emailTextField);
        } else if (StringValidator.checkValidEmail(emailTextField.getText())) {
            emailIsValid = true;
        } else {
            TextFunction.setTextFieldColor(emailTextField, "red");
            TextFunction.setEventHandler(emailTextField);
        }

        if (!StringValidator.checkString(postalCodeTextField.getText())) {
            TextFunction.setRequieredTextField(postalCodeTextField);
        } else if (StringValidator.checkPostalCode(postalCodeTextField.getText())) {
            postalCodeIsValid = true;
        } else {
            TextFunction.setTextFieldColor(postalCodeTextField, "red");
            TextFunction.setEventHandler(postalCodeTextField);
        }

        if (!StringValidator.checkString(cityTextField.getText())) {
            TextFunction.setRequieredTextField(cityTextField);
        } else if (StringValidator.checkCity(cityTextField.getText())) {
            cityIsValid = true;
        } else {
            TextFunction.setTextFieldColor(cityTextField, "red");
            TextFunction.setEventHandler(cityTextField);
        }

        if (!StringValidator.checkString(countryTextField.getText())) {
            TextFunction.setRequieredTextField(countryTextField);
        } else if (StringValidator.checkRegex(countryTextField.getText(), "[a-zA-ZäÄöÖüÜß ]*")) {
            countryIsValid = true;
        } else {
            TextFunction.setTextFieldColor(countryTextField, "red");
            TextFunction.setEventHandler(countryTextField);
        }

        if (!StringValidator.checkString(phoneNumberTextField.getText())) {
            TextFunction.setRequieredTextField(phoneNumberTextField);
        } else if (StringValidator.checkValidPhoneNumber(phoneNumberTextField.getText())) {
            phoneNumberIsValid = true;
        } else {
            TextFunction.setTextFieldColor(phoneNumberTextField, "red");
            TextFunction.setEventHandler(phoneNumberTextField);
        }

        if (!StringValidator.checkString(houseNumberTextField.getText())) {
            TextFunction.setRequieredTextField(houseNumberTextField);
        } else if (StringValidator.checkHouseNumber(houseNumberTextField.getText())) {
            houseNumberIsValid = true;
        } else {
            TextFunction.setTextFieldColor(houseNumberTextField, "red");
            TextFunction.setEventHandler(houseNumberTextField);
        }

        if (!StringValidator.checkString(streetTextField.getText())) {
            TextFunction.setRequieredTextField(streetTextField);
        } else if (StringValidator.checkStreet(streetTextField.getText())) {
            streetIsValid = true;
        } else {
            TextFunction.setTextFieldColor(streetTextField, "red");
            TextFunction.setEventHandler(streetTextField);
        }

        LocalDate dateOfBirth = birthdayDatePicker.getValue();

        if (StringValidator.calculateAge(dateOfBirth) >= 16) {
            birthdayIsValid = true;
        } else {
            birthdayDatePicker.setStyle("-fx-text-inner-color: red");
        }

        if(emailIsValid && firstNameIsValid && lastNameIsValid && nationalityIsValid &&
                phoneNumberIsValid && countryIsValid && streetIsValid && postalCodeIsValid &&
                houseNumberIsValid && cityIsValid && birthdayIsValid) {
            return true;
        }
        return false;
    }

    public void initialize(URL location, ResourceBundle resources) {
        searching = false;

        ArrayList<String> items = new ArrayList<>();
        items.add("Africa");
        items.add("Asia");
        items.add("Australia");
        items.add("Austria");
        items.add("Belgium");
        items.add("Bulgaria");
        items.add("Central America");
        items.add("Croatia");
        items.add("Cyprus");
        items.add("Czech Republic");
        items.add("Denmark");
        items.add("Estonia");
        items.add("Finland");
        items.add("France");
        items.add("Germany");
        items.add("Greece");
        items.add("Hungary");
        items.add("Ireland");
        items.add("Italy");
        items.add("Latvia");
        items.add("Liechtenstein");
        items.add("Luxembourg");
        items.add("Malta");
        items.add("Netherlands");
        items.add("North America");
        items.add("Poland");
        items.add("Portugal");
        items.add("Romania");
        items.add("Slovakia");
        items.add("South America");
        items.add("Spain");
        items.add("Sweden");
        items.add("Switzerland");

        ObservableList<String> countries = FXCollections.observableArrayList(items);
        nationalityComboBox.getItems().addAll(countries);

        nationalityComboBox.getSelectionModel().select(3);


        nationalityComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(!(nationalityComboBox.getSelectionModel().getSelectedItem() == null)){
                    if(nationalityComboBox.getSelectionModel().getSelectedItem().equals("")){
                        nationalityComboBox.getSelectionModel().select(oldValue);
                    }
                }
                nationalityComboBox.setStyle("-fx-border-color: none");
            }
        });

        // search
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

        searchDatabaseTextField.focusedProperty().addListener((observable, oldValue, newValue) ->
                searchDatabaseTextFieldFocusChanged(newValue));
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
            for (CustomerDTO c : viewController.getUseCaseController().getCustomers()) {
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
            viewController.getUseCaseController().setCustomer(selectedCustomer);
            fillData();
            contentPane.getChildren().remove(searchedCustomersListView);
            firstNameTextField.requestFocus();
        }
    }

    private void searchDatabaseTextFieldFocusChanged(boolean newValue) {
        if (newValue) {
            searching = true;
        } else {
            searching = false;
            contentPane.getChildren().remove(searchedCustomersListView);
        }
    }
}
