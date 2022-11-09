package com.fhv.hotelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane contentArea;
    private FXMLLoader currentFXMLLoader;


    public FXMLLoader loadIntoContentArea(String resourceToLoad) throws IOException {
        currentFXMLLoader = new FXMLLoader(MainApplication.class.getResource("fxml/"+ resourceToLoad + ".fxml"));
        Parent fxml = currentFXMLLoader.load();
        contentArea.getChildren().clear();
        contentArea.getChildren().setAll(fxml);
        return currentFXMLLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadIntoContentArea("home");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FXMLLoader getCurrentFXMLLoader() {
        return currentFXMLLoader;
    }

    /*
    @FXML
    private void home(MouseEvent event) {
        loadPage("home");
    }

    @FXML
    private void bookings(MouseEvent event) {
        loadPage("bookings");
    }

     */

    /*
    public void loadPage(String page) {
        Parent selectedPage = null;
        try {
            System.out.println("---"+ page + ": " + FXMLLoader.load(MainApplication.class.getResource("fxml/" + page + ".fxml")));
            selectedPage = FXMLLoader.load(MainApplication.class.getResource("fxml/" + page + ".fxml"));
            bp.setCenter(selectedPage);
        } catch (IOException e) {
            System.out.println("Error loading page: " + e);
        }
    }

     */
}