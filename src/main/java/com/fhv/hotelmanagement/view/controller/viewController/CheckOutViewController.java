//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.view.controller.useCaseController.CheckOutUseCaseController;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class CheckOutViewController {
    private CheckOutUseCaseController useCaseController;

    private boolean isFromBookingOverview;

    public CheckOutViewController() throws IOException {
        useCaseController = new CheckOutUseCaseController();
        isFromBookingOverview = false;
        loadCheckOut1();
    }

    public CheckOutUseCaseController getUseCaseController(){
        return useCaseController;
    }

    public void loadCheckOut1() throws IOException{
        FXMLLoader checkOut1Loader = MainApplication.getMainController().loadIntoContentArea("check-out-1");
        CheckOut1ViewController checkOut1ViewController = checkOut1Loader.getController();
        checkOut1ViewController.setController(this);
        checkOut1ViewController.fillData();
    }

    public void loadCheckOut2() throws IOException{
        FXMLLoader checkOut2Loader = MainApplication.getMainController().loadIntoContentArea("check-out-2");
        CheckOut2ViewController checkOut2ViewController = checkOut2Loader.getController();
        checkOut2ViewController.setController(this);
        checkOut2ViewController.fillData();
    }

    public boolean getIsFromBookingOverview() {
        return isFromBookingOverview;
    }

    public void setIsFromBookingOverview(boolean isFromBookingOverview){
        this.isFromBookingOverview = isFromBookingOverview;
    }
}
