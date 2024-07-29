// Java file for testing the logins controller

package com.genie.journey_genie.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.allOf;

import org.hamcrest.Matchers;
import java.nio.charset.Charset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.springframework.http.MediaType;
import org.springframework.http.MediaType.*;
import com.genie.journey_genie.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.mock.web.MockHttpSession;
import java.util.List;
import java.util.ArrayList;


@SpringBootTest
@AutoConfigureMockMvc
public class LoginsControllerTest {

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
    private LoginsController controller;


    /// Testing our functionality

    // Testing our controller
    @Test
    void controllerTest() throws Exception {
        assertThat(controller).isNotNull();
    }


    // Testing getting the login page when not logged in
    @Test
    void getLoginPageTest() throws Exception {
        
        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))

            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("loginPage")); 

    }

    // Testing getting the login page when logged in as a user
    @Test
    void getLoginPageUTest() throws Exception {
        // Creating the user
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "user");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);

        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/login").session(session))

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

    // Testing getting the login page when logged in as an admin
    @Test
    void getLoginPageATest() throws Exception {
        // Creating the user
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "admin");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);
        List<User> users = new ArrayList<User>();
        users.add(user);

        // When the endpoint calls findAll(), return this instead
        when(repository.findAll()).thenReturn(users);

        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/login").session(session))

            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
            .andExpect(MockMvcResultMatchers.view().name("adminPage"))
        
            .andExpect(MockMvcResultMatchers.model().attribute("users", instanceOf(List.class)))
            .andExpect(MockMvcResultMatchers.model().attribute("users", hasSize(1)))
            .andExpect(MockMvcResultMatchers.model().attribute("users", contains(
                       hasProperty("firstName", is("John")) 
            )));
    }


    // Testing getting the home page when not logged in
    @Test
    void getHomePageTest() throws Exception {
        
        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/home"))

            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
            .andExpect(MockMvcResultMatchers.view().name("loginPage")); 

    }

    // Testing getting the home page when logged in as a user
    @Test
    void getHomePageUTest() throws Exception {
        // Creating the user
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "user");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);

        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/home").session(session))

            .andExpect(MockMvcResultMatchers.status().isOk())
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

    // Testing getting the home page when logged in as an admin
    @Test
    void getHomePageATest() throws Exception {
        // Creating the user
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "admin");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);
        List<User> users = new ArrayList<User>();
        users.add(user);

        // When the endpoint calls findAll(), return this instead
        when(repository.findAll()).thenReturn(users);

        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/home").session(session))

            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("adminPage"))
        
            .andExpect(MockMvcResultMatchers.model().attribute("users", instanceOf(List.class)))
            .andExpect(MockMvcResultMatchers.model().attribute("users", hasSize(1)))
            .andExpect(MockMvcResultMatchers.model().attribute("users", contains(
                       hasProperty("firstName", is("John")) 
            )));
    }


    // Testing a successful login as a user
    @Test
    void loginSuccessTest() throws Exception {
        // Creating the user
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "user");
        List<User> users = new ArrayList<User>();
        users.add(user);

        // When the user finds by name and password, return the list instead
        when(repository.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(users);

        // Mock the post request for a user type login
        mockMvc.perform(MockMvcRequestBuilders.post("/home")
            .param("username", user.getUsername())
            .param("password", user.getPassword()))

            .andExpect(MockMvcResultMatchers.status().isOk())
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

    // Testing a successful login as an admin
    @Test
    void adminLoginSuccessTest() throws Exception {
        // Creating the user
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "admin");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);
        List<User> users = new ArrayList<User>();
        users.add(user);

        // When the user finds by name and password, return the list instead
        when(repository.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(users);

        // When the endpoint calls findAll(), return this instead
        when(repository.findAll()).thenReturn(users);

        // Mock the post request for an admin type login
        mockMvc.perform(MockMvcRequestBuilders.post("/home")
            .param("username", user.getUsername())
            .param("password", user.getPassword()))

            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("adminPage"))
        
            .andExpect(MockMvcResultMatchers.model().attribute("users", instanceOf(List.class)))
            .andExpect(MockMvcResultMatchers.model().attribute("users", hasSize(1)))
            .andExpect(MockMvcResultMatchers.model().attribute("users", contains(
                       hasProperty("firstName", is("John")) 
            )));
    }
    
    // Testing an unsuccessful login
    @Test
    void loginUnsuccessfulTest() throws Exception {

        List<User> users = new ArrayList<User>();

        // When the user finds by name and password, return the list instead
        when(repository.findByUsernameAndPassword("random", "random")).thenReturn(users);

        // Mock the post request for an unsuccessful login
        mockMvc.perform(MockMvcRequestBuilders.post("/home")
            .param("username", "random")
            .param("password", "random"))

            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
            .andExpect(MockMvcResultMatchers.view().name("loginError"));
    }


    // Testing getting the login page after going to the log out endpoint
    @Test
    void logoutTest() throws Exception {
        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/logout"))

            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("loginPage"));
    }

    // Testing getting the login page after clicking log out
    @Test
    void logoutUTest() throws Exception {
        // Mock the post request
        mockMvc.perform(MockMvcRequestBuilders.post("/logout"))

            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("loginPage"));
    }


    // Testing the redirect endpoint
    @Test
    void redirectTest() throws Exception {
        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/"))

            .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
    }

    
}
