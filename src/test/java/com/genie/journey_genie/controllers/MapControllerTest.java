package com.genie.journey_genie.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.genie.journey_genie.models.User;

@WebMvcTest(MapController.class)
public class MapControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${GOOGLE_API_KEY}")
    private String GOOGLE_API_KEY;

    @Value("${env.TRIPADVISOR_API_KEY}")
    private String TRIPADVISOR_API_KEY;

    @Test
    void testGenerateTrip() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/index/address1&address2"))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized())
        .andExpect(MockMvcResultMatchers.view().name("loginPage"));

        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "user");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);

        mockMvc.perform(MockMvcRequestBuilders.get("/index/address1&address2").session(session))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("index"))

        .andExpect(MockMvcResultMatchers.model().attribute("GOOGLE_API_KEY", GOOGLE_API_KEY))
        .andExpect(MockMvcResultMatchers.model().attribute("TRIPADVISOR_API_KEY", TRIPADVISOR_API_KEY))
        .andExpect(MockMvcResultMatchers.model().attribute("addresses", "address1&address2"))
        .andExpect(MockMvcResultMatchers.model().attribute("isGeneratedTrip", true));

    }

    @Test
    void testShowMap() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/map"))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized())
        .andExpect(MockMvcResultMatchers.view().name("loginPage"));

        User user = new User("John", "Smith", "testuser", "54321", "johnsmith@example.com", "user");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", user);

        mockMvc.perform(MockMvcRequestBuilders.get("/map").session(session))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("index"))

        .andExpect(MockMvcResultMatchers.model().attribute("GOOGLE_API_KEY", GOOGLE_API_KEY))
        .andExpect(MockMvcResultMatchers.model().attribute("TRIPADVISOR_API_KEY", TRIPADVISOR_API_KEY))
        .andExpect(MockMvcResultMatchers.model().attribute("isGeneratedTrip", false));
    }
}
