package com.fhv.hotelmanagement.view.viewController.viewController;

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
        String payment =bookingDTO.getPaymentMethod();
        paymentMethod.setValue(payment);

        AddressDTO addressDTO = viewController.getUseCaseController().getAddressDTO();
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
        billingAddressEqualsCustomerAddressCheckBox.setSelected(addressDTO.isBillingAddressEqualsCustomerAddress());

        if(addressDTO.isBillingAddressEqualsCustomerAddress()==true){
            fillBillingAddressData();
        }
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

        AddressDTO addressDTO = viewController.getUseCaseController().getAddressDTO();
        addressDTO.setStreet(billingStreetTextField.getText());
        addressDTO.setHouseNumber(billingHouseNumberTextField.getText());
        addressDTO.setCity(billingCityTextField.getText());
        addressDTO.setPostalCode(billingPostalCodeTextField.getText());
        addressDTO.setCountry(billingCountryTextField.getText());
        addressDTO.setBillingAddressEqualsCustomerAddress(billingAddressEqualsCustomerAddressCheckBox.isSelected());
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
        saveData();
        try {
            viewController.save();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
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
}
