package com.fhv.hotelmanagement.services.EmailService;

import java.io.FileNotFoundException;

public interface EmailService {
    boolean sendMail(EmailInfo emailInfo) throws FileNotFoundException;
}
