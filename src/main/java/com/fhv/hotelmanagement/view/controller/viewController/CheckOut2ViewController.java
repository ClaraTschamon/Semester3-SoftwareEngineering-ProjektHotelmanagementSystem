//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomCategoryDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.viewServices.ApachePDFBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.math.BigDecimal;

import static java.time.temporal.ChronoUnit.DAYS;

public class CheckOut2ViewController {

    @FXML
    private TableView<BookedRoomCategoryDTO> table1;
    @FXML
    private TableColumn<BookedRoomCategoryDTO, Integer> amountColTable1;
    @FXML
    private TableColumn<BookedRoomCategoryDTO, String> categoryColTable1;
    @FXML
    private TableColumn<BookedRoomCategoryDTO, BigDecimal> pricePerNightColTable1;
    @FXML
    private Text nightsText;
    @FXML
    private CheckBox printInvoiceCheckBox;
    @FXML
    private Text boardNumberText;
    @FXML
    private Text boardNameText;
    @FXML
    private Text sumRoomsText;
    @FXML
    private Text totalSumNetText;
    @FXML
    private Text sumBoardsText;
    @FXML
    private Text salesTaxText;
    @FXML
    private Text touristTaxText;
    @FXML
    private Text totalSumGrossText;

    private final BigDecimal SALEXTAX = new BigDecimal("0.2");

    private final BigDecimal TOURISTTAXPERNIGHT = new BigDecimal("1.5");

    private CheckOutViewController viewController;

    public void setController(CheckOutViewController viewController) {
        this.viewController = viewController;
    }

    @FXML
    private void onBackButtonClicked(ActionEvent actionEvent) throws IOException {
        try {
            viewController.loadCheckOut1();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void onConfirmButtonClicked(ActionEvent actionEvent) throws IOException {
        viewController.getUseCaseController().save();
        MainApplication.getMainController().loadIntoContentArea("home");

        if(printInvoiceCheckBox.selectedProperty().get()==true){
            ApachePDFBox apachePDFBox = new ApachePDFBox();
            apachePDFBox.createBill(viewController);
        }
    }

    protected void fillData() {
        fillBillData();
    }

    private void fillBillData() {
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
        sumRoomsText.setText("Summe für Zimmer:          " + totalRoomPrice + "€");

        int amountGuests = bookingDTO.getAmountGuests();
        boardNumberText.setText(String.valueOf(amountGuests));

        boardNameText.setText(bookingDTO.getBoard().getName());

        BigDecimal totalBoardPrice = new BigDecimal(0).setScale(2);

        BigDecimal boardPrice = bookingDTO.getPricePerNightForBoard();
        if (boardPrice != null) {
            boardPrice = boardPrice.multiply(new BigDecimal(nights).multiply(new BigDecimal(amountGuests)));
            totalBoardPrice = totalBoardPrice.add(boardPrice);
        }

        sumBoardsText.setText("Summe für Package:         " + totalBoardPrice + "€");

        BigDecimal totalSumNet = new BigDecimal(0);
        totalSumNet = totalSumNet.add(totalRoomPrice);
        totalSumNet = totalSumNet.add(totalBoardPrice);

        totalSumNetText.setText("Gesamtsumme (Netto):         " + totalSumNet + "€");

        BigDecimal salesTax = totalSumNet.multiply(SALEXTAX);

        salesTaxText.setText("+20% MwSt.:         " + salesTax.setScale(2) + "€");

        BigDecimal touristTax = new BigDecimal(0);
        touristTax = touristTax.add(BigDecimal.valueOf(amountGuests).multiply(TOURISTTAXPERNIGHT));
        touristTax = touristTax.multiply(BigDecimal.valueOf(nights));

        touristTaxText.setText("Ortstaxe*:           " + touristTax.setScale(2) + "€");

        BigDecimal totalSumGross = totalSumNet.add(salesTax).add(touristTax);

        totalSumGrossText.setText("Gesamtsumme (Brutto):       " + totalSumGross.setScale(2) + "€");
    }
}
