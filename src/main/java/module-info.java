module com.fhv.hotelmanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires jakarta.persistence;
    requires com.fasterxml.classmate;
    requires org.glassfish.jaxb.runtime;
    requires com.h2database;
    requires org.hibernate.commons.annotations;
    requires net.bytebuddy;

    requires org.controlsfx.controls;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires org.apache.fontbox;
    requires org.apache.pdfbox;
    requires java.desktop;
    requires jakarta.servlet.jsp.jstl;
    requires jakarta.servlet;

    exports com.fhv.hotelmanagement.view.controller.viewController;
    opens com.fhv.hotelmanagement.view.controller.viewController to javafx.fxml;
    exports com.fhv.hotelmanagement;
    opens com.fhv.hotelmanagement to javafx.fxml;
    opens com.fhv.hotelmanagement.view.DTOs to javafx.base;
    opens com.fhv.hotelmanagement.persistence.persistenceEntity to org.hibernate.orm.core;
    exports com.fhv.hotelmanagement.persistence.persistenceEntity;
    opens com.fhv.hotelmanagement.view.viewServices to javafx.base;
}