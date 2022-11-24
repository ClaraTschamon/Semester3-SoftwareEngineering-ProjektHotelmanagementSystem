package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.services.StringValidator;
import com.fhv.hotelmanagement.view.DTOs.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

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
    private TextField searchDatabase;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private ComboBox nationalityComboBox;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private TextField street;
    @FXML
    private TextField houseNumber;
    @FXML
    private TextField city;
    @FXML
    private TextField postalCode;
    @FXML
    private ComboBox<String> paymentMethod;
    @FXML
    private TextField creditCardNumber;
    @FXML
    private TextField verfuegernummer;
    @FXML
    private TextField billingCity;
    @FXML
    private TextField billingStreet;
    @FXML
    private TextField billingHouseNumber;
    @FXML
    private TextField billingPostalCode;
    private WalkInViewController viewController;

    public void setController(WalkInViewController viewController) {
        this.viewController = viewController;
    }
    protected void saveData() throws IOException{
        CustomerDTO customer = viewController.getUseCaseController().getCustomer();
        customer.setFirstName(firstName.getText());
        customer.setLastName(lastName.getText());
        customer.setNationality(nationalityComboBox.getSelectionModel().getSelectedItem().toString());
        customer.setPhoneNumber(phoneNumber.getText());
        customer.setEmail(email.getText());
        customer.getAddress().setStreet(street.getText());
        customer.getAddress().setCity(city.getText());
        customer.getAddress().setHouseNumber(houseNumber.getText());
        customer.getAddress().setPostalCode(postalCode.getText());
        customer.getAddress().setCountry(countryTextField.getText());

        customer.setDateOfBirth(birthdayDatePicker.getValue());
        viewController.loadWalkIn3();
    }

    protected void fillData() throws IOException {
        CustomerDTO customer = viewController.getUseCaseController().getCustomer();
        String firstname1= customer.getFirstName();
        firstName.setText(firstname1);
        String lastname1=customer.getLastName();
        lastName.setText(lastname1);

        String nationality1= customer.getNationality();
        if(nationality1 != null){
            nationalityComboBox.setValue(nationality1);
        }

        String phonenumber1 =customer.getPhoneNumber();
        phoneNumber.setText(phonenumber1);
        String email1 =customer.getEmail();
        email.setText(email1);
        String city1=customer.getAddress().getCity();
        city.setText(city1);
        String street1=customer.getAddress().getStreet();
        street.setText(street1);
        String housenumber1 =customer.getAddress().getHouseNumber();
        houseNumber.setText(housenumber1);
        String postalcode1=customer.getAddress().getPostalCode();
        postalCode.setText(postalcode1);

        String country1 = customer.getAddress().getCountry();
        countryTextField.setText(country1);

        if(customer.getDateOfBirth() != null){
            LocalDate birthday1 = customer.getDateOfBirth();
            birthdayDatePicker.setValue(birthday1);
        }
        else{
            LocalDate defaultBirthday = LocalDate.of(2000, 1, 1);
            birthdayDatePicker.setValue(defaultBirthday);
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
        firstName.getText();
        lastName.getText();
        phoneNumber.getText();
        email.getText();
        street.getText();
        houseNumber.getText();
        city.getText();
        postalCode.getText();
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

        if (StringValidator.checkString(firstName.getText())) {
            if (StringValidator.checkRegex(firstName.getText(), "[a-zA-ZäÄöÖüÜß]*")) {
                firstNameIsValid = true;
                setTextColor(firstName, "black");
                //setTextColorBlack(firstName);
            } else {
                setTextColor(firstName, "red");
                //setTextColorRed(firstName);
            }
        }
        else {
            setRequieredField(firstName);
        }

        if (StringValidator.checkString(lastName.getText())) {
            if (StringValidator.checkRegex(lastName.getText(), "[a-zA-ZäÄöÖüÜß]*")) {
                lastNameIsValid = true;
                setTextColor(lastName, "black");
                //setTextColorBlack(lastName);
            } else {
                setTextColor(lastName, "red");
                //setTextColorRed(lastName);
            }
        }
        else {
            setRequieredField(lastName);
        }

        if (nationalityComboBox.getValue() != null) {
             nationalityIsValid = true;
        } else {
            nationalityIsValid = false;
        }


        if (StringValidator.checkString(email.getText())) {
            if (StringValidator.checkValidEmail(email.getText())) {
                emailIsValid = true;
                setTextColor(email, "black");
            } else {
                setTextColor(email, "red");
            }
        } else {
            setRequieredField(email);
        }


        if (StringValidator.checkString(postalCode.getText())) {
            if (StringValidator.checkRegex(postalCode.getText(), "[0-9]*")) {
                postalCodeIsValid = true;
                setTextColor(postalCode, "black");
            } else {
                setTextColor(postalCode, "red");
            }
        }
        else {
            setRequieredField(postalCode);
        }

        if (StringValidator.checkString(city.getText())) {
            if (StringValidator.checkRegex(city.getText(), "[a-zA-ZäÄöÖüÜß]*")) {
                cityIsValid = true;
                setTextColor(city, "black");
            } else {
                setTextColor(city, "red");
            }
        }
        else {
            setRequieredField(city);
        }

        if (StringValidator.checkString(countryTextField.getText())) {
            if (StringValidator.checkRegex(countryTextField.getText(), "[a-zA-ZäÄöÖüÜß]*")) {
                countryIsValid = true;
                setTextColor(countryTextField, "black");
            } else {
                setTextColor(countryTextField, "red");
            }
        }
        else {
            setRequieredField(countryTextField);
        }

        if (StringValidator.checkString(phoneNumber.getText())) {
            if (StringValidator.checkValidPhoneNumber(phoneNumber.getText())) {
                phoneNumberIsValid = true;
                setTextColor(phoneNumber, "black");
            } else {
                setTextColor(phoneNumber, "red");
            }
        }
        else {
            setRequieredField(phoneNumber);
        }

        if (StringValidator.checkString(houseNumber.getText())) {
            if (StringValidator.checkRegex(houseNumber.getText(), "[0-9A-Za-z]*")) {
                houseNumberIsValid = true;
                setTextColor(houseNumber, "black");
            } else {
                setTextColor(houseNumber, "red");
            }
        }
        else {
            setRequieredField(houseNumber);
        }

        if (StringValidator.checkString(street.getText())) {
            if (StringValidator.checkRegex(street.getText(), "[0-9a-zA-Z-/]*")) {
                streetIsValid = true;
                setTextColor(street, "black");
            } else {
                setTextColor(street, "red");
            }
        }
        else {
                setRequieredField(street);
            }



        LocalDate dateOfBirth = birthdayDatePicker.getValue();

        if (StringValidator.calculateAge(dateOfBirth) >= 14) {
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

    private void setTextColorRed(TextField textField) {
        //textField.setPromptText(textField.getText());
        //textField.getText();

        textField.setStyle("-fx-prompt-text-fill: red");

    }

    private void setTextColorBlack(TextField textField) {
        textField.setPromptText(textField.getText());
        textField.setStyle("-fx-prompt-text-fill: black");
    }

    private void setRequieredField(TextField textField) {
        textField.setPromptText("Pflichtfeld");
        textField.setStyle("-fx-prompt-text-fill: red");
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
