// Java file for testing the checklist controller

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
import java.util.Optional;


@SpringBootTest
@AutoConfigureMockMvc
public class ChecklistControllerTest {

    /// Setup

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")); 

    // Mock of our server
    @Autowired
    MockMvc mockMvc;

    // Mock of the checklist repository
    @MockBean
    private ChecklistRepository checklistRepository;

    // Mock of the route repository
    @MockBean
    private Route2Repository routeRepository;

    // Controller
    @Autowired
    private ChecklistController controller;


    /// Testing our functionality
    
    // Testing our controller
    @Test
    void controllerTest() throws Exception {
        assertThat(controller).isNotNull();
    }


    // Getting the checklist page when logged out
    @Test
    void checklistPageLOTest() throws Exception {
        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/makeChecklist/checklist/1"))

            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
            .andExpect(MockMvcResultMatchers.view().name("loginPage")); 
    }

    // Getting the checklist page when the checklist is null
    @Test
    void checklistPageEmptyTest() throws Exception {
        // Creating the user for session validation
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "admin");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);

        // When the endpoint tries to find the route, return this
        Route2 route = new Route2();
        when(routeRepository.findById(1L)).thenReturn(Optional.of(route));

        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/makeChecklist/checklist/1").session(session))

            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("checklist"))
        
            .andExpect(MockMvcResultMatchers.model().attribute("checklistID", 1L));
    }

    // Getting the checklist page when the checklist is not null
    @Test
    void checklistPageTest() throws Exception {
        // Creating the user for session validation
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "admin");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);

        // When the endpoint tries to find the route, return this
        Route2 route = new Route2();
        route.setChecklist(new Checklist());
        route.getChecklist().getActivities().add("Activity1");
        route.getChecklist().getPlaces().add("Place1");
        when(routeRepository.findById(1L)).thenReturn(Optional.of(route));

        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/makeChecklist/checklist/1").session(session))

            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("checklist"))
        
            .andExpect(MockMvcResultMatchers.model().attribute("checklistID", 1L))
            .andExpect(MockMvcResultMatchers.model().attribute("activities", route.getChecklist().getActivities()))
            .andExpect(MockMvcResultMatchers.model().attribute("places", route.getChecklist().getPlaces()));
    }


    // Getting the "Add Items to Checklist" page when logged out
    @Test
    void addItemsPageLOTest() throws Exception {
        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/makeChecklist/1"))

            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
            .andExpect(MockMvcResultMatchers.view().name("loginPage")); 
    }

    // Getting the "Add Items to Checklist" page when logged in
    @Test
    void addItemsPageTest() throws Exception {
        // Creating the user for session validation
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "admin");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);

        // When the endpoint tries to find the route, return this
        Route2 route = new Route2();
        route.setChecklist(new Checklist());
        route.getChecklist().getActivities().add("Activity1");
        route.getChecklist().getPlaces().add("Place1");
        when(routeRepository.findById(1L)).thenReturn(Optional.of(route));

        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/makeChecklist/1").session(session))

            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("makeChecklist"))
        
            .andExpect(MockMvcResultMatchers.model().attribute("route", route));
    }


    // Testing creating a checklist/adding activities when logged out
    @Test
    void makeChecklistLOTest() throws Exception {
        // Mock the post request
        mockMvc.perform(MockMvcRequestBuilders.post("/makeChecklist/1"))

            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
            .andExpect(MockMvcResultMatchers.view().name("loginPage")); 
    } 

    // Testing creating a checklist/adding activities when checklist is null
    @Test
    void makeChecklistEmptyTest() throws Exception {
        // Creating the user for session validation
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "admin");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);

        // When the endpoint tries to find the route, return this
        Route2 route = new Route2();
        when(routeRepository.findById(1L)).thenReturn(Optional.of(route));

        // Mock the post request
        mockMvc.perform(MockMvcRequestBuilders.post("/makeChecklist/1").session(session)
            .param("activity", "Activity1"))

            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/makeChecklist/1"));
    }

    // Testing creating a checklist/adding activities when checklist is not null
    @Test
    void makeChecklistTest() throws Exception {
        // Creating the user for session validation
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "admin");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);

        // When the endpoint tries to find the route, return this
        Route2 route = new Route2();
        route.setChecklist(new Checklist());
        when(routeRepository.findById(1L)).thenReturn(Optional.of(route));

        // Mock the post request
        mockMvc.perform(MockMvcRequestBuilders.post("/makeChecklist/1").session(session)
            .param("activity", "Activity1"))

            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/makeChecklist/1"));
    }


    // Testing adding a place to the checklist when logged out
    @Test
    void addPlaceLOTest() throws Exception {
        // Mock the post request
        mockMvc.perform(MockMvcRequestBuilders.post("/addPlace/1"))

            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
            .andExpect(MockMvcResultMatchers.view().name("loginPage")); 
    }

    // Testing adding a place to the checklist when checklist is null
    @Test
    void addPlaceEmptyTest() throws Exception {
        // Creating the user for session validation
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "admin");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);

        // When the endpoint tries to find the route, return this
        Route2 route = new Route2();
        when(routeRepository.findById(1L)).thenReturn(Optional.of(route));

        // Mock the post request
        mockMvc.perform(MockMvcRequestBuilders.post("/addPlace/1").session(session)
            .param("placeTitle", "Place1"))

            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/route-details/1"));
    }

    // Testing adding a place to the checklist when checklist is not null
    @Test
    void addPlaceTest() throws Exception {
        // Creating the user for session validation
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "admin");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);

        // When the endpoint tries to find the route, return this
        Route2 route = new Route2();
        route.setChecklist(new Checklist());
        when(routeRepository.findById(1L)).thenReturn(Optional.of(route));

        // Mock the post request
        mockMvc.perform(MockMvcRequestBuilders.post("/addPlace/1").session(session)
            .param("placeTitle", "Place1"))

            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/route-details/1"));
    }


    // Testing removing an activity from the checklist when logged out
    @Test
    void removeActivityLOTest() throws Exception {
        // Mock the post request
        mockMvc.perform(MockMvcRequestBuilders.post("/deleteActivity/1"))

            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
            .andExpect(MockMvcResultMatchers.view().name("loginPage")); 
    }

    // Testing removing an activity from the checklist
    @Test
    void removeActivityTest() throws Exception {
        // Creating the user for session validation
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "admin");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);

        // When the endpoint tries to find the route, return this
        Route2 route = new Route2();
        route.setChecklist(new Checklist());
        route.getChecklist().setActivities(new ArrayList<>());
        route.getChecklist().getActivities().add("Activity1");
        when(routeRepository.findById(1L)).thenReturn(Optional.of(route));

        // Mock the post request
        mockMvc.perform(MockMvcRequestBuilders.post("/deleteActivity/1").session(session)
            .param("activityTitle", "Activity1"))

            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/makeChecklist/checklist/1"));
    }


    // Testing removing a place from the checklist when logged out
    @Test
    void removePlaceLOTest() throws Exception {
        // Mock the post request
        mockMvc.perform(MockMvcRequestBuilders.post("/deletePlace/1"))

            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
            .andExpect(MockMvcResultMatchers.view().name("loginPage")); 
    }

    // Testing removing a place from the checklist
    @Test
    void removePlaceTest() throws Exception {
        // Creating the user for session validation
        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "admin");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);

        // When the endpoint tries to find the route, return this
        Route2 route = new Route2();
        route.setChecklist(new Checklist());
        route.getChecklist().setPlaces(new ArrayList<>());
        route.getChecklist().getPlaces().add("Place1");
        when(routeRepository.findById(1L)).thenReturn(Optional.of(route));

        // Mock the post request
        mockMvc.perform(MockMvcRequestBuilders.post("/deletePlace/1").session(session)
            .param("placeTitle", "Place1"))

            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/makeChecklist/checklist/1"));
    }

}
