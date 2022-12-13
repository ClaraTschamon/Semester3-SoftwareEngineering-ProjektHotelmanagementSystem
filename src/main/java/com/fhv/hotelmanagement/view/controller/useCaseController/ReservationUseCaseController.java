//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.useCaseController;

import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.domain.exceptions.CustomerIsInvalidException;
import com.fhv.hotelmanagement.domain.exceptions.ReservationIsInvalidException;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ReservationUseCaseController {
    private ReservationDTO reservationDTO;
    private CustomerDTO customerDTO;
    ArrayList<RoomDTO> freeRooms;
    ArrayList<RoomDTO> freeSingleRooms;
    ArrayList<RoomDTO> freeDoubleRooms;
    ArrayList<RoomDTO> freeFamilyRooms;
    ArrayList<RoomDTO> freeSuites;

    private int maxSingleRooms;
    private int maxDoubleRooms;
    private int maxFamilyRomms;
    private int maxSuites;

    public ReservationUseCaseController(LocalDate arrivalDate, LocalDate departureDate) throws IOException {

        reservationDTO = new ReservationDTO();
        customerDTO = new CustomerDTO();
        reservationDTO.setCustomer(customerDTO);
        freeSingleRooms = new ArrayList<>();
        freeDoubleRooms = new ArrayList<>();
        freeFamilyRooms = new ArrayList<>();
        freeSuites = new ArrayList<>();
        freeRooms = createFreeRoomsList(arrivalDate, departureDate);
        createFreeRoomsLists2(freeRooms);
        maxSingleRooms = freeSingleRooms.size();
        maxDoubleRooms = freeDoubleRooms.size();
        maxFamilyRomms = freeFamilyRooms.size();
        maxSuites = freeSuites.size();
    }

    public ReservationDTO getReservationDTO() {
        return reservationDTO;
    }

    public void setReservationDTO(ReservationDTO reservationDTO) {
        this.reservationDTO = reservationDTO;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public int getMaxSingleRooms() {
        return maxSingleRooms;
    }

    public int getMaxDoubleRooms() {
        return maxDoubleRooms;
    }

    public int getMaxFamilyRomms() {
        return maxFamilyRomms;
    }

    public int getMaxSuites() {
        return maxSuites;
    }

    public ArrayList<RoomDTO> createFreeRoomsList(LocalDate minDate, LocalDate maxDate) {
        ArrayList<RoomDTO> allRooms = DomainController.getAllRooms();
        ArrayList<BookedRoomDTO> bookedRoomsBetween = DomainController.getBookedRoomsBetween(minDate, maxDate);
        ArrayList<ReservedRoomDTO> reservedRoomsBetween = DomainController.getReservedRoomsBetween(minDate, maxDate);
        ArrayList<RoomDTO> freeRooms = new ArrayList<>();

        for (RoomDTO roomDTO : allRooms) {
            boolean free = true;
            for(BookedRoomDTO bookedRoomDTO : bookedRoomsBetween){
                if (bookedRoomDTO.getRoom().getNumber() == roomDTO.getNumber()) {
                    free = false;
                    break;
                }
            }
            for(ReservedRoomDTO reservedRoomDTO : reservedRoomsBetween){
                if(reservedRoomDTO.getRoom().getNumber() == roomDTO.getNumber()){
                    free = false;
                    break;
                }
            }

            if(free){
                freeRooms.add(roomDTO);
            }
        }

        return freeRooms;
    }

    private void createFreeRoomsLists2(ArrayList<RoomDTO> freeRooms){

        //freeRoomSingleRooms,... Listen befüllen
        for(RoomDTO roomDTO : freeRooms){
            switch (roomDTO.getCategory().getName()){
                case "Single room":
                    freeSingleRooms.add(roomDTO);
                    break;
                case "Double room":
                    freeDoubleRooms.add(roomDTO);
                    break;
                case "Family room":
                    freeFamilyRooms.add(roomDTO);
                    break;
                case "Suite":
                    freeSuites.add(roomDTO);
                    break;
            }
        }
    }

    public void createRoomReservations(int countSingleRooms, int countDoubleRooms, int countFamilyRooms, int countSuites,
                                       LocalDate arrivalDate, LocalDate departureDate) {

        freeRooms = createFreeRoomsList(arrivalDate, departureDate);

        createFreeRoomsLists2(freeRooms);

        //freie räume zuteilen...
        ArrayList<ReservedRoomDTO> reservedRooms = new ArrayList<>();
        for (int i = 0; i <= countSingleRooms; i++) {
            RoomDTO roomDTO = freeSingleRooms.get(i);
            reservedRooms.add(new ReservedRoomDTO(reservationDTO, roomDTO, arrivalDate, departureDate));
        }
        for(int i = 0; i <= countDoubleRooms; i++){
            RoomDTO roomDTO = freeDoubleRooms.get(i);
            reservedRooms.add(new ReservedRoomDTO(reservationDTO, roomDTO, arrivalDate, departureDate));
        }
        for(int i = 0; i <= countFamilyRooms; i++){
            RoomDTO roomDTO = freeFamilyRooms.get(i);
            reservedRooms.add(new ReservedRoomDTO(reservationDTO, roomDTO, arrivalDate, departureDate));
        }
        for(int i = 0; i <= countSuites; i++){
            RoomDTO roomDTO = freeSuites.get(i);
            reservedRooms.add(new ReservedRoomDTO(reservationDTO, roomDTO, arrivalDate, departureDate));
        }

        reservationDTO.setReservedRooms(reservedRooms);


        HashMap<String, RoomCategoryDTO> roomCategories = DomainController.getAllRoomCategories();
        ArrayList<ReservedRoomCategoryDTO> reservedRoomCategories = new ArrayList<>();
        if(countSingleRooms > 0){
            RoomCategoryDTO roomCategoryDTO = roomCategories.get("Single room");
            reservedRoomCategories.add(new ReservedRoomCategoryDTO(reservationDTO, roomCategoryDTO,
                    roomCategoryDTO.getPricePerNight(), countSingleRooms));
        }
        if(countDoubleRooms > 0){
            RoomCategoryDTO roomCategoryDTO = roomCategories.get("Double room");
            reservedRoomCategories.add(new ReservedRoomCategoryDTO(reservationDTO, roomCategoryDTO,
                    roomCategoryDTO.getPricePerNight(), countDoubleRooms));
        }
        if(countFamilyRooms > 0){
            RoomCategoryDTO roomCategoryDTO = roomCategories.get("Family room");
            reservedRoomCategories.add(new ReservedRoomCategoryDTO(reservationDTO, roomCategoryDTO,
                    roomCategoryDTO.getPricePerNight(), countFamilyRooms));
        }
        if(countSuites > 0){
            RoomCategoryDTO roomCategoryDTO = roomCategories.get("Suite");
            reservedRoomCategories.add(new ReservedRoomCategoryDTO(reservationDTO, roomCategoryDTO,
                    roomCategoryDTO.getPricePerNight(), countSuites));
        }

        reservationDTO.setReservedRoomCategories(reservedRoomCategories);
    }



    public void save() throws CustomerIsInvalidException, ReservationIsInvalidException {
        if (reservationDTO != null && customerDTO != null) {
            reservationDTO.setPricePerNightForBoard(reservationDTO.getBoard().getPricePerNight()); //nullpointer exception
            reservationDTO.setCreationTimestamp(LocalDateTime.now());
            customerDTO.setSaved(true);
            Long customerNumber = DomainController.saveCustomer(customerDTO);
            customerDTO.setNumber(customerNumber);
            reservationDTO.setCustomer(customerDTO);
            DomainController.saveReservation(reservationDTO);
        }
    }
}
