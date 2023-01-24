//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.services.EmailService;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class Email implements IEmailService {
    private String from;
    private String to;
    private String cc;
    private String subject;
    private String body;

    public Email() {}

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public void sendMail(Email email) {
        try{
            /*absoluter Pfad angeben weil es Tomcat sonst nicht findet!*/
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("C:\\Users\\clara\\IdeaProjects\\Hotelmanagement\\Emails.txt", true));
            //DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("C:\\Users\\samuel\\Documents\\GitHub\\Hotelmanagement\\Emails.txt", true));
            //DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("C:\\Users\\ninah\\FH Dornbirn\\Hotelmanagement\\Emails.txt", true));
            dataOutputStream.writeUTF(LocalDateTime.now().toString());
            dataOutputStream.writeUTF(": ");
            dataOutputStream.writeUTF(email.toString());
            dataOutputStream.write('\n');
            dataOutputStream.flush();
            dataOutputStream.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Email{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", cc='" + cc + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
