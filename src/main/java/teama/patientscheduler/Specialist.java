package teama.patientscheduler;

public class Specialist {
    private String name;
    private String specialty;
    private String insurance;
    private String officeAddress;
    private String zipCode;

    // Default constructor
    public Specialist() {
    }

    public Specialist(String name, String specialty, String insurance, String officeAddress, String zipCode) {
        this.name = name;
        this.specialty = specialty;
        this.insurance = insurance;
        this.officeAddress = officeAddress;
        this.zipCode = zipCode;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}