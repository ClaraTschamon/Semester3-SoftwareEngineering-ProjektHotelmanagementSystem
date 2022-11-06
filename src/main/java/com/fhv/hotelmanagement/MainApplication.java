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
        launch();
    }
}
