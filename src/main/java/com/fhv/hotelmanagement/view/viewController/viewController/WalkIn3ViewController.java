package com.fhv.hotelmanagement.view.viewController.viewController;

import com.fhv.hotelmanagement.view.DTOs.AddressDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class WalkIn3ViewController {
    @FXML
    CheckBox billingAddressEqualsCustomerAddressCheckBox;
    @FXML
    TextField billingStreetTextField;
    @FXML
    TextField billingHouseNumberTextField;
    @FXML
    TextField billingCityTextField;
    @FXML
    TextField billingPostalCodeTextField;
    @FXML
    TextArea notesTextArea;
    WalkInViewController viewController;

    public void setController(WalkInViewController viewController) {
        this.viewController = viewController;
    }

    public void fillData() {
        // TODO
        fillBillingAddressData();
    }

    public void fillBillingAddressData() {
        if (billingAddressEqualsCustomerAddressCheckBox.isSelected()) {
            AddressDTO address = viewController.getUseCaseController().getCustomer().getAddress();
            billingStreetTextField.setText(address.getStreet());
            billingHouseNumberTextField.setText(address.getHouseNumber());
            billingCityTextField.setText(address.getCity());
            billingPostalCodeTextField.setText(address.getPostalCode());
        } else {
            billingStreetTextField.setText("");
            billingHouseNumberTextField.setText("");
            billingCityTextField.setText("");
            billingPostalCodeTextField.setText("");
        }
    }

    @FXML
    public void onBackButtonClickedPayment(ActionEvent e) {
        try {
            viewController.loadWalkIn2();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    public void onSaveButtonClicked(ActionEvent e) {
        // TODO fill all attributes
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
