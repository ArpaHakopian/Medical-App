package teama.patientscheduler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AppointmentBookingControl implements Initializable {

    @FXML
    private Label specialistNameLabel;

    @FXML
    private ListView<String> appointmentsListView;

    @FXML
    private Button bookAppointmentButton;

    private Specialist selectedSpecialist;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookAppointmentButton.setOnAction(event -> bookSelectedAppointment());
    }

    public void setSelectedSpecialist(Specialist specialist) {
        this.selectedSpecialist = specialist;
        specialistNameLabel.setText(specialist.getName());


        loadAvailableAppointments();
    }

    private void loadAvailableAppointments() {
        List<String> appointments = AppointmentManager.getAvailableAppointmentsForSpecialist(selectedSpecialist);

        ObservableList<String> appointmentsObservableList = FXCollections.observableArrayList(appointments);
        appointmentsListView.setItems(appointmentsObservableList);
    }

    private void bookSelectedAppointment() {
        String selectedAppointment = appointmentsListView.getSelectionModel().getSelectedItem();

        if (selectedAppointment != null) {
            AppointmentManager.bookAppointment(selectedSpecialist, selectedAppointment);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Appointment booked successfully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please select an appointment to book.");
            alert.showAndWait();
        }
    }
}
