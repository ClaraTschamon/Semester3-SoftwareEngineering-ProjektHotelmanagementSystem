package com.fhv.hotelmanagement;

import com.fhv.hotelmanagement.view.controller.viewController.WalkIn1ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


public class MainApplication extends Application {
    Stage stage;
    static FXMLLoader fxmlLoader;
    Scene scene;
    Properties configProperties;
    MainController mainController;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.mainController = new MainController();
        loadMainView();
    }

    public static MainController getMainController() throws IOException {
        return fxmlLoader.getController();
    }

    private void loadMainView() throws IOException {
        double width = Double.parseDouble(getConfigProperties().getProperty("window.width"));
        double height = Double.parseDouble(getConfigProperties().getProperty("window.height"));

        URL mainViewURL = MainApplication.class.getResource("fxml/main-view.fxml");
        fxmlLoader = new FXMLLoader(mainViewURL);
        WalkIn1ViewController w = new WalkIn1ViewController(); // debug
        w.initialize(null, null);
        scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("Sunway Hotel");
        stage.setScene(scene);
        stage.show();
    }

    private Properties getConfigProperties() throws IOException {
        if (configProperties == null) {
            InputStream propertiesInput = MainApplication.class.getResourceAsStream("configurations/config.properties");
            configProperties = new Properties();
            configProperties.load(propertiesInput);
        }
        return configProperties;
    }

    public static void main(String[] args) {

        //ID Tester
//        int count = 0;
//
//        while (count<10){
//            Customer customer = new Customer();
//            System.out.println(customer.getId());
//
//            Booking booking = new Booking();
//            System.out.println(booking.getId());
//        }

        launch();
    }
}
