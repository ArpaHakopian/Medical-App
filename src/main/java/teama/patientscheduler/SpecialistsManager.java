package teama.patientscheduler;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;

//import static java.util.stream.Nodes.collect;

public class SpecialistsManager {
    public static List<Specialist> getFilteredData(String insurance, String specialty, String zipCode) {
        List<Specialist> unfilteredSpecialists = getAllSpecialists();
        List<Specialist> filteredSpecialists = new ArrayList<>();

        for (Specialist specialist : unfilteredSpecialists) {
            boolean matches = true;

            if (insurance != null && !insurance.equals(specialist.getInsurance())) {
                matches = false;
            }

            if (specialty != null && !specialty.equals(specialist.getSpecialty())) {
                matches = false;
            }

            if (zipCode != null && !zipCode.equals(specialist.getZipCode())) {
                matches = false;
            }

            if (matches) {
                filteredSpecialists.add(specialist);
            }
        }

        return filteredSpecialists;
    }

    public static List<Specialist> getAllSpecialists() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Specialist> specialists = objectMapper.readValue(new File("specialists.json"), new TypeReference<List<Specialist>>() {});
            return specialists;
        } catch (IOException e) {
            System.err.println("FAILED to get specialists located at: specialists.json");
            e.printStackTrace();
            return null;
        }
    }

    public static Set<String> getAllSpecialistTypes() {
        List<Specialist> allSpecialists = getAllSpecialists();

        if (allSpecialists != null) {
            return allSpecialists.stream()
                    .map(Specialist::getSpecialty)
                    .collect(Collectors.toSet());
        } else {
            return null;
        }
    }

    public static Set<String> getAllInsuranceCompanies() {
        List<Specialist> allInsuranceCompanies = getAllSpecialists();

        if (allInsuranceCompanies != null) {
            return allInsuranceCompanies.stream()
                    .map(Specialist::getInsurance)
                    .collect(Collectors.toSet());
        } else {
            return null;
        }
    }

    public static Set<String> getAllZipCodes() {
        List<Specialist> allZipCodes = getAllSpecialists();

        if (allZipCodes != null) {
            return allZipCodes.stream()
                    .map(Specialist::getZipCode)
                    .collect(Collectors.toSet());
        } else {
            return null;
        }
    }
}
