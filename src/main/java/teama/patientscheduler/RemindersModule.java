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


    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //get reminders from file
    public static List<RemindersModule> getReminders() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<RemindersModule> reminders = objectMapper.readValue(new File(remindersFilePath), new TypeReference<List<RemindersModule>>(){});
            return reminders;
        } catch (IOException e) {
            System.err.println("FAILED to get reminders from: " + remindersFilePath);
            e.printStackTrace();
            return null;
        }
    }
    //add a new reminder
    public static void addReminder(RemindersModule newReminder) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<RemindersModule> reminders = getReminders();

        reminders.add(newReminder);

        try {
            objectMapper.writeValue(new File(remindersFilePath), reminders);
        } catch (IOException e) {
            System.err.println("FAILED to write reminders to: " + remindersFilePath);
            e.printStackTrace();
        }
    }
}
