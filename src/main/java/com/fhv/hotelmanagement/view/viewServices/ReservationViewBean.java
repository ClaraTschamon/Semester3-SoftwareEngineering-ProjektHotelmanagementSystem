package com.fhv.hotelmanagement.view.viewServices;

import com.fhv.hotelmanagement.view.DTOs.ReservationDTO;
import com.fhv.hotelmanagement.view.DTOs.ReservedRoomDTO;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationViewBean {
    private ReservationDTO reservationDTO;
    private long reservationNumber;
    private String lastName;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private ArrayList<Integer> roomNumbers = new ArrayList<Integer>();
    private static final double BUTTON_HEIGHT = 20;
    private Button imageButton;
    private ImageView confirmedImageView = new ImageView(new Image("com/fhv/hotelmanagement/fxml/Bilder/icons/confirmation.png"));
    private ImageView waitingImageView = new ImageView(new Image("com/fhv/hotelmanagement/fxml/Bilder/icons/waiting.png"));

    public ReservationViewBean (ReservationDTO reservationDTO) {
        this.reservationDTO = reservationDTO;
        this.reservationNumber = reservationDTO.getNumber();
        this.lastName = reservationDTO.getCustomer().getLastName();
        this.arrivalDate = reservationDTO.getArrivalDate();
        this.departureDate = reservationDTO.getDepartureDate();
        this.imageButton = new Button();
        this.imageButton.setPrefSize(Region.USE_COMPUTED_SIZE, BUTTON_HEIGHT);
        this.imageButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent");
        this.imageButton.setPadding(Insets.EMPTY);

        for(ReservedRoomDTO reservedRoomDTO : reservationDTO.getReservedRooms()) {
            this.roomNumbers.add(reservedRoomDTO.getRoom().getNumber());
        }

        if(reservationDTO.getBooking() == null) { //wenn booking vorhanden ist, ist sie bestätigt
            waitingImageView.setFitHeight(BUTTON_HEIGHT);
            waitingImageView.setPreserveRatio(true);
            imageButton.setGraphic(waitingImageView);
        } else {
            confirmedImageView.setFitHeight(BUTTON_HEIGHT);
            confirmedImageView.setPreserveRatio(true);
            imageButton.setGraphic(confirmedImageView);
        }
    }

    public ReservationDTO getReservationDTO() {
        return reservationDTO;
    }

    public long getReservationNumber() {
        return reservationNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public ArrayList<Integer> getRoomNumbers() {
        return roomNumbers;
    }

    public Button getImageButton(){return imageButton;}
}
