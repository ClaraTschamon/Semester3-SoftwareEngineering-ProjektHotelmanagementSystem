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
    private WalkInUseCaseController useCaseController;


    @FXML
    private void onNextButtonClicked(ActionEvent e) throws IOException {
        try{
            // TODO fill all attributes
            useCaseController.getCustomer().setFirstName(firstName.getText());
            useCaseController.getCustomer().setLastName(lastName.getText());
            useCaseController.getCustomer().setNationality(nationality.getText());
            useCaseController.getCustomer().setPhoneNumber(phoneNumber.getText());
            useCaseController.getCustomer().setEmail(email.getText());
            useCaseController.getCustomer().setCity(city.getText());
            useCaseController.getCustomer().setStreet(street.getText());
            useCaseController.getCustomer().setHouseNumber(houseNumber.getText());
            useCaseController.getCustomer().setPostalCode(postalCode.getText());

            MainApplication.getMainController().loadIntoContentArea("walk-in-3");
            WalkIn3ViewController walkIn3ViewController = MainApplication.getMainController().getCurrentFXMLLoader().getController();
            walkIn3ViewController.setUseCaseController(useCaseController);
        }catch(IOException | URISyntaxException exc){
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void onBackButtonClicked(ActionEvent e) throws IOException {
        try{
            MainApplication.getMainController().loadIntoContentArea("walk-in-1");
        }catch(IOException | URISyntaxException exc){
            System.out.println(exc.getMessage());
        }
//        mainApplication.load("Walk-In1");

    }

    @FXML
    private void onCancelButtonClicked(ActionEvent e){
        try{
            MainApplication.getMainController().loadIntoContentArea("home");
        }catch(IOException | URISyntaxException exc){
            System.out.println(exc.getMessage());
        }
        //alle Daten aus TextFields löschen? --> muss ausprobiert werden!
    }

    public void setUseCaseController(WalkInUseCaseController useCaseController) {
        this.useCaseController = useCaseController;
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

        /*
        CreateCustomerUseCaseController useCaseController = new CreateCustomerUseCaseController();

        CustomerEntity customerEntity = new CustomerEntity(number, firstName.getText(), lastName.getText(),
                null, nationality.getText(), phoneNumber.getText(), email.getText(), street.getText(),
                houseNumber.getText(), postalCode.getText(), city.getText(), null, creditCardNumber.getText(), true, null);
        //useCaseController bekommt person übergeben und speichert sie in die Datenbank
        number++;

        useCaseController.storeCustomer(customerEntity);

         */
    }
}
