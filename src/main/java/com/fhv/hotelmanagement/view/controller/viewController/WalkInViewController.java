package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import javafx.fxml.FXMLLoader;
import com.fhv.hotelmanagement.view.controller.useCaseController.*;

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

    public void save() throws IOException {
        useCaseController.save();
        MainApplication.getMainController().loadIntoContentArea("home");
    }
}
