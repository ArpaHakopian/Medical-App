package teama.patientscheduler;

import java.util.List;
import java.util.ArrayList;

public class AppointmentManager {

    public static List<String> getAvailableAppointmentsForSpecialist(Specialist specialist) {
        List<String> availableAppointments = new ArrayList<>();
        availableAppointments.add("2024-08-20 10:00 AM");
        availableAppointments.add("2024-08-20 11:00 AM");
        availableAppointments.add("2024-08-21 02:00 PM");
        return availableAppointments;
    }


    public static void bookAppointment(Specialist specialist, String appointmentTime) {

        RemindersModule reminder = new RemindersModule();
        reminder.setDate(appointmentTime.split(" ")[0]);
        reminder.setTime(appointmentTime.split(" ")[1]);
        reminder.setMessage("Appointment with " + specialist.getName());

        RemindersModule.addReminder(reminder);
    }
}
