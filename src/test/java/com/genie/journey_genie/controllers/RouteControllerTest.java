package com.genie.journey_genie.controllers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.genie.journey_genie.models.Route;
import com.genie.journey_genie.models.RouteRepository;

@WebMvcTest(RouteController.class)
public class RouteControllerTest {

    @MockBean
    private RouteRepository routeRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testDeleteRoute() {

    }

    @Test
    void testSaveRoute() {

    }

    @Test
    void testViewRouteDetails() throws Exception {
        Route r1 = new Route("(52.3676,4.9041)", "(49.2827,-123.1207)", "Amsterdam", "Vancouver", "flying");
        Route r2 = new Route("(51.1657,10.4515)", "(50.5039,4.4699)", "Germany", "Belgium", "driving");
        Route r3 = new Route("(49.2628,-122.7811)", "(49.2849,-122.8678)", "Port Coquitlam", "Port Moody", "bicycling");

        List<Route> routes = new ArrayList<Route>();
        routes.add(r1);
        routes.add(r2);
        routes.add(r3);

        when(routeRepository.findById(0)).thenReturn(routes.get(0));
        when(routeRepository.findById(1)).thenReturn(routes.get(1));
        when(routeRepository.findById(2)).thenReturn(routes.get(2));

        mockMvc.perform(MockMvcRequestBuilders.get("/route-details/0"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("route-details"))

            .andExpect(MockMvcResultMatchers.model().attribute("route", is(
                allOf(
                    hasProperty("startCoords", Matchers.is("(52.3676,4.9041)")),
                    hasProperty("endCoords", Matchers.is("(49.2827,-123.1207)")),
                    hasProperty("startPoint", Matchers.is("Amsterdam")),
                    hasProperty("endPoint", Matchers.is("Vancouver")),
                    hasProperty("travelMode", Matchers.is("flying"))
                )
            )));

            mockMvc.perform(MockMvcRequestBuilders.get("/route-details/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("route-details"))

            .andExpect(MockMvcResultMatchers.model().attribute("route", is(
                allOf(
                    hasProperty("startCoords", Matchers.is("(51.1657,10.4515)")),
                    hasProperty("endCoords", Matchers.is("(50.5039,4.4699)")),
                    hasProperty("startPoint", Matchers.is("Germany")),
                    hasProperty("endPoint", Matchers.is("Belgium")),
                    hasProperty("travelMode", Matchers.is("driving"))
                )
            )));

            mockMvc.perform(MockMvcRequestBuilders.get("/route-details/2"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("route-details"))

            .andExpect(MockMvcResultMatchers.model().attribute("route", is(
                allOf(
                    hasProperty("startCoords", Matchers.is("(49.2628,-122.7811)")),
                    hasProperty("endCoords", Matchers.is("(49.2849,-122.8678)")),
                    hasProperty("startPoint", Matchers.is("Port Coquitlam")),
                    hasProperty("endPoint", Matchers.is("Port Moody")),
                    hasProperty("travelMode", Matchers.is("bicycling"))
                )
            )));
    }

    @Test
    void testViewSavedRoutes() throws Exception {
        Route r1 = new Route("(52.3676,4.9041)", "(49.2827,-123.1207)", "Amsterdam", "Vancouver", "flying");
        Route r2 = new Route("(51.1657,10.4515)", "(50.5039,4.4699)", "Germany", "Belgium", "driving");
        Route r3 = new Route("(49.2628,-122.7811)", "(49.2849,-122.8678)", "Port Coquitlam", "Port Moody", "bicycling");

        List<Route> routes = new ArrayList<Route>();
        routes.add(r1);
        routes.add(r2);
        routes.add(r3);

        when(routeRepository.findAll()).thenReturn(routes);

        mockMvc.perform(MockMvcRequestBuilders.get("/saved-routes"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("saved-routes"))

            .andExpect(MockMvcResultMatchers.model().attribute("routes", hasItem(
                allOf(
                    hasProperty("startCoords", Matchers.is("(52.3676,4.9041)")),
                    hasProperty("endCoords", Matchers.is("(49.2827,-123.1207)")),
                    hasProperty("startPoint", Matchers.is("Amsterdam")),
                    hasProperty("endPoint", Matchers.is("Vancouver")),
                    hasProperty("travelMode", Matchers.is("flying"))
                )
            )))
            .andExpect(MockMvcResultMatchers.model().attribute("routes", hasItem(
                allOf(
                    hasProperty("startCoords", Matchers.is("(51.1657,10.4515)")),
                    hasProperty("endCoords", Matchers.is("(50.5039,4.4699)")),
                    hasProperty("startPoint", Matchers.is("Germany")),
                    hasProperty("endPoint", Matchers.is("Belgium")),
                    hasProperty("travelMode", Matchers.is("driving"))
                )
            )))
            .andExpect(MockMvcResultMatchers.model().attribute("routes", hasItem(
                allOf(
                    hasProperty("startCoords", Matchers.is("(49.2628,-122.7811)")),
                    hasProperty("endCoords", Matchers.is("(49.2849,-122.8678)")),
                    hasProperty("startPoint", Matchers.is("Port Coquitlam")),
                    hasProperty("endPoint", Matchers.is("Port Moody")),
                    hasProperty("travelMode", Matchers.is("bicycling"))
                )
            )));
    }   

}
