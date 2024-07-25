module teama.patientscheduler {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens teama.patientscheduler to javafx.fxml;
    exports teama.patientscheduler;
}