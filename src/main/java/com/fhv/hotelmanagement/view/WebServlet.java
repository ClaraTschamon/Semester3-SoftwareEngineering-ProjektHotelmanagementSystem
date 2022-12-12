//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view;
import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.domain.exceptions.CustomerIsInvalidException;
import com.fhv.hotelmanagement.domain.exceptions.ReservationIsInvalidException;
import com.fhv.hotelmanagement.view.DTOs.AddressDTO;
import com.fhv.hotelmanagement.view.DTOs.BoardDTO;
import com.fhv.hotelmanagement.view.DTOs.CustomerDTO;
import com.fhv.hotelmanagement.view.DTOs.ReservationDTO;
import com.fhv.hotelmanagement.view.controller.useCaseController.ReservationUseCaseController;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@jakarta.servlet.annotation.WebServlet(urlPatterns = "/Controller")
public class WebServlet extends HttpServlet {

    private HttpSession session;
    private ReservationUseCaseController useCaseController;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        ServletContext application = getServletContext();
        String dispatchto = "";
        String page = "";
        RequestDispatcher dispatcher;

        if (request.getParameter("dispatchto") != null) {
            dispatchto = request.getParameter("dispatchto");
        }

        switch (dispatchto) {
            case "newReservation":
                if(useCaseController == null){
                    useCaseController = new ReservationUseCaseController( //fehler hier
                            (LocalDate) session.getAttribute("arrivalDate"),
                            (LocalDate) session.getAttribute("departureDate")); //wird evtl. nullpointer exception verursachen
                }

                try {
                    saveData(request, response);
                } catch (ReservationIsInvalidException | CustomerIsInvalidException e) {
                    //page = "/ReservationErrorView.jsp";
                    //dispatcher = application.getRequestDispatcher(page);
                    //dispatcher.forward(request, response);
                    throw new RuntimeException(e);
                }

                page = "/ReservationSuccessView.jsp";
                dispatcher = application.getRequestDispatcher(page);
                dispatcher.forward(request, response);
                break;

            case "selectDates":
                LocalDate arrivalDate = LocalDate.parse(request.getParameter("arrivalDate"));
                LocalDate departureDate = LocalDate.parse(request.getParameter("departureDate"));
                if(useCaseController == null){
                    useCaseController = new ReservationUseCaseController(arrivalDate, departureDate);
                }
                int amountGuests = Integer.parseInt(request.getParameter("people-input"));
                session.setAttribute("arrivalDate", arrivalDate);
                session.setAttribute("departureDate", departureDate);
                session.setAttribute("amountGuests", amountGuests);
                session.setAttribute("maxSingleRooms", useCaseController.getMaxSingleRooms());
                session.setAttribute("maxDoubleRooms", useCaseController.getMaxDoubleRooms());
                session.setAttribute("maxFamilyRooms", useCaseController.getMaxFamilyRomms());
                session.setAttribute("maxSuites", useCaseController.getMaxSuites());

                page = "/ReservationForm.jsp";
                dispatcher = application.getRequestDispatcher(page);
                dispatcher.forward(request, response);
                break;
        }
    }

    public void saveData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ReservationIsInvalidException, CustomerIsInvalidException {
        ReservationDTO reservationDTO = useCaseController.getReservationDTO();
        CustomerDTO customerDTO = useCaseController.getCustomerDTO();
        AddressDTO customerAddressDTO = useCaseController.getCustomerDTO().getAddress();

        //get Attributes for Reservation
        LocalDateTime creationTimestamp = LocalDateTime.now();
        LocalDate arrivalDate = LocalDate.parse(request.getParameter("arrivalDate"));
        LocalDate departureDate = LocalDate.parse(request.getParameter("departureDate"));
        AddressDTO billingAddress = new AddressDTO(); //wieder auf null setzen
        String comment = "ddd"; //probe. wieder auf null setzen
        String paymentMethod = "Credit card"; //null
        String creditCardNumber = "1181 1818 1818 1818"; //null
        String expirationDate = "12/22"; //null
        String authorisationNumber = "123"; //null
        BoardDTO selectedBoard = getBoardByName(request.getParameter("Board"));
        System.out.println(selectedBoard.toString());
        Integer amountGuests = Integer.parseInt(request.getParameter("people-input"));

        //set Reservation Attributes
        reservationDTO.setCreationTimestamp(creationTimestamp);
        reservationDTO.setArrivalDate(arrivalDate);
        reservationDTO.setDepartureDate(departureDate);
        reservationDTO.setBillingAddress(billingAddress);
        reservationDTO.setComment(comment);
        reservationDTO.setPaymentMethod(paymentMethod);
        reservationDTO.setCreditCardNumber(creditCardNumber);
        reservationDTO.setExpirationDate(expirationDate);
        reservationDTO.setAuthorisationNumber(authorisationNumber);
        reservationDTO.setAmountGuests(amountGuests);

        //get Attributes for Customer
        String firstName = request.getParameter("firstname"); //input name=
        String lastName = request.getParameter("lastname");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("birthdate"));
        String nationality = request.getParameter("Nationality");
        String phoneNumber = request.getParameter("PhoneNumber");
        String email = request.getParameter("Email Address");

        //get Attributes for Customer's living Address
        String street = request.getParameter("Street");
        String houseNumber = request.getParameter("HouseNumber");
        String postalCode = request.getParameter("ZIPCode");
        String city = request.getParameter("City");
        String country = request.getParameter("Country");

        //set Address Attributes
        customerAddressDTO.setStreet(street);
        customerAddressDTO.setHouseNumber(houseNumber);
        customerAddressDTO.setPostalCode(postalCode);
        customerAddressDTO.setCity(city);
        customerAddressDTO.setCity(country);

        //set Customer Attributes
        customerDTO.setFirstName(firstName);
        customerDTO.setLastName(lastName);
        customerDTO.setDateOfBirth(dateOfBirth);
        customerDTO.setNationality(nationality);
        customerDTO.setPhoneNumber(phoneNumber);
        customerDTO.setEmail(email);
        customerDTO.setAddress(customerAddressDTO);

        int countSingleRooms = Integer.parseInt(request.getParameter("singleroom"));
        int countDoubleRooms = Integer.parseInt(request.getParameter("doubleroom"));
        int countFamilyRooms = Integer.parseInt(request.getParameter("familyroom"));
        int countSuites = Integer.parseInt(request.getParameter("suite"));
        useCaseController.createRoomReservations(countSingleRooms, countDoubleRooms,
                countFamilyRooms, countSuites, arrivalDate, departureDate);

        //save Data
        useCaseController.save();
    }

    private BoardDTO getBoardByName(String name) {
        for (BoardDTO boardDTO : DomainController.getAllBoards()) {
            if (boardDTO.getName().equals(name)) {
                return boardDTO;
            }
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
