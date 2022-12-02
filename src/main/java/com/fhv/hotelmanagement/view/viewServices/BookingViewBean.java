package com.fhv.hotelmanagement.view.viewServices;


import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookingViewBean {

    private BookingDTO bookingDTO;
    private long bookingNumber;
    private String lastName;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private ArrayList<Integer> roomNumbers = new ArrayList<>();
    private Image checkedInImage = new Image("com/fhv/hotelmanagement/fxml/Bilder/icons/confirmation.png");
    //private ImageView checkedInImageView = new ImageView();

    //private char checkedInSymbol = '\u2611';

    private String symbol = "\u2757";


    public BookingViewBean(BookingDTO bookingDTO){
        this.bookingDTO = bookingDTO;
        this.bookingNumber = bookingDTO.getNumber();
        this.lastName = bookingDTO.getCustomer().getLastName();
        this.arrivalDate = bookingDTO.getArrivalDate();
        this.departureDate = bookingDTO.getDepartureDate();
        for(BookedRoomDTO bookedRoomDTO : bookingDTO.getBookedRooms()){
            this.roomNumbers.add(bookedRoomDTO.getRoom().getNumber());
        }
        /*
        if(bookingDTO.getCheckInDatetime() != null && bookingDTO.getCheckOutDatetime() == null){
            //dann ist der status eingechecked
            System.out.println(symbol);
        }*/
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
}
