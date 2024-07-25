package teama.patientscheduler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientSchedulerApp extends Application {
    private static PatientSchedulerApp instance;
    private Stage primaryStage;

    public PatientSchedulerApp() {
        instance = this;
    }

    public static PatientSchedulerApp getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        showLoginScene();
    }

    private void showLoginScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientSchedulerApp.class.getResource("loginSceneView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    public void showMainScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientSchedulerApp.class.getResource("mainSceneView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }
}