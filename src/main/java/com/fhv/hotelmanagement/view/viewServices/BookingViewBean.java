//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.viewServices;

import com.fhv.hotelmanagement.view.DTOs.BookedRoomDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookingViewBean {
    private BookingDTO bookingDTO;
    private long bookingNumber;
    private String lastName;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private String comment;
    private ArrayList<Integer> roomNumbers = new ArrayList<>();


    private static final double BUTTON_HEIGHT = 20;
    private Button imageButton;
    private Button commentButton;
    private ImageView checkedInImageView = new ImageView(new Image("com/fhv/hotelmanagement/fxml/Bilder/icons/confirmation.png"));
    private ImageView checkedOutImageView = new ImageView(new Image("com/fhv/hotelmanagement/fxml/Bilder/icons/checkedOut.png"));
    private ImageView waitingImageView = new ImageView(new Image("com/fhv/hotelmanagement/fxml/Bilder/icons/waiting.png"));
    private ImageView commentImageView = new ImageView(new Image("com/fhv/hotelmanagement/fxml/Bilder/icons/comment.png"));
    private ImageView noCommentImageView = new ImageView(new Image("com/fhv/hotelmanagement/fxml/Bilder/icons/noComment.png"));

    public BookingViewBean(BookingDTO bookingDTO){
        this.bookingDTO = bookingDTO;
        this.bookingNumber = bookingDTO.getNumber();
        this.lastName = bookingDTO.getCustomer().getLastName();
        this.arrivalDate = bookingDTO.getArrivalDate();
        this.departureDate = bookingDTO.getDepartureDate();
        this.comment = bookingDTO.getComment();

        this.imageButton = new Button();
        this.imageButton.setPrefSize(Region.USE_COMPUTED_SIZE, BUTTON_HEIGHT);
        this.imageButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent");
        this.imageButton.setPadding(Insets.EMPTY);

        this.commentButton = new Button();
        this.commentButton.setPrefSize(Region.USE_COMPUTED_SIZE, BUTTON_HEIGHT);
        this.commentButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent");
        this.commentButton.setPadding(Insets.EMPTY);

        for(BookedRoomDTO bookedRoomDTO : bookingDTO.getBookedRooms()){
            this.roomNumbers.add(bookedRoomDTO.getRoom().getNumber());
        }

        if(bookingDTO.getCheckInDatetime() != null && bookingDTO.getCheckOutDatetime() == null){
            //dann ist der status eingechecked
            checkedInImageView.setFitHeight(BUTTON_HEIGHT);
            checkedInImageView.setPreserveRatio(true);
            imageButton.setGraphic(checkedInImageView);
        } else if(bookingDTO.getCheckOutDatetime() != null){
            //dann ist der Status ausgechecked
            checkedOutImageView.setFitHeight(BUTTON_HEIGHT);
            checkedOutImageView.setPreserveRatio(true);
            imageButton.setGraphic(checkedOutImageView);

        } else if(bookingDTO.getCheckInDatetime() == null){
            //dann ist der Status nicht eingechecked und nicht ausgechecked
            waitingImageView.setFitHeight(BUTTON_HEIGHT);
            waitingImageView.setPreserveRatio(true);
            imageButton.setGraphic(waitingImageView);
        }

        if(bookingDTO.getComment() != null){
            if(bookingDTO.getComment().equals("")){
                noCommentImageView.setFitHeight(BUTTON_HEIGHT);
                noCommentImageView.setPreserveRatio(true);
                commentButton.setGraphic(noCommentImageView);
            } else {
                commentImageView.setFitHeight(BUTTON_HEIGHT);
                commentImageView.setPreserveRatio(true);
                commentButton.setGraphic(commentImageView);
                Tooltip tooltip = new Tooltip();
                tooltip.setText(comment);
                commentButton.setTooltip(tooltip);
            }
        } else{
            noCommentImageView.setFitHeight(BUTTON_HEIGHT);
            noCommentImageView.setPreserveRatio(true);
            commentButton.setGraphic(noCommentImageView);
        }
    }

    public BookingDTO getBookingDTO() {
        return bookingDTO;
    }

    public long getBookingNumber() {
        return bookingNumber;
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

    public String getComment() {
        return comment;
    }

    public Button getImageButton() {
        return imageButton;
    }

    public Button getCommentButton() {
        return commentButton;
    }
}
