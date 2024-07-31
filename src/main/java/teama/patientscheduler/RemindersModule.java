package teama.patientscheduler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class RemindersModule {
    private String date;
    private String time;
    private String message;

    private static final String remindersFilePath = "reminders.json";

    // Getters
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    // Setters
    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Method to get reminders from file
    public static List<RemindersModel> getReminders() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<RemindersModel> reminders = objectMapper.readValue(new File(remindersFilePath), new TypeReference<List<RemindersModel>>(){});
            return reminders;
        } catch (IOException e) {
            System.err.println("FAILED to get reminders from: " + remindersFilePath);
            e.printStackTrace();
            return null;
        }
    }
}
