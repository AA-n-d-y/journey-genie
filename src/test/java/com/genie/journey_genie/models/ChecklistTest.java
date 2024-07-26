// Java file for testing the Checklist class

package com.genie.journey_genie.models;

import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChecklistTest {
    @Test
    void checklistTest() {
        // Creating the checklist
        Checklist checklist = new Checklist();

        // Creating lists
        List<String> activities = new ArrayList<>();
        activities.add("Hiking");
        List<String> places = new ArrayList<>();
        places.add("Vancouver");


        // Testing its methods

        checklist.setActivities(activities);
        assertEquals(activities, checklist.getActivities());
        
        checklist.setPlaces(places);
        assertEquals(places, checklist.getPlaces());
    }   

}
