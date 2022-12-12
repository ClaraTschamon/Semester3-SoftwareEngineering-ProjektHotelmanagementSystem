package com.fhv.hotelmanagement.view;
import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.domain.factory.BoardFactory;
import com.fhv.hotelmanagement.domain.factory.RoomCategoryFactory;
import com.fhv.hotelmanagement.domain.factory.RoomFactory;
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
                page = "/ReservationSuccessView.jsp";
                //saveData(request, response);

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

    public void saveData(HttpServletRequest request, HttpServletResponse response){
        ReservationDTO reservationDTO = useCaseController.getReservationDTO();
        CustomerDTO customerDTO = useCaseController.getCustomer();

        LocalDateTime creationTimestamp = LocalDateTime.now();
        LocalDate arrivalDate = LocalDate.parse(request.getParameter("arrivalDate"));
        LocalDate departureDate = LocalDate.parse(request.getParameter("departureDate"));
        int amountGuests = Integer.parseInt(request.getParameter("people-input"));

        String board = request.getParameter("Board");
        System.out.println(board);
        /*BoardDTO selectedBoard = getBoardByName(request.getParameter("Board"));
        System.out.println(selectedBoard.toString());*/

        String firstName = request.getParameter("firstname"); //input name=
        String lastName = request.getParameter("lastname");

        int countSingleRooms = Integer.parseInt(request.getParameter("singleroom"));
        int countDoubleRooms = Integer.parseInt(request.getParameter("doubleroom"));
        int countFamilyRooms = Integer.parseInt(request.getParameter("familyroom"));
        int countSuites = Integer.parseInt(request.getParameter("suite"));
        //ganz am schluss... erst wenn reservierung erstellt ist.
        useCaseController.createRoomReservations(countSingleRooms, countDoubleRooms,
                countFamilyRooms, countSuites, arrivalDate, departureDate);
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
