//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.domain.exceptions.BookingIsInvalidException;
import com.fhv.hotelmanagement.domain.exceptions.CustomerIsInvalidException;
import com.fhv.hotelmanagement.view.viewServices.WarningType;
import javafx.fxml.FXMLLoader;
import com.fhv.hotelmanagement.view.controller.useCaseController.*;

import java.io.IOException;
import java.util.Arrays;

public class CheckInViewController {
    private CheckInUseCaseController useCaseController;
    private boolean searching;
    private boolean isCheckIn; //also anhand von reservierung mit Reservierung

    public CheckInViewController() throws IOException {
        useCaseController = new CheckInUseCaseController();
        searching = false;
        isCheckIn = false;
        loadCheckIn1();
    }

    public CheckInViewController(boolean isCheckIn) throws IOException {
        if(isCheckIn) {
            this.isCheckIn = true;
        }
        useCaseController = new CheckInUseCaseController();
        searching = false;
        loadCheckIn1();
    }

    protected CheckInUseCaseController getUseCaseController() {
        return useCaseController;
    }

    public void loadCheckIn1() throws IOException {
        searching = false;
        FXMLLoader checkIn1Loader = MainApplication.getMainController().loadIntoContentArea("check-in-1");
        com.fhv.hotelmanagement.view.controller.viewController.CheckIn1ViewController checkIn1ViewController = checkIn1Loader.getController();
        checkIn1ViewController.setController(this);
        checkIn1ViewController.fillData();
    }

    public void loadCheckIn2() throws IOException {
        searching = false;
        FXMLLoader checkIn2Loader = MainApplication.getMainController().loadIntoContentArea("check-in-2");
        CheckIn2ViewController checkIn2ViewController = checkIn2Loader.getController();
        checkIn2ViewController.setController(this);
        checkIn2ViewController.fillData();
    }

    public void loadCheckIn3() throws IOException {
        searching = false;
        FXMLLoader checkIn3Loader = MainApplication.getMainController().loadIntoContentArea("check-in-3");
        CheckIn3ViewController checkIn3ViewController = checkIn3Loader.getController();
        checkIn3ViewController.setController(this);
        checkIn3ViewController.fillData();
    }

    public void cancel() throws IOException {
        useCaseController.cancel();
        MainApplication.getMainController().loadIntoContentArea("home");
    }

    public void save() {
        try {
            useCaseController.save();
            MainApplication.getMainController().loadIntoContentArea("booking-overview");
            MainApplication.getMainController().alert("The booking was saved successfully.",
                    WarningType.CONFIRMATION);
        } catch (BookingIsInvalidException e) {
            System.out.println(e.getMessage());
            MainApplication.getMainController().alert("Invalid booking: The booking could not be saved.",
                    WarningType.WARNING);
        } catch (CustomerIsInvalidException e) {
            System.out.println(e.getMessage());
            MainApplication.getMainController().alert("Invalid customer: The customer could not be saved.",
                    WarningType.WARNING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            MainApplication.getMainController().alert("The screen could not be changed.",
                    WarningType.WARNING);
        }
    }

    public boolean getIsCheckIn() {
        return isCheckIn;
    }

    public void setIsCheckIn(boolean checkIn) {
        isCheckIn = checkIn;
    }
}
