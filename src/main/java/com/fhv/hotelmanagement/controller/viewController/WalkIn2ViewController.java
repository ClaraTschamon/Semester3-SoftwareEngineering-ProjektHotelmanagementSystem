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

    private WalkInUseCaseController useCaseController;

    @FXML
    private void onNextButtonClicked(ActionEvent e) throws IOException {
        try {
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
                    MainApplication.getMainController().loadIntoContentArea("walk-in-3");
                    WalkIn3ViewController walkIn3ViewController = MainApplication.getMainController().getCurrentFXMLLoader().getController();
                    walkIn3ViewController.setUseCaseController(useCaseController);
                }

            } catch (IOException | URISyntaxException exc) {
                System.out.println(exc.getMessage());
            }
        }

        @FXML
        private void onBackButtonClicked (ActionEvent e) throws IOException {
            try {
                MainApplication.getMainController().loadIntoContentArea("walk-in-1");
            } catch (IOException | URISyntaxException exc) {
                System.out.println(exc.getMessage());
            }
//        mainApplication.load("Walk-In1");

        }

        @FXML
        private void onCancelButtonClicked (ActionEvent e){
            try {
                MainApplication.getMainController().loadIntoContentArea("home");
            } catch (IOException | URISyntaxException exc) {
                System.out.println(exc.getMessage());
            }
            //alle Daten aus TextFields löschen? --> muss ausprobiert werden!
        }

        public void setUseCaseController (WalkInUseCaseController useCaseController){
            this.useCaseController = useCaseController;
        }


        //Payment Page (Walk-In3)
        @FXML
        private void onBackButtonClickedPayment (ActionEvent e) throws IOException {
//        mainApplication.load("Walk-In2");
        }


        @FXML
        private void onCancelButtonClickedPayment (ActionEvent e){
            //alle Textfelder löschen
            //home menü laden

        }


        @FXML
        private void onSaveButtonClicked (ActionEvent e){
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
