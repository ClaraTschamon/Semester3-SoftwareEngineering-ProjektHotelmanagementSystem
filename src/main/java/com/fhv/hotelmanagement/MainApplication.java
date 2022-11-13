package com.fhv.hotelmanagement;

import com.fhv.hotelmanagement.domain.domainController.DomainManager;
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
    static DomainManager domainManager;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        loadMainView();
    }

    public static MainController getMainController() throws IOException {
        return fxmlLoader.getController();
    }

    public static DomainManager getDomainManager() {
        return domainManager;
    }

    private void loadMainView() throws IOException {
        domainManager = new DomainManager();

        double width = Double.parseDouble(getConfigProperties().getProperty("window.width"));
        double height = Double.parseDouble(getConfigProperties().getProperty("window.height"));

        URL mainViewURL = MainApplication.class.getResource("fxml/main-view.fxml");
        fxmlLoader = new FXMLLoader(mainViewURL);
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
