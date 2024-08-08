package teama.patientscheduler;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;

//import static java.util.stream.Nodes.collect;

public class SpecialistsManager {
    private List<Specialist> specialists;

    public static List<Specialist> getAllSpecialists() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read the JSON file into a List of Specialist objects
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
//            Stream<Specialist> stream = allSpecialists.stream();
//            Stream<String> tream = stream.map(Specialist::getSpecialty);
//            return tream.collect(Collectors.toSet());
            return allSpecialists.stream()
                    .map(Specialist::getSpecialty)
                    .collect(Collectors.toSet());
        } else {
            return null;
        }

//        if (allSpecialists != null) {
//            // Collect all unique specialties
//            return allSpecialists.map(Specialist::getSpecialty)
//                    .collect(Collectors.toSet());
//        } else {
//            return null;
//        }
    }
}
