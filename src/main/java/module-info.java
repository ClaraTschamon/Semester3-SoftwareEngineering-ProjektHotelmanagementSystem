module com.fhv.hotelmanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires jakarta.persistence;
    requires com.fasterxml.classmate;
    requires org.glassfish.jaxb.runtime;
    requires com.h2database;
    requires org.hibernate.commons.annotations;
    requires net.bytebuddy;

    exports com.fhv.hotelmanagement.controller.viewController;
    opens com.fhv.hotelmanagement.controller.viewController to javafx.fxml;
    exports com.fhv.hotelmanagement;
    opens com.fhv.hotelmanagement to javafx.fxml;
//    opens com.fhv.hotelmanagement.controller to javafx.fxml;
}