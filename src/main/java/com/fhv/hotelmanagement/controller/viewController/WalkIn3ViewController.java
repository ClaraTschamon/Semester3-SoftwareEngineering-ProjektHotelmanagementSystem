package com.fhv.hotelmanagement.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.controller.useCaseController.WalkInUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URISyntaxException;

public class WalkIn3ViewController {
    WalkInUseCaseController useCaseController;

    @FXML
    private TextField expirationDateTextfield;

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

    @FXML
    public void onBackButtonClickedPayment(ActionEvent e) throws IOException, URISyntaxException {
        MainApplication.getMainController().loadIntoContentArea("walk-in-2");
    }

    @FXML
    public void onCancelButtonClickedPayment(ActionEvent e) throws IOException, URISyntaxException {
        MainApplication.getMainController().loadIntoContentArea("home");
    }

    @FXML
    public void onSaveButtonClicked(ActionEvent e) throws IOException, URISyntaxException {
        // TODO fill all attributes
        String textfieldCreditCardNumber = creditCardNumber.getText();
        String textfieldVerfuegerNummer = verfuegernummer.getText();
        String textfieldBillingCity = billingCity.getText();
        String textfieldBillingHouseNumber = billingHouseNumber.getText();
        String textfieldBillingStreet = billingStreet.getText();
        String textfieldBillingPostalCode = billingPostalCode.getText();
        String textfieldLoc = expirationDateTextfield.getText();

        boolean creditCardNumberIsValid = false;
        boolean verfuegerNummerIsValid = false;
        boolean billingCityIsValid = false;
        boolean billingHouseNumberIsValid = false;
        boolean billingStreetIsValid = false;
        boolean billingPostalCodeIsValid = false;
        boolean locIsValid = false;

        if (!textfieldCreditCardNumber.isEmpty() && textfieldCreditCardNumber.matches("[0-9]{8,19}")) {
            creditCardNumberIsValid = true;
        } else if(textfieldCreditCardNumber.isEmpty()) {
            creditCardNumber.setStyle("-fx-text-inner-color: red");
            creditCardNumber.setText("Pflichtfeld");
        } else if(!textfieldCreditCardNumber.matches("[0-9]*")) {
            creditCardNumber.setStyle("-fx-text-inner-color: red");
            creditCardNumber.setText("inkorrekte eingabe");
        } else if(textfieldCreditCardNumber.length() < 8 || textfieldCreditCardNumber.length() >19) {
            creditCardNumber.setStyle("-fx-text-inner-color: red");
            creditCardNumber.setText("die Kreditkartennummer hat 8 bis 19 Ziffern");
        }

        if (!textfieldVerfuegerNummer.isEmpty() && textfieldVerfuegerNummer.matches("[0-9]{3}")) {
            verfuegerNummerIsValid = true;
        } else if(textfieldVerfuegerNummer.isEmpty()) {
            verfuegernummer.setStyle("-fx-text-inner-color: red");
            verfuegernummer.setText("Pflichtfeld");
        } else if(!textfieldVerfuegerNummer.matches("[0-9]*")) {
            verfuegernummer.setStyle("-fx-text-inner-color: red");
            verfuegernummer.setText("inkorrekte Eingabe");
        } else if(textfieldVerfuegerNummer.length() < 3 || textfieldVerfuegerNummer.length() > 3) {
            verfuegernummer.setStyle("-fx-text-inner-color: red");
            verfuegernummer.setText("die Sichherheitsnummer hat 3 Ziffern");
        }

        if (!textfieldBillingCity.isEmpty() && textfieldBillingCity.matches("[a-zA-ZäÄöÖüÜß]*")) {
            billingCityIsValid = true;
        } else if(textfieldBillingCity.isEmpty()) {
            billingCity.setStyle("-fx-text-inner-color: red");
            billingCity.setText("Pflichtfeld");
        } else if(!textfieldBillingCity.matches("[a-zA-ZäÄöÖüÜß]*")) {
            billingCity.setStyle("-fx-text-inner-color: red");
            billingCity.setText("inkorrekte Eingabe");
        }

        if (!textfieldBillingHouseNumber.isEmpty() && textfieldBillingHouseNumber.matches("[0-9]*")) {
            billingHouseNumberIsValid = true;
        } else if(textfieldBillingHouseNumber.isEmpty()) {
            billingHouseNumber.setStyle("-fx-text-inner-color: red");
            billingHouseNumber.setText("Pflichtfeld");
        } else if(!textfieldBillingHouseNumber.matches("[0-9]*")) {
            billingHouseNumber.setStyle("-fx-text-inner-color: red");
            billingHouseNumber.setText("inkorrekte Eingabe");
        }

        if (!textfieldBillingStreet.isEmpty() && textfieldBillingStreet.matches("[a-zA-ZäÄöÖüÜß]*")) {
            billingStreetIsValid = true;
        } else if(textfieldBillingStreet.isEmpty()) {
            billingStreet.setStyle("-fx-text-inner-color: red");
            billingStreet.setText("Pflichtfeld");
        } else if(!textfieldBillingStreet.matches("[a-zA-ZäÄöÖüÜß]*")) {
            billingStreet.setStyle("-fx-text-inner-color: red");
            billingStreet.setText("inkorrekte Eingabe");
        }

        if (!textfieldBillingPostalCode.isEmpty() && textfieldBillingPostalCode.matches("[0-9]{1,10}")) {
            billingPostalCodeIsValid = true;
        } else if(textfieldBillingPostalCode.isEmpty()) {
            billingPostalCode.setStyle("-fx-text-inner-color: red");
            billingPostalCode.setText("Pflichtfeld");
        } else if(!textfieldBillingPostalCode.matches("[0-9]*")) {
            billingPostalCode.setStyle("-fx-text-inner-color: red");
            billingPostalCode.setText("inkorrekte Eingabe");
        } else if(textfieldBillingPostalCode.length() < 1 || textfieldBillingPostalCode.length() > 10) {
            billingPostalCode.setStyle("-fx-text-inner-color: red");
            billingPostalCode.setText("die Postleitzahl");
        }

        if (!textfieldLoc.isEmpty() && textfieldLoc.matches("[0-1][1-9]/[0-9][0-9]")) {
            locIsValid = true;
        } else if(textfieldLoc.isEmpty()) {
            expirationDateTextfield.setStyle("-fx-text-inner-color: red");
            expirationDateTextfield.setText("Pflichtfeld");
        } else if(!textfieldLoc.matches("[0-1][1-9]/[0-9][0-9]")) {
            expirationDateTextfield.setStyle("-fx-text-inner-color: red");
            expirationDateTextfield.setText("inkorrekte Eingabe");
        }

        if(creditCardNumberIsValid && verfuegerNummerIsValid && billingStreetIsValid &&
        billingHouseNumberIsValid && billingCityIsValid && billingPostalCodeIsValid && verfuegerNummerIsValid) {
            useCaseController.save();
            MainApplication.getMainController().loadIntoContentArea("home");
        }

    }

    public void setUseCaseController(WalkInUseCaseController useCaseController) {
        this.useCaseController = useCaseController;
    }
}
