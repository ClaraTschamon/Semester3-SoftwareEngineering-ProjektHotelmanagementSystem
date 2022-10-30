package com.fhv.hotelmanagement;

import com.fhv.hotelmanagement.JPAPersistence.DBFacade;
import com.fhv.hotelmanagement.DBModel.RoomCategory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;


public class MainApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        InputStream input = new FileInputStream("src/main/resources/com/fhv/hotelmanagement/configurations/config.properties");
        Properties properties = new Properties();
        properties.load(input);
        double width = Double.parseDouble(properties.getProperty("window.width"));
        double height = Double.parseDouble(properties.getProperty("window.height"));

        URL fxmlURL = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\com\\fhv\\hotelmanagement\\FXML\\main-view.fxml").toUri().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("Sunway Hotel");
        stage.setScene(scene);
        stage.show();

        //TestDB
        DBFacade dbf = new DBFacade();
        List<RoomCategory> roomCategories = dbf.getAllRoomCategories();
        for(RoomCategory r : roomCategories){
            System.out.println(r.toString());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
