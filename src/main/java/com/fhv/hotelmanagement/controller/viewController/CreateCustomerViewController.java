package com.fhv.hotelmanagement.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.controller.useCaseController.CreateCustomerUseCaseController;
import com.fhv.hotelmanagement.persistence.persistenceEntity.CustomerEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateCustomerViewController {

    //TODO: customer date of birth wird nicht abgefragt! umändern oder aus datenbank löschen?
    //TODO: country wird nicht abgefragt... ändern oder feld aus datenbank löschen??
    //TODO: verfügernummer und ablaufdatum der kreditkarte fehlt in datenbank

    private static int number;
    //MainController mainController = new MainController();
    MainApplication mainApplication = new MainApplication();

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


    @FXML
    private void onNextButtonClicked(ActionEvent e) throws IOException {
//        mainApplication.load("Walk-In3");
    }

    @FXML
    private void onBackButtonClicked(ActionEvent e) throws IOException {
        //load previous Page
//        mainApplication.load("Walk-In1");

    }

    @FXML
    private void onCancelButtonClicked(ActionEvent e){
        //load HomePage
        //alle Daten aus TextFields löschen? --> muss ausprobiert werden!
    }



    //Payment Page (Walk-In3)
    @FXML
    private void onBackButtonClickedPayment(ActionEvent e) throws IOException {
//        mainApplication.load("Walk-In2");
    }


    @FXML
    private void onCancelButtonClickedPayment(ActionEvent e){
        //alle Textfelder löschen
        //home menü laden

    }


    @FXML
    private void onSaveButtonClicked(ActionEvent e){
        //daten in datenbank speichern und alle textfelder löschen
        //      (ausprobieren ob es nicht automatisch gelöscht wird)
        //home menü laden

        CreateCustomerUseCaseController useCaseController = new CreateCustomerUseCaseController();

        CustomerEntity customerEntity = new CustomerEntity(number, firstName.getText(), lastName.getText(),
                null, nationality.getText(), phoneNumber.getText(), email.getText(), street.getText(),
                houseNumber.getText(), postalCode.getText(), city.getText(), null, creditCardNumber.getText(), true, null);
        //useCaseController bekommt person übergeben und speichert sie in die Datenbank
        number++;

        useCaseController.storeCustomer(customerEntity);
    }
}
