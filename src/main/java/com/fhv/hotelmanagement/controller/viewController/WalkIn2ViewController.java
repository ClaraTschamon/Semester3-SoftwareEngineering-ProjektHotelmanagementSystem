package com.fhv.hotelmanagement.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.controller.useCaseController.WalkInUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URISyntaxException;

public class WalkIn2ViewController {
    //TODO: Tabulator automatisch auf Vorname setzen

    private static int number;

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

    @FXML
    private void onBackButtonClicked(ActionEvent e) throws IOException {
        try {
            viewController.loadWalkIn1();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void onNextButtonClicked(ActionEvent e) throws IOException {
        try{
            // TODO fill all attributes
            viewController.getUseCaseController().getCustomer().setFirstName(firstName.getText());
            viewController.getUseCaseController().getCustomer().setLastName(lastName.getText());
            viewController.getUseCaseController().getCustomer().setNationality(nationality.getText());
            viewController.getUseCaseController().getCustomer().setPhoneNumber(phoneNumber.getText());
            viewController.getUseCaseController().getCustomer().setEmail(email.getText());
            viewController.getUseCaseController().getCustomer().getAddress().setCity(city.getText());
            viewController.getUseCaseController().getCustomer().getAddress().setStreet(street.getText());
            viewController.getUseCaseController().getCustomer().getAddress().setHouseNumber(houseNumber.getText());
            viewController.getUseCaseController().getCustomer().getAddress().setPostalCode(postalCode.getText());

            viewController.loadWalkIn3();
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
}
