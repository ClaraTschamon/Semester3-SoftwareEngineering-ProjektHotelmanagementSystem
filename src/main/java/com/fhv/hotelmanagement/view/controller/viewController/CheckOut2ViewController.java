package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomCategoryDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.controller.useCaseController.CheckOutUseCaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;

public class CheckOut2ViewController implements Initializable {

    @FXML
    public TableView<BookedRoomCategoryDTO> table1;
    @FXML
    public TableColumn<BookedRoomCategoryDTO, Integer> amountColTable1;
    @FXML
    public TableColumn<BookedRoomCategoryDTO, String> categoryColTable1;
    @FXML
    public TableColumn<BookedRoomCategoryDTO, BigDecimal> pricePerNightColTable1;
    @FXML
    public Text nightsText;
    @FXML
    public TextField sumRoomsTextField;
    @FXML
    public CheckBox printInvoiceCheckBox;
    @FXML
    public Text numberText;
    @FXML
    public ComboBox boardComboBox;
    @FXML
    public TextField sumBoardTextField;
    @FXML
    public TextField totalSumNetTextField;
    @FXML
    public Text salesTaxText;
    @FXML
    public Text touristTaxText;
    @FXML
    public Text totalSumGrossText;

    private final BigDecimal SALEXTAX = new BigDecimal("0.2");

    private final BigDecimal TOURISTTAXPERNIGHT = new BigDecimal("1.5");


    private CheckOutUseCaseController useCaseController;
    private CheckOutViewController viewController;

    public CheckOut2ViewController(){
        useCaseController = new CheckOutUseCaseController();
    }

    public void setController(CheckOutViewController viewController){
        this.viewController = viewController;
    }

    @FXML
    public void onBackButtonClicked(ActionEvent actionEvent) throws IOException {
        try{
            //saveData(); //neu
            viewController.loadCheckOut1();
        }catch (IOException exc){
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    public void onConfirmButtonClicked(ActionEvent actionEvent) throws IOException {
        useCaseController.save();
        MainApplication.getMainController().loadIntoContentArea("home");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        calculatePrices();


    }

    private void calculatePrices(){
        BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();
        ObservableList<BookedRoomCategoryDTO> bookedRoomCategoryDTOS = FXCollections.observableArrayList(bookingDTO.getBookedRoomCategories());
        table1.setItems(bookedRoomCategoryDTOS);
        amountColTable1.setCellValueFactory(new PropertyValueFactory<BookedRoomCategoryDTO, Integer>("amount"));
        categoryColTable1.setCellValueFactory(new PropertyValueFactory<BookedRoomCategoryDTO, String>("roomCategory"));
        pricePerNightColTable1.setCellValueFactory(new PropertyValueFactory<BookedRoomCategoryDTO, BigDecimal>("pricePerNight"));

        int nights = (int) DAYS.between(bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate());
        BigDecimal totalRoomPrice = new BigDecimal(0).setScale(2);

        for (BookedRoomCategoryDTO c : bookingDTO.getBookedRoomCategories()) {
            BigDecimal price = c.getPricePerNight().multiply(new BigDecimal(c.getAmount())).multiply(BigDecimal.valueOf(nights));
            totalRoomPrice = totalRoomPrice.add(price);
        }
        nightsText.setText("Nächte: " + nights);
        sumRoomsTextField.setText(totalRoomPrice + "€");


        int amountGuests = bookingDTO.getAmountGuests();
        numberText.setText(String.valueOf(amountGuests));


        boardComboBox.getSelectionModel().select(bookingDTO.getBoard().getName());
        //changelistener auf boardcombobox

        BigDecimal totalBoardPrice = new BigDecimal(0).setScale(2);

        BigDecimal boardPrice = bookingDTO.getPricePerNightForBoard();
        if (boardPrice != null) {
            boardPrice = boardPrice.multiply(new BigDecimal(nights).multiply(new BigDecimal(amountGuests)));
            totalBoardPrice = totalBoardPrice.add(boardPrice);
        }
        sumBoardTextField.setText(totalBoardPrice + "€");

        BigDecimal totalSumNet = new BigDecimal(0);
        totalSumNet = totalSumNet.add(totalRoomPrice);
        totalSumNet = totalSumNet.add(totalBoardPrice);

        totalSumNetTextField.setText(totalSumNet + "€");

        BigDecimal salesTax = totalSumNet.multiply(SALEXTAX);

        salesTaxText.setText("+20% MwSt.:         " + salesTax.setScale(2) + "€");

        BigDecimal touristTax = new BigDecimal(0);
        touristTax = touristTax.add(BigDecimal.valueOf(amountGuests).multiply(TOURISTTAXPERNIGHT));
        touristTax = touristTax.multiply(BigDecimal.valueOf(nights));

        touristTaxText.setText("Ortstaxe:              " + touristTax.setScale(2) + "€");

        BigDecimal totalSumGross = totalSumNet.add(salesTax).add(touristTax);

        totalSumGrossText.setText("Gesamtsumme (Brutto):       " + totalSumGross.setScale(2) + "€");
    }

    public void fillData(){

    }

    protected void saveData(){


    }




}
