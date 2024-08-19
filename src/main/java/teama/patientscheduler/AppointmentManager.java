package teama.patientscheduler;

import java.util.List;
import java.util.ArrayList;

public class AppointmentManager {

    public static List<String> getAvailableAppointmentsForSpecialist(Specialist specialist) {
        // Simulated data
        List<String> availableAppointments = new ArrayList<>();
        availableAppointments.add("2024-08-20 10:00 AM");
        availableAppointments.add("2024-08-20 11:00 AM");
        availableAppointments.add("2024-08-21 02:00 PM");
        return availableAppointments;
    }

    public static void bookAppointment(Specialist specialist, String appointmentTime) {
        // Simulated booking logic
        System.out.println("Booking appointment with " + specialist.getName() + " at " + appointmentTime);
    }
}
