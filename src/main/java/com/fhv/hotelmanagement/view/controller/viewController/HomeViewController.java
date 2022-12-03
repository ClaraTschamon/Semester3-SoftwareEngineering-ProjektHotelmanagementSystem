//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomCategoryDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.DTOs.RoomCategoryDTO;
import com.fhv.hotelmanagement.view.DTOs.RoomDTO;
import com.fhv.hotelmanagement.view.viewServices.BookingViewBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class HomeViewController implements Initializable {

    @FXML
    private BarChart<String, Integer> freeRoomsBarChart;
    @FXML
    private TableView<BookingViewBean> checkOutTodayTableView;
    @FXML
    private TableColumn<BookingViewBean, String> nameCol;
    @FXML
    private TableColumn<BookingViewBean, ArrayList<Integer>> roomNrCol;
    @FXML
    private NumberAxis yAxis;
    private static int totalSingleRooms;
    private static int totalDoubleRooms;
    private static int totalFamilyRooms;
    private static int totalSuites;

    @FXML
    private void onWalkInClicked(ActionEvent e) throws IOException {
        WalkInViewController walkInViewControllerController = new WalkInViewController();
    }

    @FXML
    private void onCheckOutClicked(ActionEvent e) throws IOException{
        CheckOutViewController checkOutViewController = new CheckOutViewController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        createBarChart();

        fillCheckOutTodayTable();

    }

    public void createBarChart(){
        ArrayList<RoomDTO> allRooms =  DomainController.getAllRooms();
        //Count all Rooms
        for(RoomDTO roomDTO : allRooms){
            String category = roomDTO.getCategory().getName();
            if(category.equals("Single room")){
                totalSingleRooms ++;
            } else if(category.equals("Double room")){
                totalDoubleRooms ++;
            } else if(category.equals("Family room")){
                totalFamilyRooms ++;
            } else{
                totalSuites ++;
            }
        }

        ArrayList<BookedRoomCategoryDTO> allBookedRoomCategoryDTOs = DomainController.getAllBookedRoomCategoriesWithoutBookings();
        int occupiedSingleRooms = 0;
        int occupiedDoubleRooms = 0;
        int occupiedFamilyRooms = 0;
        int occupiedSuites = 0;

        //count all occupied Rooms
        for(BookedRoomCategoryDTO bookedRoomCategoryDTO : allBookedRoomCategoryDTOs){
            String category = bookedRoomCategoryDTO.getRoomCategory().getName();
            if(category.equals("Single room")){
                occupiedSingleRooms ++;
            } else if(category.equals("Double room")){
                occupiedDoubleRooms ++;
            } else if(category.equals("Family room")){
                occupiedFamilyRooms ++;
            } else{
                occupiedSuites ++;
            }
        }

        XYChart.Series<String, Integer> totalRoomsSeries = new XYChart.Series();
        totalRoomsSeries.setName("total");
        totalRoomsSeries.getData().add(new XYChart.Data<>("Single Rooms", totalSingleRooms));
        totalRoomsSeries.getData().add(new XYChart.Data<>("Double Rooms", totalDoubleRooms));
        totalRoomsSeries.getData().add(new XYChart.Data<>("Family Rooms", totalFamilyRooms));
        totalRoomsSeries.getData().add(new XYChart.Data<>("Suites", totalSuites));

        //berechne freie räume für jede Kategorie (alle räume - belegte räume)
        int freeSingleRooms = totalSingleRooms - occupiedSingleRooms;
        int freeDoubleRooms = totalDoubleRooms - occupiedDoubleRooms;
        int freeFamilyRooms = totalFamilyRooms - occupiedFamilyRooms;
        int freeSuites = totalSuites - occupiedSuites;

        XYChart.Series<String, Integer> freeRoomsSeries = new XYChart.Series();
        freeRoomsSeries.setName("free");
        freeRoomsSeries.getData().add(new XYChart.Data<>("Single Rooms", freeSingleRooms));
        freeRoomsSeries.getData().add(new XYChart.Data<>("Double Rooms", freeDoubleRooms));
        freeRoomsSeries.getData().add(new XYChart.Data<>("Family Rooms", freeFamilyRooms));
        freeRoomsSeries.getData().add(new XYChart.Data<>("Suites", freeSuites));


        freeRoomsBarChart.getData().addAll(totalRoomsSeries, freeRoomsSeries);
        //yAxis.setUpperBound(11);
    }

    public void fillCheckOutTodayTable(){
        ArrayList<BookingDTO> allBookingDTOs = DomainController.getCurrentBookings(); //reicht das oder muss ich aus datenbank abfragen welche bookings heute ausgechecked werden?
        ArrayList<BookingViewBean> checkOutTodayBeans = new ArrayList<>();

        for(BookingDTO bookingDTO : allBookingDTOs){
            if(bookingDTO.getDepartureDate().equals(LocalDate.now())){
                BookingViewBean booking = new BookingViewBean(bookingDTO);
                checkOutTodayBeans.add(booking);
            }
        }

        ObservableList<BookingViewBean> checkOutTodayBookings = FXCollections.observableArrayList(checkOutTodayBeans);

        nameCol.setCellValueFactory(new PropertyValueFactory<BookingViewBean, String>("lastName"));
        roomNrCol.setCellValueFactory(new PropertyValueFactory<BookingViewBean, ArrayList<Integer>>("roomNumbers"));

        if(checkOutTodayBookings.size() == 0){
            checkOutTodayTableView.setPlaceholder(new Label("No check-outs today"));
            checkOutTodayTableView.getItems().clear();
        }else{
            checkOutTodayTableView.setItems(checkOutTodayBookings);
        }
    }

}