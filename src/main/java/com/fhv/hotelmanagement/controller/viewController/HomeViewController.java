package com.fhv.hotelmanagement.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;


public class HomeViewController {

    @FXML
    private void onWalkInClicked(ActionEvent e){
        try{
            MainApplication.getMainController().loadIntoContentArea("walk-in-1");
        }catch(IOException | URISyntaxException exc){
            System.out.println(exc.getMessage());
        }
    }

    private void loadPage(String page) throws IOException {
        InputStream input = new FileInputStream("src/main/resources/com/fhv/hotelmanagement/configurations/config.properties");
        Properties properties = new Properties();
        properties.load(input);
        double width = Double.parseDouble(properties.getProperty("window.width"));
        double height = Double.parseDouble(properties.getProperty("window.height"));

        URL fxmlURL = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\com\\fhv\\hotelmanagement\\FXML\\walk-in-1.fxml").toUri().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        Stage stage = new Stage();
        stage.setTitle("Sunway Hotel");
        stage.setScene(scene);
        stage.show();
    }
}