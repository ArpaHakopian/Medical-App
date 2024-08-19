package teama.patientscheduler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainSceneControl implements Initializable {

    @FXML
    private Button clearFilterButton;

    @FXML
    private Button filterButton;

    @FXML
    private ChoiceBox<String> insuranceField;

    @FXML
    private ChoiceBox<String> specialistField;

    @FXML
    private ChoiceBox<String> zipCodeField;

    @FXML
    private TableView<Specialist> specialistsTable;

    @FXML
    private TableColumn<Specialist, String> insuranceColumn;

    @FXML
    private TableColumn<Specialist, String> nameColumn;

    @FXML
    private TableColumn<Specialist, String> officeAddressColumn;

    @FXML
    private TableColumn<Specialist, String> specialtyColumn;

    @FXML
    private TableColumn<Specialist, String> zipCodeColumn;

    @FXML
    public void handleClearFilterButton() {
        specialistField.setValue(null);
        insuranceField.setValue(null);
        zipCodeField.setValue(null);
    }
    @FXML
    public void handleFilterButton() {
        List<Specialist> filteredSpecialists = SpecialistsManager.getFilteredData(insuranceField.getValue(), specialistField.getValue(), zipCodeField.getValue());

        specialistsTable.setItems(FXCollections.observableArrayList(filteredSpecialists));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> insuranceOptions = FXCollections.observableArrayList(SpecialistsManager.getAllInsuranceCompanies());
        insuranceField.setItems(insuranceOptions);

        ObservableList<String> specialistsOptions = FXCollections.observableArrayList(SpecialistsManager.getAllSpecialistTypes());
        specialistField.setItems(specialistsOptions);

        ObservableList<String> zipCodeOptions = FXCollections.observableArrayList(SpecialistsManager.getAllZipCodes());
        zipCodeField.setItems(zipCodeOptions);

        nameColumn.setCellValueFactory(new PropertyValueFactory<Specialist, String>("name"));
        specialtyColumn.setCellValueFactory(new PropertyValueFactory<Specialist, String>("specialty"));
        insuranceColumn.setCellValueFactory(new PropertyValueFactory<Specialist, String>("insurance"));
        officeAddressColumn.setCellValueFactory(new PropertyValueFactory<Specialist, String>("officeAddress"));
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<Specialist, String>("zipCode"));

        ObservableList<Specialist> specialists = specialistsTable.getItems();

        specialists.addAll(Objects.requireNonNull(SpecialistsManager.getAllSpecialists()));
        specialistsTable.setItems(specialists);

        specialistsTable.setRowFactory(_ -> {
            TableRow<Specialist> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Specialist rowData = row.getItem();
                    handleRowDoubleClick(rowData);
                }
            });
            return row;
        });
    }

   // private void handleRowDoubleClick(Specialist specialist) {
   //     System.out.println("Double-clicked on: " + specialist.getName() + ", Zip Code: " + specialist.getZipCode());
  //  }
    @FXML
    private void handleRowDoubleClick(Specialist specialist) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("appointmentBookingSceneView.fxml"));
            VBox appointmentBookingScene = loader.load();

            AppointmentBookingControl controller = loader.getController();
            controller.setSelectedSpecialist(specialist);

            Stage stage = new Stage();
            Scene scene = new Scene(appointmentBookingScene);
            stage.setScene(scene);
            stage.setTitle("Book Appointment");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Failed to load appointment booking scene.");
            alert.showAndWait();
        }
    }

}
