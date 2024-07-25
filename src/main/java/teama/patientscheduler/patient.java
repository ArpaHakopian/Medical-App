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
    public static patient loadFromFile(String username) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("patients.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[0].equals(username)) {
                patient user = new patient(parts[0], parts[1], parts[2], parts[3]);
                user.providers = loadProviders(parts[4]);
                user.reminders = loadReminders(parts[5]);
                reader.close();
                return user;
            }
        }
        reader.close();
        return null;
    }
    private static List<Provider> loadProviders(String filename) throws IOException {
        List<Provider> providers = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            providers.add(new Provider(parts[0], parts[1], parts[2]));
        }
        reader.close();
        return providers;
    }
    private static List<Reminder> loadReminders(String filename) throws IOException {
        List<Reminder> reminders = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            reminders.add(new Reminder(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3])));
        }
        reader.close();
        return reminders;
    }
    public void saveToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("patients.txt", true));
        writer.write(username + "," + password + "," + fullName + "," + insuranceInfo + "," + "providers_" + username + ".txt," + "reminders_" + username + ".txt");
        writer.newLine();
        writer.close();
        saveProviders();
        saveReminders();
    }
    private void saveProviders() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("providers_" + username + ".txt"));
        for (Provider provider : providers) {
            writer.write(provider.getName() + "," + provider.getSpecialty() + "," + provider.getLocation());
            writer.newLine();
        }
        writer.close();
    }
    
    private void saveReminders() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("reminders_" + username + ".txt"));
        for (Reminder reminder : reminders) {
            writer.write(reminder.getMessage() + "," + reminder.getDate() + "," + reminder.getProvider() + "," + reminder.isRead());
            writer.newLine();
        }
        writer.close();
    }
}