//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.domain.exceptions.BookingIsInvalidException;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomCategoryDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.DTOs.ReservationDTO;
import com.fhv.hotelmanagement.view.viewServices.ApachePDFBoxServices;
import com.fhv.hotelmanagement.view.viewServices.WarningType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

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
    @FXML
    public Text phDepositText;

    private final BigDecimal SALEXTAX = new BigDecimal("0.2");
    private final BigDecimal DEPOSITCUT = new BigDecimal("0.15");
    private final BigDecimal TOURISTTAXPERNIGHT = new BigDecimal("1.5");

    private CheckOutViewController viewController;

    public void setController(CheckOutViewController viewController) {
        this.viewController = viewController;
    }

    @FXML
    private void onBackButtonClicked(ActionEvent actionEvent) {
        try {
            viewController.loadCheckOut1();
        } catch (IOException exc) {
            MainApplication.getMainController().alert("Could not switch page", WarningType.WARNING);
            System.out.println("Error clicking back button: " + exc.getMessage());
        }
    }

    @FXML
    private void onConfirmButtonClicked(ActionEvent actionEvent) {
        try {
            viewController.getUseCaseController().save();
            MainApplication.getMainController().loadIntoContentArea("home");

            if (printInvoiceCheckBox.selectedProperty().get() == true) {
                ApachePDFBoxServices apachePDFBox = new ApachePDFBoxServices();
                apachePDFBox.createBill(viewController);
            }
            MainApplication.getMainController().alert("Successfully checked out", WarningType.CONFIRMATION);

        } catch (BookingIsInvalidException e) {
            System.out.println("Error booking is invalid: " + e.getMessage() + " Booking: " + viewController.getUseCaseController().getBooking());
            MainApplication.getMainController().alert("Can not check out booking.", WarningType.WARNING);
        } catch (IOException e) {
            System.out.println("Error loading home screen: " + e.getMessage());
            MainApplication.getMainController().alert("Something went wrong switching the screen.", WarningType.WARNING);
        }
    }

    @FXML
    private void onCancelButtonClicked(ActionEvent actionEvent) {
        try {
            MainApplication.getMainController().loadIntoContentArea("home");
        } catch (IOException e) {
            MainApplication.getMainController().alert("Could cancel transaction.", WarningType.WARNING);
            System.out.println("Error clicking cancel button: " + e.getMessage());
        }
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

        //deposit and total amount calculation
        ReservationDTO reservation = viewController.getUseCaseController().getBooking().getReservation();
        BigDecimal depositValue = new BigDecimal(0);
        phDepositText.setText(depositValue.setScale(2)+"€");

        BigDecimal totalSumGross = totalSumNet.add(salesTax).add(touristTax);

        if(reservation != null){
            LocalDate creationTimestamp = reservation.getCreationTimestamp().toLocalDate();
            Period period = Period.between(creationTimestamp, reservation.getArrivalDate());
            int daysDiff = Math.abs(period.getDays());

            //Verneinen sobald man es testen kann
            if(!(daysDiff<=3)){
                depositValue = totalSumGross.multiply(DEPOSITCUT);
                phDepositText.setText(depositValue.setScale(2)+"€");
                totalSumGross= totalSumGross.subtract(depositValue);
            }
        }
        phTotalSumGrossText.setText(totalSumGross.setScale(2) + "€");
    }
}
