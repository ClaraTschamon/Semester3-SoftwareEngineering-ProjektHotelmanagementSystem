package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import javafx.event.EventHandler;
import com.fhv.hotelmanagement.domain.exceptions.BookingIsInvalidException;
import com.fhv.hotelmanagement.domain.exceptions.CustomerIsInvalidException;
import com.fhv.hotelmanagement.view.viewServices.WarningType;
import javafx.fxml.FXMLLoader;
import com.fhv.hotelmanagement.view.controller.useCaseController.*;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class WalkInViewController {
    private WalkInUseCaseController useCaseController;

    public WalkInViewController() {
        try {
            useCaseController = new WalkInUseCaseController();
            loadWalkIn1();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    protected WalkInUseCaseController getUseCaseController() {
        return useCaseController;
    }

    public void loadWalkIn1() throws IOException {
        FXMLLoader walkIn1Loader = MainApplication.getMainController().loadIntoContentArea("walk-in-1");
        com.fhv.hotelmanagement.view.controller.viewController.WalkIn1ViewController walkIn1ViewController = walkIn1Loader.getController();
        walkIn1ViewController.setController(this);
        walkIn1ViewController.fillData();
    }

    public void loadWalkIn2() throws IOException {
        FXMLLoader walkIn2Loader = MainApplication.getMainController().loadIntoContentArea("walk-in-2");
        WalkIn2ViewController walkIn2ViewController = walkIn2Loader.getController();
        walkIn2ViewController.setController(this);
        walkIn2ViewController.fillData();
    }

    public void loadWalkIn3() throws IOException {
        FXMLLoader walkIn3Loader = MainApplication.getMainController().loadIntoContentArea("walk-in-3");
        WalkIn3ViewController walkIn3ViewController = walkIn3Loader.getController();
        walkIn3ViewController.setController(this);
        walkIn3ViewController.fillData();
    }

    public void cancel() throws IOException {
        useCaseController.cancel();
        MainApplication.getMainController().loadIntoContentArea("home");
    }

    public void save() {
        try {
            useCaseController.save();
            MainApplication.getMainController().loadIntoContentArea("home");
            MainApplication.getMainController().alert("Die Buchung wurde erfolgreich gespeichert.",
                    WarningType.CONFIRMATION);
        } catch (BookingIsInvalidException e) {
            System.out.println(e.getMessage());
            MainApplication.getMainController().alert("Ungültige Buchung: Die Buchung konnte nicht gespeichert werden.",
                    WarningType.WARNING);
        } catch (CustomerIsInvalidException e) {
            System.out.println(e.getMessage());
            MainApplication.getMainController().alert("Ungültiger Kunde: Der Kunde konnte nicht gespeichert werden.",
                    WarningType.WARNING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            MainApplication.getMainController().alert("Der Bildschirm konnte nicht gewechselt werden",
                    WarningType.WARNING);
        }
    }

    public void setTextColor(TextField textField, String color) {
        textField.setStyle("-fx-text-inner-color: " + color);
    }

    public void setRequieredField(TextField textField) {
        textField.setPromptText("Pflichtfeld");
        textField.setStyle("-fx-prompt-text-fill: red");
    }

    public void setEventHandler(TextField textField){
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                setTextColor(textField, "black");
            }
        });
    }
}
