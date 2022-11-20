package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.services.StringValidator;
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
        if (validate()) {
            saveData();
            try {
                viewController.save();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
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

        boolean creditCardNumberIsValid = false;
        boolean verfuegerNummerIsValid = false;
        boolean billingCityIsValid = false;
        boolean billingHouseNumberIsValid = false;
        boolean billingStreetIsValid = false;
        boolean billingPostalCodeIsValid = false;
        boolean expireDateIsValid = false;

        if (StringValidator.checkString(creditCardTextField.getText())) {
            if (StringValidator.checkRegex(creditCardTextField.getText(), "[0-9 ]{8,19}")) {
                creditCardNumberIsValid = true;
                setTextColor(creditCardTextField, "black");
            } else {
                setTextColor(creditCardTextField, "red");
            }
        }
        else {
            setRequieredField(creditCardTextField);
        }

        if (StringValidator.checkString(authorisationNumberTextField.getText())) {
            if (StringValidator.checkRegex(authorisationNumberTextField.getText(), "[0-9]{3}")) {
                verfuegerNummerIsValid = true;
                setTextColor(authorisationNumberTextField, "black");
            } else {
                setTextColor(authorisationNumberTextField, "red");
            }
        }
        else {
            setRequieredField(authorisationNumberTextField);
        }

        if (StringValidator.checkString(billingCityTextField.getText())) {
            if (StringValidator.checkRegex(billingCityTextField.getText(), "[a-zA-Z]*")) {
                billingCityIsValid = true;
                setTextColor(billingCityTextField, "black");
            } else {
                setTextColor(billingCityTextField, "red");
            }
        }
        else {
            setRequieredField(billingCityTextField);
        }

        if (StringValidator.checkString(billingHouseNumberTextField.getText())) {
            if (StringValidator.checkRegex(billingHouseNumberTextField.getText(), "[0-9]*")) {
                billingHouseNumberIsValid = true;
                setTextColor(billingHouseNumberTextField, "black");
            } else {
                setTextColor(billingHouseNumberTextField, "red");
            }
        }
        else {
            setRequieredField(billingHouseNumberTextField);
        }

        if (StringValidator.checkString(billingStreetTextField.getText())) {
            if (StringValidator.checkRegex(billingStreetTextField.getText(), "[a-zA-Z]*")) {
                billingStreetIsValid = true;
                setTextColor(billingStreetTextField, "black");
            } else {
                setTextColor(billingStreetTextField, "red");
            }
        }
        else {
            setRequieredField(billingStreetTextField);
        }

        if (StringValidator.checkString(billingPostalCodeTextField.getText())) {
            if (StringValidator.checkRegex(billingPostalCodeTextField.getText(), "[0-9]*")) {
                billingPostalCodeIsValid = true;
                setTextColor(billingPostalCodeTextField, "black");
            } else {
                setTextColor(billingPostalCodeTextField, "red");
            }
        }
        else {
            setRequieredField(billingPostalCodeTextField);
        }

        if (StringValidator.checkString(expiryDateTextField.getText())) {
            if (StringValidator.checkValidExpirationDate(expiryDateTextField.getText())) {
                expireDateIsValid = true;
                setTextColor(expiryDateTextField, "black");
            } else {
                setTextColor(expiryDateTextField, "red");
            }
        }
        else {
            setRequieredField(expiryDateTextField);
        }

        if(creditCardNumberIsValid && verfuegerNummerIsValid && billingStreetIsValid &&
                billingHouseNumberIsValid && billingCityIsValid && billingPostalCodeIsValid &&
                verfuegerNummerIsValid && expireDateIsValid) {
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
}
