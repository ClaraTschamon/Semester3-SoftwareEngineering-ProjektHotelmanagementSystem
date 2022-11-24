package com.fhv.hotelmanagement;

import com.fhv.hotelmanagement.domain.factory.BoardFactory;
import com.fhv.hotelmanagement.domain.factory.RoomCategoryFactory;
import com.fhv.hotelmanagement.domain.factory.RoomFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


public class MainApplication extends Application {
    static Stage stage;
    static FXMLLoader fxmlLoader;
    Scene scene;
    Properties configProperties;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        loadMainView();
    }

    public static MainController getMainController() throws IOException {
        return fxmlLoader.getController();
    }

    private void loadMainView() throws IOException {
        BoardFactory.getAllBoards();
        RoomFactory.getAllRooms();
        RoomCategoryFactory.getAllRoomCategories();

        double width = Double.parseDouble(getConfigProperties().getProperty("window.width"));
        double height = Double.parseDouble(getConfigProperties().getProperty("window.height"));

        URL mainViewURL = MainApplication.class.getResource("fxml/main-view.fxml");
        fxmlLoader = new FXMLLoader(mainViewURL);
        scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("Sunway Hotel");
        stage.getIcons().add(new Image(MainApplication.class.getResource("fxml/Bilder/Logo4.png").openStream()));
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
