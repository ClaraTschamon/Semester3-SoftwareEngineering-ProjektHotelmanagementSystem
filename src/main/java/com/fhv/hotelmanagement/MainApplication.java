package com.fhv.hotelmanagement;

import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;


public class MainApplication extends Application {
    Stage stage;
    FXMLLoader fxmlLoader;
    Scene scene;


    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        /*
        InputStream input = new FileInputStream("src/main/resources/com/fhv/hotelmanagement/configurations/config.properties");
        //Pfad nicht hartkodiert!!! Mit getResource machen()
        Properties properties = new Properties();
        properties.load(input);
        double width = Double.parseDouble(properties.getProperty("window.width"));
        double height = Double.parseDouble(properties.getProperty("window.height"));

        URL fxmlURL = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\com\\fhv\\hotelmanagement\\FXML\\Walk-In2.fxml").toUri().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("Walk-In");
        stage.setScene(scene);
        stage.show();

         */
        load("Walk-In2");

    }

    public void load(String page) throws IOException {

        InputStream input = new FileInputStream("src/main/resources/com/fhv/hotelmanagement/configurations/config.properties");
        Properties properties = new Properties();
        properties.load(input);
        double width = Double.parseDouble(properties.getProperty("window.width"));
        double height = Double.parseDouble(properties.getProperty("window.height"));

        URL fxmlURL = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\com\\fhv\\hotelmanagement\\fxml\\" + page + ".fxml").toUri().toURL();
        fxmlLoader = new FXMLLoader(fxmlURL);
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Title");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
