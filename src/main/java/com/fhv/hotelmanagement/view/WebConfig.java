package com.fhv.hotelmanagement.view;

import com.fhv.hotelmanagement.services.EmailService.Email;
import com.fhv.hotelmanagement.services.EmailService.IEmailService;
import com.fhv.hotelmanagement.view.controller.useCaseController.ReservationUseCaseController;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.IOException;

@WebListener
public class WebConfig implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        // Do your thing during webapp's startup.
        try {
            IEmailService emailService = new Email();
            ReservationUseCaseController reservationUseCaseController = new ReservationUseCaseController(emailService);
            ServletContext context = event.getServletContext();
            context.setAttribute("reservationUseCaseController", reservationUseCaseController);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
