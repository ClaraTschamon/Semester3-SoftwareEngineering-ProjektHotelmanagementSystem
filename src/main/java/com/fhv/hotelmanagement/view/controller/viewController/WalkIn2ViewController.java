package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.view.DTOs.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;

public class WalkIn2ViewController {
    //TODO: Tabulator automatisch auf Vorname setzen

    private static int number;

    @FXML
    private DatePicker birthday;
    @FXML
    private TextField countryTextField;
    @FXML
    private TextField searchDatabase;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField nationality;
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
    @FXML TextField verfuegernummer;
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
        customer.setNationality(nationality.getText());
        customer.setPhoneNumber(phoneNumber.getText());
        customer.setEmail(email.getText());
        customer.getAddress().setStreet(street.getText());
        customer.getAddress().setCity(city.getText());
        customer.getAddress().setHouseNumber(houseNumber.getText());
        customer.getAddress().setPostalCode(postalCode.getText());
        customer.getAddress().setCountry(countryTextField.getText());
        customer.setDateOfBirth(birthday.getValue());
        viewController.loadWalkIn3();
    }

    protected void fillData() throws IOException {
        CustomerDTO customer = viewController.getUseCaseController().getCustomer();
        String firstname1= customer.getFirstName();
        firstName.setText(firstname1);
        String lastname1=customer.getLastName();
        lastName.setText(lastname1);
        String nationality1= customer.getNationality();
        nationality.setText(nationality1);
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
        LocalDate birthday1 = customer.getDateOfBirth();
        birthday.setValue(birthday1);
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
        String textfieldFirstName = firstName.getText();
        String textfieldLastName = lastName.getText();
        String textfieldNationality = nationality.getText();
        String textfieldPhoneNumber = phoneNumber.getText();
        String textfieldEmail = email.getText();
        String textfieldStreet = street.getText();
        String textfieldHouseNumber = houseNumber.getText();
        String textfieldCity = city.getText();
        String textfieldPostalCode = postalCode.getText();

        boolean firstNameIsValid = false;
        boolean lastNameIsValid = false;
        boolean nationalityIsValid = false;
        boolean phoneNumberIsValid = false;
        boolean emailIsValid = false;
        boolean streetIsValid = false;
        boolean houseNumberIsValid = false;
        boolean cityIsValid = false;
        boolean postalCodeIsValid = false;

        if (!textfieldFirstName.isEmpty() && textfieldFirstName.matches("[a-zA-ZäÄöÖüÜß]*")) {
            firstNameIsValid = true;
        } else if(textfieldFirstName.isEmpty()) {
            firstName.setStyle("-fx-text-inner-color: red");
            firstName.setText("Pflichtfeld");
        } else if(!textfieldFirstName.matches("[a-zA-ZäÄöÖüÜß]*")) {
            firstName.setStyle("-fx-text-inner-color: red");
            firstName.setText("inkorrekte Eingabe");
        }

        if (!textfieldLastName.isEmpty() && textfieldLastName.matches("[a-zA-ZäÄöÖüÜß]*")) {
            lastNameIsValid = true;
        } else if(textfieldLastName.isEmpty()) {
            lastName.setStyle("-fx-text-inner-color: red");
            lastName.setText("Pflichtfeld");
        } else if(!textfieldLastName.matches("[a-zA-ZäÄöÖüÜß]*")) {
            lastName.setStyle("-fx-text-inner-color: red");
            lastName.setText("inkorrekte Eingabe");
        }

        if (!textfieldNationality.isEmpty() && textfieldNationality.matches("[a-zA-ZäÄöÖüÜß]*")) {
            nationalityIsValid = true;
        } else if(textfieldNationality.isEmpty()) {
            nationality.setStyle("-fx-text-inner-color: red");
            nationality.setText("Pflichtfeld");
        } else if(!textfieldNationality.matches("[a-zA-ZäÄöÖüÜß]*")) {
            nationality.setStyle("-fx-text-inner-color: red");
            nationality.setText("inkorrekte Eingabe");
        }

        if (!textfieldPhoneNumber.isEmpty() && textfieldPhoneNumber.matches("[0-9]{7,15}")) {
            phoneNumberIsValid = true;
        } else if(textfieldPhoneNumber.isEmpty()) {
            phoneNumber.setStyle("-fx-text-inner-color: red");
            phoneNumber.setText("Pflichtfeld");
        } else if(!textfieldPhoneNumber.matches("[0-9]*")) {
            phoneNumber.setStyle("-fx-text-inner-color: red");
            phoneNumber.setText("inkorrekte Eingabe");
        } else if(textfieldPhoneNumber.length() > 7 || textfieldPhoneNumber.length() < 15) {
            phoneNumber.setStyle("-fx-text-inner-color: red");
            phoneNumber.setText("länge der Telefonnummer beachten");
        }

        if (!textfieldEmail.isEmpty() && textfieldEmail.matches("^(.+)@(.+)$")) {
            emailIsValid = true;
        } else if(textfieldEmail.isEmpty()) {
            email.setStyle("-fx-text-inner-color: red");
            email.setText("Pflichtfeld");
        } else if(!textfieldEmail.matches("^(.+)@(.+)$")) {
            email.setStyle("-fx-text-inner-color: red");
            email.setText("inkorrekte Eingabe");
        }

        if (!textfieldStreet.isEmpty() && textfieldStreet.matches("[a-zA-ZäÄöÖüÜß]*")) {
            streetIsValid = true;
        } else if(textfieldStreet.isEmpty()) {
            street.setStyle("-fx-text-inner-color: red");
            street.setText("Pflichtfeld");
        } else if(!textfieldStreet.matches("[a-zA-ZäÄöÖüÜß]*")) {
            street.setStyle("-fx-text-inner-color: red");
            street.setText("inkorrekte Eingabe");
        }

        if (!textfieldHouseNumber.isEmpty() && textfieldHouseNumber.matches("[0-9]*")) {
            houseNumberIsValid = true;
        } else if(textfieldHouseNumber.isEmpty()) {
            houseNumber.setStyle("-fx-text-inner-color: red");
            houseNumber.setText("Pflichtfeld");
        } else if(!textfieldHouseNumber.matches("[0-9]*")) {
            houseNumber.setStyle("-fx-text-inner-color: red");
            houseNumber.setText("inkorrekte Eingabe");
        }

        if (!textfieldCity.isEmpty() && textfieldCity.matches("[a-zA-ZäÄöÖüÜß]*")) {
            cityIsValid = true;
        } else if(textfieldCity.isEmpty()) {
            city.setStyle("-fx-text-inner-color: red");
            city.setText("Pflichtfeld");
        } else if(!textfieldCity.matches("[a-zA-ZäÄöÖüÜß]*")) {
            city.setStyle("-fx-text-inner-color: red");
            city.setText("inkorrekte Eingabe");
        }

        if (!textfieldPostalCode.isEmpty() && textfieldPostalCode.matches("[0-9]*")) {
            postalCodeIsValid = true;
        } else if(textfieldPostalCode.isEmpty()) {
            postalCode.setStyle("-fx-text-inner-color: red");
            postalCode.setText("Pflichtfeld");
        } else if(!textfieldPostalCode.matches("[0-9]*")) {
            postalCode.setStyle("-fx-text-inner-color: red");
            postalCode.setText("inkorrekte Eingabe");
        }

        if (firstNameIsValid && lastNameIsValid && nationalityIsValid && phoneNumberIsValid &&
                emailIsValid && streetIsValid && houseNumberIsValid && cityIsValid && postalCodeIsValid) {
            return true;
        }
        return false;
    }
}
