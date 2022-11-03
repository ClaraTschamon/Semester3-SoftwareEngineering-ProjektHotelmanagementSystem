package com.fhv.hotelmanagement;

import com.fhv.hotelmanagement.persistence.PersistenceFacade;
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


    @Override
    public void start(Stage stage) throws IOException {
        InputStream input = new FileInputStream("src/main/resources/com/fhv/hotelmanagement/configurations/config.properties");
        //Pfad nicht hartkodiert!!! Mit getResource machen()
        Properties properties = new Properties();
        properties.load(input);
        double width = Double.parseDouble(properties.getProperty("window.width"));
        double height = Double.parseDouble(properties.getProperty("window.height"));

        URL fxmlURL = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\com\\fhv\\hotelmanagement\\FXML\\Walk-In.fxml").toUri().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("Walk-In");
        stage.setScene(scene);
        //neu
        stage.setMaximized(true);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
