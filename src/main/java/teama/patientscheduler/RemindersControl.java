package teama.patientscheduler;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class RemindersControl implements Initializable {

    @FXML
    private ListView<HBox> remindersListView;

    private Map<String, Boolean> checkboxStates = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadReminders();
        startAutoRefresh();
    }

    private void loadReminders() {
        List<RemindersModule> reminders = RemindersModule.getReminders();
        ObservableList<HBox> items = FXCollections.observableArrayList();

        if (reminders != null) {
            for (RemindersModule reminder : reminders) {
                String reminderId = reminder.getDate() + reminder.getTime(); // Use a unique identifier for the reminder
                String reminderText = String.format("%s %s: %s", reminder.getDate(), reminder.getTime(), reminder.getMessage());

                CheckBox checkBox = new CheckBox();
                checkBox.setSelected(checkboxStates.getOrDefault(reminderId, false)); // Restore the checkbox state

                checkBox.setOnAction(event -> checkboxStates.put(reminderId, checkBox.isSelected())); // Update the state on change

                HBox hbox = new HBox(10, checkBox, new Text(reminderText));
                items.add(hbox);
            }
        }
        else {
            HBox errorBox = new HBox(new Text("Failed to load reminders."));
            items.add(errorBox);
        }

        remindersListView.setItems(items);
    }

    private void startAutoRefresh() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> loadReminders()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
