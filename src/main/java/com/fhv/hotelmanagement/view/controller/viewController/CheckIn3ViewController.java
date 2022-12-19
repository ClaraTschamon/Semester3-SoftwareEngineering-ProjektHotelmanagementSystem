//Hotelmanagementsystem TeamA 2022/23
/**Created by
 * Date:
 * Time:
 * Project Name:
 */

package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.services.StringValidator;
import com.fhv.hotelmanagement.view.viewServices.TextFunction;
import com.fhv.hotelmanagement.view.DTOs.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class CheckIn3ViewController {
    CheckInViewController viewController;

    @FXML
    private TextField billingCountryTextField;
    @FXML
    private TextField creditCardTextField;
    @FXML
    private TextField authorisationNumberTextField;
    @FXML
    private TextField expireDateTextField;
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
    @FXML
    private ComboBox paymentMethodComboBox;
    @FXML
    private Label checkInForLabel;
    @FXML
    private Label roomNumbersLabel;


    /*if you go back after you clicked the "billingAddressEqualsCustomerAddressButton" it will delete the filled in customer bill address
    -->This prevents false information to be saved (if the customer chooses to replace his address after going back to walk-In2*/
    private boolean billingAddressEqualsCustomerAddressBackButtonClick;

    public void setController(CheckInViewController viewController) {
        this.viewController = viewController;
    }

    protected void fillData() {
        BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();
        String creditCard = bookingDTO.getCreditCardNumber();
        creditCardTextField.setText(creditCard);
        String authorisationNumber = bookingDTO.getAuthorisationNumber();
        authorisationNumberTextField.setText(authorisationNumber);
        String expiryDate = bookingDTO.getExpirationDate();
        expireDateTextField.setText(expiryDate);
        String comment = bookingDTO.getComment(); //Comment = Notes
        notesTextArea.setText(comment);

        if (bookingDTO.getPaymentMethod() != null) {
            String payment = bookingDTO.getPaymentMethod();
            paymentMethodComboBox.setValue(payment);
        }

        AddressDTO addressDTO = viewController.getUseCaseController().getBooking().getBillingAddress();
        String billingStreet = addressDTO.getStreet();
        billingStreetTextField.setText(billingStreet);
        String billingHouseNumber = addressDTO.getHouseNumber();
        billingHouseNumberTextField.setText(billingHouseNumber);
        String billingCity = addressDTO.getCity();
        billingCityTextField.setText(billingCity);
        String billingPostalCode = addressDTO.getPostalCode();
        billingPostalCodeTextField.setText(billingPostalCode);
        String billingCountry = addressDTO.getCountry();
        billingCountryTextField.setText(billingCountry);

        billingAddressEqualsCustomerAddressCheckBox.setSelected(viewController.getUseCaseController().isBillingAddressEqualsCustomerAddress());
        fillBillingAddressData();
        fillSummaryLabels();
    }

    private void fillBillingAddressData() {
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

    public void fillSummaryLabels() {
        BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();
        CustomerDTO customerDTO = viewController.getUseCaseController().getCustomer();
        checkInForLabel.setText("Check-in for: " + customerDTO.getFirstName() + " " + customerDTO.getLastName());

        StringBuilder sb = new StringBuilder("Room number(s): ");
        for (BookedRoomDTO bookedRoomDTO : bookingDTO.getBookedRooms()) {
            sb.append(bookedRoomDTO.getRoom().getNumber());
            sb.append(" ");
        }
        roomNumbersLabel.setText(sb.toString());

    }

    protected void saveData() {
        BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();
        String paymentMethod = (String) paymentMethodComboBox.getValue();
        bookingDTO.setPaymentMethod(paymentMethod);
        if (paymentMethod.equals("Credit card")) {
            bookingDTO.setCreditCardNumber(creditCardTextField.getText());
            bookingDTO.setAuthorisationNumber(authorisationNumberTextField.getText());
            bookingDTO.setExpirationDate(expireDateTextField.getText());
        } else {
            bookingDTO.setCreditCardNumber(null);
            bookingDTO.setAuthorisationNumber(null);
            bookingDTO.setExpirationDate(null);
        }

        bookingDTO.setComment(notesTextArea.getText());

        AddressDTO billingAddressDTO = viewController.getUseCaseController().getBooking().getBillingAddress();
        billingAddressDTO.setStreet(billingStreetTextField.getText());
        billingAddressDTO.setHouseNumber(billingHouseNumberTextField.getText());
        billingAddressDTO.setCity(billingCityTextField.getText());
        billingAddressDTO.setPostalCode(billingPostalCodeTextField.getText());
        billingAddressDTO.setCountry(billingCountryTextField.getText());
        viewController.getUseCaseController().setBillingAddressEqualsCustomerAddress(billingAddressEqualsCustomerAddressCheckBox.isSelected());
    }

    @FXML
    private void onBackButtonClickedPayment(ActionEvent e) {
        try {
            saveData();
            viewController.loadCheckIn2();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void onSaveButtonClicked(ActionEvent e) {
        if (validate()) {
            saveData();
            viewController.save();
        }
    }

    @FXML
    private void onCancelButtonClickedPayment(ActionEvent e) {
        try {
            viewController.cancel();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void billingAddressEqualsCustomerAddressCheckBoxChanged(ActionEvent e) {
        fillBillingAddressData();
    }

    private boolean validate() {

        boolean creditCardNumberIsValid = false;
        boolean authorisationNumberIsValid = false;
        boolean billingCityIsValid = false;
        boolean billingHouseNumberIsValid = false;
        boolean billingStreetIsValid = false;
        boolean billingPostalCodeIsValid = false;
        boolean expireDateIsValid = false;

        if (paymentMethodComboBox.getValue().equals("Credit card")) {
            if (!StringValidator.checkString(creditCardTextField.getText())) {
                TextFunction.setRequieredTextField(creditCardTextField);
            } else if (StringValidator.checkRegex(creditCardTextField.getText(), "[0-9]{4}[ ][0-9]{4}[ ][0-9]{4}[ ][0-9]{4}[ ]{0,}") ||
                    StringValidator.checkRegex(creditCardTextField.getText(), "[0-9]{14,16}[ ]{0,}") ||
                    StringValidator.checkRegex(creditCardTextField.getText(), "[0-9]{4}[ ][0-9]{6}[ ][0-9]{4}[ ]{0,}") ||
                    StringValidator.checkRegex(creditCardTextField.getText(), "[0-9]{4}[ ][0-9]{6}[ ][0-9]{5}[ ]{0,}")) {
                creditCardNumberIsValid = true;
            } else {
                TextFunction.setTextFieldColor(creditCardTextField, "red");
                TextFunction.setEventHandler(creditCardTextField);
            }

            if (!StringValidator.checkString(authorisationNumberTextField.getText())) {
                TextFunction.setRequieredTextField(authorisationNumberTextField);
            } else if (StringValidator.checkRegex(authorisationNumberTextField.getText(), "[0-9 ]{3,5}")) {
                authorisationNumberIsValid = true;
            } else {
                TextFunction.setTextFieldColor(authorisationNumberTextField, "red");
                TextFunction.setEventHandler(authorisationNumberTextField);
            }

            if (!StringValidator.checkString(expireDateTextField.getText())) {
                TextFunction.setRequieredTextField(expireDateTextField);
            } else if (StringValidator.checkValidExpirationDate(expireDateTextField.getText())) {
                expireDateIsValid = true;
            } else {
                TextFunction.setTextFieldColor(expireDateTextField, "red");
                TextFunction.setEventHandler(expireDateTextField);
            }
        } else {
            creditCardNumberIsValid = true;
            authorisationNumberIsValid = true;
            expireDateIsValid = true;
        }

        if (!StringValidator.checkString(billingCityTextField.getText())) {
            TextFunction.setRequieredTextField(billingCityTextField);
        } else if (StringValidator.checkCity(billingCityTextField.getText())) {
            billingCityIsValid = true;
        } else {
            TextFunction.setTextFieldColor(billingCityTextField, "red");
            TextFunction.setEventHandler(billingCityTextField);
        }

        if (!StringValidator.checkString(billingHouseNumberTextField.getText())) {
            TextFunction.setRequieredTextField(billingHouseNumberTextField);
        } else if (StringValidator.checkHouseNumber(billingHouseNumberTextField.getText())) {
            billingHouseNumberIsValid = true;
        } else {
            TextFunction.setTextFieldColor(billingHouseNumberTextField, "red");
            TextFunction.setEventHandler(billingHouseNumberTextField);
        }

        if (!StringValidator.checkString(billingStreetTextField.getText())) {
            TextFunction.setRequieredTextField(billingStreetTextField);
        } else if (StringValidator.checkStreet(billingStreetTextField.getText())) {
            billingStreetIsValid = true;
        } else {
            TextFunction.setTextFieldColor(billingStreetTextField, "red");
            TextFunction.setEventHandler(billingStreetTextField);
        }

        if (!StringValidator.checkString(billingPostalCodeTextField.getText())) {
            TextFunction.setRequieredTextField(billingPostalCodeTextField);
        } else if (StringValidator.checkPostalCode(billingPostalCodeTextField.getText())) {
            billingPostalCodeIsValid = true;
        } else {
            TextFunction.setTextFieldColor(billingPostalCodeTextField, "red");
            TextFunction.setEventHandler(billingPostalCodeTextField);
        }

        return (creditCardNumberIsValid && authorisationNumberIsValid && expireDateIsValid &&
                    billingStreetIsValid && billingHouseNumberIsValid && billingCityIsValid && billingPostalCodeIsValid);
    }

    @FXML
    private void paymentMethodComboBoxOnAction() {
        if (paymentMethodComboBox.getValue().equals("Credit card")) {
            creditCardTextField.setDisable(false);
            authorisationNumberTextField.setDisable(false);
            expireDateTextField.setDisable(false);
        } else {
            creditCardTextField.setDisable(true);
            authorisationNumberTextField.setDisable(true);
            expireDateTextField.setDisable(true);
        }
    }
}