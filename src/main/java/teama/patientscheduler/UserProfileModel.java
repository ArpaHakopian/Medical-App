package teama.patientscheduler;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserProfileModel {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String zipCode;
    private String provider;

    private static final String demoUserFilePath = "demoUserProfile.json";

    public static Boolean isLoginDemoUser(String inputtedUsername, String inputtedPassword) {
        UserProfileModel demoUser = UserProfileModel.getDemoUser();

        if (demoUser.getUsername().equals(inputtedUsername) && demoUser.getPassword().equals(inputtedPassword)) {
            return true;
        }

        return false;
    }

    public static UserProfileModel getDemoUser() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            UserProfileModel demoUser = objectMapper.readValue(new File(demoUserFilePath), UserProfileModel.class);
            return demoUser;
        } catch (IOException e) {
            System.err.println("FAILED to get demo profile located at: " + demoUserFilePath);
            e.printStackTrace();
            return null;
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
