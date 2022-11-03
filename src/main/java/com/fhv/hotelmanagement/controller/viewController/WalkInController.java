package com.fhv.hotelmanagement.controller.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class WalkInController {

    @FXML
    private Text chooseRoom;

    @FXML
    private Text departureDate;

    @FXML
    private Text room;

    @FXML
    private Text roomPrice;

    //package fehlt

    @FXML
    private void onCancelButtonClicked(ActionEvent e){
        //zurück zum Home Menü
    }

    @FXML
    private void onNextButtonClicked(ActionEvent e){
        try{
            loadPage("Walk-In2.0");
        }catch(IOException exc){
            System.out.println(exc.getMessage());
        }
    }

    private void loadPage(String page) throws IOException {
        InputStream input = new FileInputStream("src/main/resources/com/fhv/hotelmanagement/configurations/config.properties");
        Properties properties = new Properties();
        properties.load(input);
        double width = Double.parseDouble(properties.getProperty("window.width"));
        double height = Double.parseDouble(properties.getProperty("window.height"));

        URL fxmlURL = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\com\\fhv\\hotelmanagement\\FXML\\Walk-In2.0.fxml").toUri().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        Stage stage = new Stage();
        stage.setTitle("Sunway Hotel");
        stage.setScene(scene);
        stage.show();

    }
}
