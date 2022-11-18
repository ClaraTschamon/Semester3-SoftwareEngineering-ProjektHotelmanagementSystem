package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.view.DTOs.AddressDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class WalkIn3ViewController {
    @FXML
    private TextField billingCountryTextField;
    @FXML
    private TextField creditCardTextField;
    @FXML
    private TextField authorisationNumberTextField;
    @FXML
    private TextField expiryDateTextField;
    @FXML
    private CheckBox billingAddressEqualsCustomerAddressCheckBox;
    @FXML
    private TextField billingStreetTextField;
    @FXML
    private TextField billingHouseNumberTextField;
    @FXML
    private TextField billingCityTextField;
    @FXML
    private TextField billingPostalCodeTextField;
    @FXML
    private TextArea notesTextArea;
    WalkInViewController viewController;
    @FXML
    private ComboBox paymentMethod;

    /*if you go back after you clicked the "billingAddressEqualsCustomerAddressButton" it will delete the filled in customer bill address
    -->This prevents false information to be saved (if the customer chooses to replace his address after going back to walk-In2*/
    private boolean billingAddressEqualsCustomerAddressBackButtonClick;

    public void setController(WalkInViewController viewController) {
        this.viewController = viewController;
    }

    public void fillData() {
        BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();
        String creditCard = bookingDTO.getCreditCardNumber();
        creditCardTextField.setText(creditCard);
        String authorisationNumber=bookingDTO.getAuthorisationNumber();
        authorisationNumberTextField.setText(authorisationNumber);
        String expiryDate=bookingDTO.getExpirationDate();
        expiryDateTextField.setText(expiryDate);
        String comment = bookingDTO.getComment(); //Comment = Notes
        notesTextArea.setText(comment);

        if(bookingDTO.getPaymentMethod()!=null){
            String payment =bookingDTO.getPaymentMethod();
            paymentMethod.setValue(payment);
        }

        AddressDTO addressDTO = viewController.getUseCaseController().getBooking().getBillingAddress();
        String billingStreet = addressDTO.getStreet();
        billingStreetTextField.setText(billingStreet);
        String billingHouseNumber = addressDTO.getHouseNumber();
        billingHouseNumberTextField.setText(billingHouseNumber);
        String billingCity =addressDTO.getCity();
        billingCityTextField.setText(billingCity);
        String billingPostalCode = addressDTO.getPostalCode();
        billingPostalCodeTextField.setText(billingPostalCode);
        String billingCountry = addressDTO.getCountry();
        billingCountryTextField.setText(billingCountry);

        billingAddressEqualsCustomerAddressCheckBox.setSelected(viewController.getUseCaseController().isBillingAddressEqualsCustomerAddress());
        fillBillingAddressData();
    }

    public void fillBillingAddressData() {
        if (billingAddressEqualsCustomerAddressCheckBox.isSelected()) {
            AddressDTO address = viewController.getUseCaseController().getCustomer().getAddress();
            billingStreetTextField.setText(address.getStreet());
            billingHouseNumberTextField.setText(address.getHouseNumber());
            billingCityTextField.setText(address.getCity());
            billingPostalCodeTextField.setText(address.getPostalCode());
            billingCountryTextField.setText(address.getCountry());
        } else {
            billingStreetTextField.setText("");
            billingHouseNumberTextField.setText("");
            billingCityTextField.setText("");
            billingPostalCodeTextField.setText("");
            billingCountryTextField.setText("");
        }
    }

    protected void saveData(){
        BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();
        bookingDTO.setCreditCardNumber(creditCardTextField.getText());
        bookingDTO.setAuthorisationNumber(authorisationNumberTextField.getText());
        bookingDTO.setExpirationDate(expiryDateTextField.getText());
        bookingDTO.setComment(notesTextArea.getText());
        bookingDTO.setPaymentMethod((String) paymentMethod.getSelectionModel().getSelectedItem());

        AddressDTO billingAddressDTO = viewController.getUseCaseController().getBooking().getBillingAddress();
        billingAddressDTO.setStreet(billingStreetTextField.getText());
        billingAddressDTO.setHouseNumber(billingHouseNumberTextField.getText());
        billingAddressDTO.setCity(billingCityTextField.getText());
        billingAddressDTO.setPostalCode(billingPostalCodeTextField.getText());
        billingAddressDTO.setCountry(billingCountryTextField.getText());
        viewController.getUseCaseController().setBillingAddressEqualsCustomerAddress(billingAddressEqualsCustomerAddressCheckBox.isSelected());
    }

    @FXML
    public void onBackButtonClickedPayment(ActionEvent e) {
        try {
            saveData();
            viewController.loadWalkIn2();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    public void onSaveButtonClicked(ActionEvent e) {
//        if (validate()) {
            saveData();
            try {
                viewController.save();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
//        }
    }

    @FXML
    public void onCancelButtonClickedPayment(ActionEvent e) {
        try {
            viewController.cancel();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    public void billingAddressEqualsCustomerAddressCheckBoxChanged(ActionEvent e) {
        fillBillingAddressData();
    }

    private boolean validate() {
        String textfieldCreditCardNumber = creditCardTextField.getText();
        String textfieldVerfuegerNummer = authorisationNumberTextField.getText();
        String textfieldBillingCity = billingCityTextField.getText();
        String textfieldBillingHouseNumber = billingHouseNumberTextField.getText();
        String textfieldBillingStreet = billingStreetTextField.getText();
        String textfieldBillingPostalCode = billingPostalCodeTextField.getText();
        String textfieldLoc = expiryDateTextField.getText();

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
            creditCardTextField.setStyle("-fx-text-inner-color: red");
            creditCardTextField.setText("Pflichtfeld");
        } else if(!textfieldCreditCardNumber.matches("[0-9]*")) {
            creditCardTextField.setStyle("-fx-text-inner-color: red");
            creditCardTextField.setText("inkorrekte eingabe");
        } else if(textfieldCreditCardNumber.length() < 8 || textfieldCreditCardNumber.length() >19) {
            creditCardTextField.setStyle("-fx-text-inner-color: red");
            creditCardTextField.setText("die Kreditkartennummer hat 8 bis 19 Ziffern");
        }

        if (!textfieldVerfuegerNummer.isEmpty() && textfieldVerfuegerNummer.matches("[0-9]{3}")) {
            verfuegerNummerIsValid = true;
        } else if(textfieldVerfuegerNummer.isEmpty()) {
            authorisationNumberTextField.setStyle("-fx-text-inner-color: red");
            authorisationNumberTextField.setText("Pflichtfeld");
        } else if(!textfieldVerfuegerNummer.matches("[0-9]*")) {
            authorisationNumberTextField.setStyle("-fx-text-inner-color: red");
            authorisationNumberTextField.setText("inkorrekte Eingabe");
        } else if(textfieldVerfuegerNummer.length() < 3 || textfieldVerfuegerNummer.length() > 3) {
            authorisationNumberTextField.setStyle("-fx-text-inner-color: red");
            authorisationNumberTextField.setText("die Sichherheitsnummer hat 3 Ziffern");
        }

        if (!textfieldBillingCity.isEmpty() && textfieldBillingCity.matches("[a-zA-ZäÄöÖüÜß]*")) {
            billingCityIsValid = true;
        } else if(textfieldBillingCity.isEmpty()) {
            billingCityTextField.setStyle("-fx-text-inner-color: red");
            billingCityTextField.setText("Pflichtfeld");
        } else if(!textfieldBillingCity.matches("[a-zA-ZäÄöÖüÜß]*")) {
            billingCityTextField.setStyle("-fx-text-inner-color: red");
            billingCityTextField.setText("inkorrekte Eingabe");
        }

        if (!textfieldBillingHouseNumber.isEmpty() && textfieldBillingHouseNumber.matches("[0-9]*")) {
            billingHouseNumberIsValid = true;
        } else if(textfieldBillingHouseNumber.isEmpty()) {
            billingHouseNumberTextField.setStyle("-fx-text-inner-color: red");
            billingHouseNumberTextField.setText("Pflichtfeld");
        } else if(!textfieldBillingHouseNumber.matches("[0-9]*")) {
            billingHouseNumberTextField.setStyle("-fx-text-inner-color: red");
            billingHouseNumberTextField.setText("inkorrekte Eingabe");
        }

        if (!textfieldBillingStreet.isEmpty() && textfieldBillingStreet.matches("[a-zA-ZäÄöÖüÜß]*")) {
            billingStreetIsValid = true;
        } else if(textfieldBillingStreet.isEmpty()) {
            billingStreetTextField.setStyle("-fx-text-inner-color: red");
            billingStreetTextField.setText("Pflichtfeld");
        } else if(!textfieldBillingStreet.matches("[a-zA-ZäÄöÖüÜß]*")) {
            billingStreetTextField.setStyle("-fx-text-inner-color: red");
            billingStreetTextField.setText("inkorrekte Eingabe");
        }

        if (!textfieldBillingPostalCode.isEmpty() && textfieldBillingPostalCode.matches("[0-9]{1,10}")) {
            billingPostalCodeIsValid = true;
        } else if(textfieldBillingPostalCode.isEmpty()) {
            billingPostalCodeTextField.setStyle("-fx-text-inner-color: red");
            billingPostalCodeTextField.setText("Pflichtfeld");
        } else if(!textfieldBillingPostalCode.matches("[0-9]*")) {
            billingPostalCodeTextField.setStyle("-fx-text-inner-color: red");
            billingPostalCodeTextField.setText("inkorrekte Eingabe");
        } else if(textfieldBillingPostalCode.length() < 1 || textfieldBillingPostalCode.length() > 10) {
            billingPostalCodeTextField.setStyle("-fx-text-inner-color: red");
            billingPostalCodeTextField.setText("die Postleitzahl");
        }

        if (!textfieldLoc.isEmpty() && textfieldLoc.matches("[0-1][1-9]/[0-9][0-9]")) {
            locIsValid = true;
        } else if(textfieldLoc.isEmpty()) {
            expiryDateTextField.setStyle("-fx-text-inner-color: red");
            expiryDateTextField.setText("Pflichtfeld");
        } else if(!textfieldLoc.matches("[0-1][1-9]/[0-9][0-9]")) {
            expiryDateTextField.setStyle("-fx-text-inner-color: red");
            expiryDateTextField.setText("inkorrekte Eingabe");
        }

        if(creditCardNumberIsValid && verfuegerNummerIsValid && billingStreetIsValid &&
                billingHouseNumberIsValid && billingCityIsValid && billingPostalCodeIsValid && verfuegerNummerIsValid) {
            return true;
        }
        return false;
    }
}
