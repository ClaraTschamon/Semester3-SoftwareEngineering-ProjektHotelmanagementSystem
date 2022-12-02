//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomCategoryDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
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
    private TableView<BookedRoomCategoryDTO> bookedRoomsTable;
    @FXML
    private TableColumn<BookedRoomCategoryDTO, Integer> amountColTable;
    @FXML
    private TableColumn<BookedRoomCategoryDTO, String> categoryColTable;
    @FXML
    private TableColumn<BookedRoomCategoryDTO, BigDecimal> pricePerNightColTable;
    @FXML
    public Text phNightsText;
    @FXML
    public Text phBoardAmountText;
    @FXML
    private CheckBox printInvoiceCheckBox;
    @FXML
    private Text boardNameText;
    @FXML
    public Text phSumRoomsText;
    @FXML
    public Text phSumBoardsText;
    @FXML
    public Text phTotalSumNetText;
    @FXML
    public Text phSalesTaxText;
    @FXML
    public Text phTouristTaxText;
    @FXML
    public Text phTotalSumGrossText;

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
    }

    protected void fillData() {
        fillBillData();
    }

    private void fillBillData() {
        BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();
        ObservableList<BookedRoomCategoryDTO> bookedRoomCategoryDTOS = FXCollections.observableArrayList(bookingDTO.getBookedRoomCategories());
        bookedRoomsTable.setItems(bookedRoomCategoryDTOS);
        amountColTable.setCellValueFactory(new PropertyValueFactory<BookedRoomCategoryDTO, Integer>("amount"));
        categoryColTable.setCellValueFactory(new PropertyValueFactory<BookedRoomCategoryDTO, String>("roomCategory"));
        pricePerNightColTable.setCellValueFactory(new PropertyValueFactory<BookedRoomCategoryDTO, BigDecimal>("pricePerNight"));

        int nights = (int) DAYS.between(bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate());
        BigDecimal totalRoomPrice = new BigDecimal(0).setScale(2);

        for (BookedRoomCategoryDTO c : bookingDTO.getBookedRoomCategories()) {
            BigDecimal price = c.getPricePerNight().multiply(new BigDecimal(c.getAmount())).multiply(BigDecimal.valueOf(nights));
            totalRoomPrice = totalRoomPrice.add(price);
        }
        phNightsText.setText(String.valueOf(nights));
        phSumRoomsText.setText(totalRoomPrice + "€");


        int amountGuests = bookingDTO.getAmountGuests();
        phBoardAmountText.setText(String.valueOf(amountGuests));

        boardNameText.setText(bookingDTO.getBoard().getName());

        BigDecimal totalBoardPrice = new BigDecimal(0).setScale(2);

        BigDecimal boardPrice = bookingDTO.getPricePerNightForBoard();
        if (boardPrice != null) {
            boardPrice = boardPrice.multiply(new BigDecimal(nights).multiply(new BigDecimal(amountGuests)));
            totalBoardPrice = totalBoardPrice.add(boardPrice);
        }

        phSumBoardsText.setText(totalBoardPrice + "€");

        BigDecimal totalSumNet = new BigDecimal(0);
        totalSumNet = totalSumNet.add(totalRoomPrice);
        totalSumNet = totalSumNet.add(totalBoardPrice);

        phTotalSumNetText.setText(totalSumNet + "€");

        BigDecimal salesTax = totalSumNet.multiply(SALEXTAX);

        phSalesTaxText.setText(salesTax.setScale(2) + "€");

        BigDecimal touristTax = new BigDecimal(0);
        touristTax = touristTax.add(BigDecimal.valueOf(amountGuests).multiply(TOURISTTAXPERNIGHT));
        touristTax = touristTax.multiply(BigDecimal.valueOf(nights));

        phTouristTaxText.setText(touristTax.setScale(2) + "€");

        BigDecimal totalSumGross = totalSumNet.add(salesTax).add(touristTax);

        phTotalSumGrossText.setText(totalSumGross.setScale(2) + "€");
    }
}
