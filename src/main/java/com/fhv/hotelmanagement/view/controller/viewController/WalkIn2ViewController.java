package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.view.DTOs.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;

public class WalkIn2ViewController {
    //TODO: Tabulator automatisch auf Vorname setzen

    private static int number;

    @FXML
    private DatePicker birthday;
    @FXML
    private TextField countryTextField;
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
    protected void saveData() throws IOException{
        CustomerDTO customer = viewController.getUseCaseController().getCustomer();
        customer.setFirstName(firstName.getText());
        customer.setLastName(lastName.getText());
        customer.setNationality(nationality.getText());
        customer.setPhoneNumber(phoneNumber.getText());
        customer.setEmail(email.getText());
        customer.getAddress().setStreet(street.getText());
        customer.getAddress().setCity(city.getText());
        customer.getAddress().setHouseNumber(houseNumber.getText());
        customer.getAddress().setPostalCode(postalCode.getText());
        customer.getAddress().setCountry(countryTextField.getText());
        customer.setDateOfBirth(birthday.getValue());
        viewController.loadWalkIn3();
    }

    protected void fillData() throws IOException {
        CustomerDTO customer = viewController.getUseCaseController().getCustomer();
        String firstname1= customer.getFirstName();
        firstName.setText(firstname1);
        String lastname1=customer.getLastName();
        lastName.setText(lastname1);
        String nationality1= customer.getNationality();
        nationality.setText(nationality1);
        String phonenumber1 =customer.getPhoneNumber();
        phoneNumber.setText(phonenumber1);
        String email1 =customer.getEmail();
        email.setText(email1);
        String city1=customer.getAddress().getCity();
        city.setText(city1);
        String street1=customer.getAddress().getStreet();
        street.setText(street1);
        String housenumber1 =customer.getAddress().getHouseNumber();
        houseNumber.setText(housenumber1);
        String postalcode1=customer.getAddress().getPostalCode();
        postalCode.setText(postalcode1);
        String country1 = customer.getAddress().getCountry();
        countryTextField.setText(country1);
        LocalDate birthday1 = customer.getDateOfBirth();
        birthday.setValue(birthday1);
    }

    @FXML
    private void onBackButtonClicked(ActionEvent e) throws IOException {
        try {
            saveData();
            viewController.loadWalkIn1();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void onNextButtonClicked(ActionEvent e) throws IOException {
        try{
            saveData();
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
