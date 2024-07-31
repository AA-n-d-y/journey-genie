// Java file for testing the user controller

package com.genie.journey_genie.controllers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.allOf;

import org.hamcrest.Matchers;
import java.nio.charset.Charset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;
import com.genie.journey_genie.models.*;
import org.springframework.mock.web.MockHttpSession;
import java.util.List;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    /// Setup

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")); 

    // Mock of our server
    @Autowired
    MockMvc mockMvc;

    // Mock of our repository
    @MockBean
    private UserRepository repository;

    // Controller
    @Autowired
    private UserController controller;

    @Value("${GOOGLE_API_KEY}")
    private String GOOGLE_API_KEY;

    /// Testing our functionality

    // Testing our controller
    @Test
    void controllerTest() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    void preferencesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/preferences"))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized())
        .andExpect(MockMvcResultMatchers.view().name("loginPage"));

        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "user");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);

        mockMvc.perform(MockMvcRequestBuilders.get("/preferences").session(session))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("preferences"))

        .andExpect(MockMvcResultMatchers.model().attribute("user", user))
        .andExpect(MockMvcResultMatchers.model().attribute("hasPreferences", false))
        .andExpect(MockMvcResultMatchers.model().attribute("GOOGLE_API_KEY", GOOGLE_API_KEY));

        Preferences preferences = new Preferences(1,2,false,"Canada",3,"hiking,beaches,sight seeing");
        user.setPreferences(preferences);

        String interests[] = preferences.getInterests().split(",");

        mockMvc.perform(MockMvcRequestBuilders.get("/preferences").session(session))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("preferences"))

        .andExpect(MockMvcResultMatchers.model().attribute("user", user))
        .andExpect(MockMvcResultMatchers.model().attribute("interests", interests))
        .andExpect(MockMvcResultMatchers.model().attribute("hasPreferences", true))
        .andExpect(MockMvcResultMatchers.model().attribute("GOOGLE_API_KEY", GOOGLE_API_KEY));
    }

    // Getting the registration page when logged out
    @Test
    void registrationPageTest() throws Exception {
        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/register"))

            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("register")); 
    }

    // Getting the registration page when logged in as a user
    @Test
    void registrationPageUTest() throws Exception {
        // Creating the user
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "user");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);

        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/register").session(session))

            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
            .andExpect(MockMvcResultMatchers.view().name("userPage"))
        
            .andExpect(MockMvcResultMatchers.model().attribute("user", 
                allOf(
                    hasProperty("firstName", Matchers.is("John")),
                    hasProperty("lastName", Matchers.is("Smith")),
                    hasProperty("username", Matchers.is("testuser")),
                    hasProperty("password", Matchers.is("54321")),
                    hasProperty("email", Matchers.is("johnsmith@example.com")),
                    hasProperty("type", Matchers.is("user"))
                )
            ));
    }

    // Getting the registration page when logged in as an admin
    @Test
    void registrationPageATest() throws Exception {
        // Creating the user
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "admin");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);
        List<User> users = new ArrayList<User>();
        users.add(user);

        // When the endpoint calls findAll(), return this instead
        when(repository.findAll()).thenReturn(users);

        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/register").session(session))

            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
            .andExpect(MockMvcResultMatchers.view().name("adminPage"))
        
            .andExpect(MockMvcResultMatchers.model().attribute("users", instanceOf(List.class)))
            .andExpect(MockMvcResultMatchers.model().attribute("users", hasSize(1)))
            .andExpect(MockMvcResultMatchers.model().attribute("users", contains(
                       hasProperty("firstName", is("John")) 
            )));
    }


    // Testing creating a new user
    @Test
    void registrationTest() throws Exception {

        // Mock the post request 
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
            .param("firstName", "John")
            .param("lastName", "Smith")
            .param("username", "testuser")
            .param("password", "54321")
            .param("email", "johnsmith@example.com")
            .param("type", "user"))

            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.view().name("loginPage"));
    }

    // Testing creating a duplicate user
    @Test
    void registrationDuplicateTest() throws Exception {
        // Creating the user
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "user");
        List<User> users = new ArrayList<User>();
        users.add(user);

        // When the user finds by username, return the list instead
        when(repository.findByUsername("testuser")).thenReturn(users);

        // Mock the post request for a duplicate user
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
            .param("firstName", "John")
            .param("lastName", "Smith")
            .param("username", "testuser")
            .param("password", "54321")
            .param("email", "johnsmith@example.com")
            .param("type", "user"))

            .andExpect(MockMvcResultMatchers.status().isConflict())
            .andExpect(MockMvcResultMatchers.view().name("userExists"));
    }


    // Testing saving preferences
    @Test
    void savePreferencesTest() throws Exception {
        Preferences preferences = new Preferences(1,2,false,"Canada",3,"hiking,beaches,sight seeing");
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "admin");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);

        mockMvc.perform(MockMvcRequestBuilders.post("/save-preferences").session(session)
            .param("duration", Integer.toString(preferences.getDuration()) )
            .param("activitiesPerDay", Integer.toString(preferences.getActivitiesPerDay()))
            .param("tolls", Boolean.toString(preferences.isTolls()))
            .param("location", preferences.getLocation())
            .param("range", Float.toString(preferences.getRange()))
            .param("interests", preferences.getInterests()))

            .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    
}
