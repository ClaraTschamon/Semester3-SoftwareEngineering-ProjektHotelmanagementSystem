package com.fhv.hotelmanagement;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;


public class MainApplication extends Application {

    protected static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Hotelmanagement");
    @Override
    public void start(Stage stage) throws IOException {
        //InputStream input = new FileInputStream("src/main/resources/teama.hotelmanagement.configurations/config.properties");
        InputStream input = new FileInputStream("src/main/resources/com/fhv/hotelmanagement/configurations/config.properties");
        Properties properties = new Properties();
        properties.load(input);
        double width = Double.parseDouble(properties.getProperty("width"));
        double height = Double.parseDouble(properties.getProperty("height"));

        URL fxmlURL = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\com\\fhv\\hotelmanagement\\FXML\\main-view.fxml").toUri().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("Sunway Hotel");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
