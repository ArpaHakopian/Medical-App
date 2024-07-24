import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class patient{
    private String username;
    private String password;
    private String fullName;
    private String insuranceInfo;
    private List<Provider> providers;
    private List<Reminder> reminders;

    public patient(String username, String password, String fullName, String insuranceInfo){
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.insuranceInfo = insuranceInfo;
        this.providers = new ArrayList<>();
        this.reminders = new ArrayList<>();
    }

    public boolean login(String username, String password){
        return this.username.equals(username)&&this.password.equals(password);
    }
    public String getFullName() {
        return fullName;
    }
    
    public String getInsuranceInfo() {
        return insuranceInfo;
    }
    
    public List<Provider> getProviders() {
        return providers;
    }
    
    public void addProvider(Provider provider) {
        providers.add(provider);
    }
    
    public List<Reminder> getReminders() {
        return reminders;
    }
    
    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
    }
    
}