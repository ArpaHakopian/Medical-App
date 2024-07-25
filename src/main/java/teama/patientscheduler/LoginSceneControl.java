package teama.patientscheduler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginSceneControl implements Initializable {
    UserProfileModel loggedInUserProfile;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private void handleSubmit() throws IOException {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please fill out all fields before attempting to login.");
            alert.showAndWait();
            return;
        }

        if (!UserProfileModel.isLoginDemoUser(usernameField.getText(), passwordField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid username or password.");
            alert.showAndWait();
            return;
        }

        // TODO: find the best way to pass the logged in userProfile to the main scene...
        // UserProfileModel loggedInUserProfile = UserProfileModel.getDemoUser();
        PatientSchedulerApp.getInstance().showMainScene();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameField.textProperty().addListener((_, _, newValue) -> {
            if (newValue.contains(" ")) {
                usernameField.setText(newValue.replace(" ", ""));
            }
        });

        passwordField.textProperty().addListener((_, _, newValue) -> {
            if (newValue.contains(" ")) {
                passwordField.setText(newValue.replace(" ", ""));
            }
        });
    }
}