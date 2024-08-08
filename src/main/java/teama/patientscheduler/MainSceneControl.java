package teama.patientscheduler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainSceneControl implements Initializable {

    @FXML
    private Button clearFilterButton;

    @FXML
    private Button filterButton;

    @FXML
    private ComboBox<String> insuranceField;

    @FXML
    private ComboBox<?> specialistField;

    @FXML
    private ComboBox<?> zipCodeField;

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

//    public void

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        ObservableList<String> insuranceOptionsList = new ArrayList<>(SpecialistsManager.getAllSpecialistTypes());
        ObservableList<String> insuranceOptionsList = FXCollections.observableArrayList(SpecialistsManager.getAllSpecialistTypes());
        insuranceField.setItems(insuranceOptionsList);

        nameColumn.setCellValueFactory(new PropertyValueFactory<Specialist, String>("name"));
        specialtyColumn.setCellValueFactory(new PropertyValueFactory<Specialist, String>("specialty"));
        insuranceColumn.setCellValueFactory(new PropertyValueFactory<Specialist, String>("insurance"));
        officeAddressColumn.setCellValueFactory(new PropertyValueFactory<Specialist, String>("officeAddress"));
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<Specialist, String>("zipCode"));

        ObservableList<Specialist> specialists = specialistsTable.getItems();

        specialists.addAll(Objects.requireNonNull(SpecialistsManager.getAllSpecialists()));
        specialistsTable.setItems(specialists);
    }
}
