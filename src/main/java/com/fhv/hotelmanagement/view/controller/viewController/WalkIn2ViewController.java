package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.services.StringValidator;
import com.fhv.hotelmanagement.view.DTOs.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.w3c.dom.Text;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WalkIn2ViewController implements Initializable {

    private static int number;

    @FXML
    private DatePicker birthdayDatePicker;
    @FXML
    private TextField countryTextField;
    @FXML
    private TextField searchDatabaseTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private ComboBox nationalityComboBox;
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
    public CheckBox customerSavedCheckBox;
    private WalkInViewController viewController;

    public void setController(WalkInViewController viewController) {
        this.viewController = viewController;
    }
    protected void saveData() throws IOException{
        CustomerDTO customer = viewController.getUseCaseController().getCustomer();
        customer.setFirstName(firstNameTextField.getText());
        customer.setLastName(lastNameTextField.getText());
        customer.setNationality(nationalityComboBox.getSelectionModel().getSelectedItem().toString());
        customer.setPhoneNumber(phoneNumberTextField.getText());
        customer.setEmail(emailTextField.getText());
        customer.setSaved(customerSavedCheckBox.isSelected());
        customer.getAddress().setStreet(streetTextField.getText());
        customer.getAddress().setCity(cityTextField.getText());
        customer.getAddress().setHouseNumber(houseNumberTextField.getText());
        customer.getAddress().setPostalCode(postalCodeTextField.getText());
        customer.getAddress().setCountry(countryTextField.getText());

        customer.setDateOfBirth(birthdayDatePicker.getValue());
        viewController.loadWalkIn3();
    }

    protected void fillData() throws IOException {
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
    }

    @FXML
    private void onBackButtonClicked(ActionEvent e) throws IOException {
        try {
            saveData();
            viewController.loadWalkIn1();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void onNextButtonClicked(ActionEvent e) throws IOException {
        try{
            if (validate()) {
                saveData();
                viewController.loadWalkIn3();
            }
        }catch(IOException exc){
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void onCancelButtonClicked(ActionEvent e){
        try{
            viewController.cancel();
        }catch(IOException exc){
            System.out.println(exc.getMessage());
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

        if (StringValidator.checkString(firstNameTextField.getText())) {
            if (StringValidator.checkRegex(firstNameTextField.getText(), "[^0-9]+$")) {
                firstNameIsValid = true;
            } else {
                setTextColor(firstNameTextField, "red");
                setEventHandler(firstNameTextField);
            }
        }
        else {
            setRequieredField(firstNameTextField);
        }

        if (StringValidator.checkString(lastNameTextField.getText())) {
            if (StringValidator.checkRegex(lastNameTextField.getText(), "[^0-9]+$")) {
                lastNameIsValid = true;
            } else {
                setTextColor(lastNameTextField, "red");
                setEventHandler(lastNameTextField);
            }
        }
        else {
            setRequieredField(lastNameTextField);
        }

        if (nationalityComboBox.getValue() != null) {
             nationalityIsValid = true;
        } else {
            nationalityIsValid = false;
        }


        if (StringValidator.checkString(emailTextField.getText())) {
            if (StringValidator.checkValidEmail(emailTextField.getText())) {
                emailIsValid = true;
            } else {
                setTextColor(emailTextField, "red");
                setEventHandler(emailTextField);
            }
        } else {
            setRequieredField(emailTextField);
        }


        if (StringValidator.checkString(postalCodeTextField.getText())) {
            if (StringValidator.checkPostalCode(postalCodeTextField.getText())) {
                postalCodeIsValid = true;
            } else {
                setTextColor(postalCodeTextField, "red");
                setEventHandler(postalCodeTextField);
            }
        }
        else {
            setRequieredField(postalCodeTextField);
        }

        if (StringValidator.checkString(cityTextField.getText())) {
            if (StringValidator.checkCity(cityTextField.getText())) {
                cityIsValid = true;
            } else {
                setTextColor(cityTextField, "red");
                setEventHandler(cityTextField);
            }
        }
        else {
            setRequieredField(cityTextField);
        }

        if (StringValidator.checkString(countryTextField.getText())) {
            if (StringValidator.checkRegex(countryTextField.getText(), "[a-zA-ZäÄöÖüÜß]*")) {
                countryIsValid = true;
            } else {
                setTextColor(countryTextField, "red");
                setEventHandler(countryTextField);
            }
        }
        else {
            setRequieredField(countryTextField);
        }

        if (StringValidator.checkString(phoneNumberTextField.getText())) {
            if (StringValidator.checkValidPhoneNumber(phoneNumberTextField.getText())) {
                phoneNumberIsValid = true;
            } else {
                setTextColor(phoneNumberTextField, "red");
                setEventHandler(phoneNumberTextField);
            }
        }
        else {
            setRequieredField(phoneNumberTextField);
        }

        if (StringValidator.checkString(houseNumberTextField.getText())) {
            if (StringValidator.checkHouseNumber(houseNumberTextField.getText())) {
                houseNumberIsValid = true;
            } else {
                setTextColor(houseNumberTextField, "red");
                setEventHandler(houseNumberTextField);
            }
        }
        else {
            setRequieredField(houseNumberTextField);
        }

        if (StringValidator.checkString(streetTextField.getText())) {
            if (StringValidator.checkStreet(streetTextField.getText())) {
                streetIsValid = true;
            } else {

                setTextColor(streetTextField, "red");
                setEventHandler(streetTextField);
            }
        }
        else {

                setRequieredField(streetTextField);
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

    private void setTextColor(TextField textField, String color) {
        textField.setStyle("-fx-text-inner-color: " + color);
    }

    private void setRequieredField(TextField textField) {
        textField.setPromptText("Pflichtfeld");
        textField.setStyle("-fx-prompt-text-fill: red");
    }

    private void setEventHandler(TextField textField){
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                setTextColor(textField, "black");
            }
        });
    }

    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<String> items = new ArrayList<>();
        items.add("Afrika");
        items.add("Asien");
        items.add("Australien");
        items.add("Belgien");
        items.add("Bulgarien");
        items.add("Dänemark");
        items.add("Deutschland");
        items.add("Estland");
        items.add("Finnland");
        items.add("Frankreich");
        items.add("Griechenland");
        items.add("Irland");
        items.add("Italien");
        items.add("Kroatien");
        items.add("Lettland");
        items.add("Lichtenstein");
        items.add("Luxemburg");
        items.add("Malta");
        items.add("Mittelamerika");
        items.add("Niederlande");
        items.add("Nordamerika");
        items.add("Österreich");
        items.add("Polen");
        items.add("Portugal");
        items.add("Rumänien");
        items.add("Schweden");
        items.add("Schweiz");
        items.add("Slowakei");
        items.add("Spanien");
        items.add("Südamerika");
        items.add("Tschechien");
        items.add("Ungarn");
        items.add("Zypern");

        ObservableList<String> countries = FXCollections.observableArrayList(items);
        nationalityComboBox.getItems().addAll(countries);

        nationalityComboBox.getSelectionModel().select(21);

        nationalityComboBox.setOnAction(event -> {
            String data = nationalityComboBox.getSelectionModel().getSelectedItem().toString();
        });
    }
}
