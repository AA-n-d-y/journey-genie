package com.genie.journey_genie.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {

    // Testing the Note class
    @Test
    void noteTest() {
        // Creating a Route2 object to associate with the Note

        String[] testCoords = {"coords"};
        String[] testPoints = {"points"};
        Route2 route = new Route2(testCoords, testPoints, "driving", null);
        //id fields are automatically generated by the persistence 
        //framework (e.g., Hibernate) when a new entity is saved to the database
        // So, let's set the id field of the route object to 1L, a long value
        route.setId(1L); //cause id is long

        // we could also use the constructor to set the id(SAME)
        // Route2 route = new Route2();
        // route.setId(1L);
        // route.setStartCoords("startCoords");
        // route.setEndCoords("endCoords");
        // route.setStartPoint("startPoint");
        // route.setEndPoint("endPoint");
        // route.setTravelMode("driving");



        // Creating the Note object 
        Note note = new Note();
        note.setId(1L);
        note.setRoute(route);
        note.setContent("This is a test note.");

        // Testing the getters for note
        assertEquals(1L, note.getId());
        assertEquals(route, note.getRoute());
        assertEquals("This is a test note.", note.getContent());

        // Testing the setters For note
        note.setId(2L);
        assertEquals(2L, note.getId());

        Route2 newRoute = new Route2();
        newRoute.setId(2L);
        note.setRoute(newRoute);
        assertEquals(newRoute, note.getRoute());

        note.setContent("Updated test note.");
        assertEquals("Updated test note.", note.getContent());
    }   

    // Testing the default constructor
    // checking that Note class's default constructor can successfully create a new instance of the Note class.
    @Test
    void testDefaultConstructor() {
        Note note = new Note(); //creates a new instance of the Note class using its default constructor. 
                                //default constructor is the no-argument constructor that initializes an object with default values.
        assertNotNull(note); // assertNotNull method checks that the note object is not null.
        //If the note object is not null, the assertion will pass, indicating that the 
        //default constructor successfully created an instance of the Note class.
    }
    

}