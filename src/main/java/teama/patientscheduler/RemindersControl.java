package teama.patientscheduler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RemindersControl implements Initializable {

    @FXML
    private ListView<HBox> remindersListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadReminders();
    }

    private void loadReminders() {
        List<RemindersModule> reminders = RemindersModule.getReminders();
        if (reminders != null) {
            for (RemindersModule reminder : reminders) {
                String reminderText = String.format("%s %s: %s", reminder.getDate(), reminder.getTime(), reminder.getMessage());

                CheckBox checkBox = new CheckBox();
                HBox hbox = new HBox(10, checkBox, new Text(reminderText));

                remindersListView.getItems().add(hbox);
            }
        } else {
            HBox errorBox = new HBox(new Text("Failed to load reminders."));
            remindersListView.getItems().add(errorBox);
        }
    }
}
