package com.fhv.hotelmanagement;

import com.fhv.hotelmanagement.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane contentArea;


    public void loadIntoContentArea(String resourceToLoad) throws IOException, URISyntaxException {
        Parent fxml = FXMLLoader.load(MainApplication.class.getResource("fxml/"+ resourceToLoad + ".fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().setAll(fxml);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadIntoContentArea("home");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
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