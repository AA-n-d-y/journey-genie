// Java file for testing the User class

package com.genie.journey_genie.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void userTest() {
        // Creating the user
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "user");

        // Testing its methods
        
        assertEquals("John", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("testuser", user.getUsername());
        assertEquals("54321", user.getPassword());
        assertEquals("johnsmith@example.com", user.getEmail());
        assertEquals("user", user.getType());

        user.setFirstName("Bob");
        assertEquals("Bob", user.getFirstName());
        user.setLastName("Random");
        assertEquals("Random", user.getLastName());
        user.setUsername("testUser");
        assertEquals("testUser", user.getUsername());
        user.setPassword("67890");
        assertEquals("67890", user.getPassword());
        user.setEmail("bobrandom@example.com");
        assertEquals("bobrandom@example.com", user.getEmail());
        user.setType("admin");
        assertEquals("admin", user.getType());
    }   

}
