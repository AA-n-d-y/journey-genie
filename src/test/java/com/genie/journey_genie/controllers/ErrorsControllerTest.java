// Java file for testing the errors controller

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
import java.util.List;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class ErrorsControllerTest {

    /// Setup

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")); 

    // Mock of our server
    @Autowired
    MockMvc mockMvc;

    // Controller
    @Autowired
    private ErrorsController controller;


    /// Testing our functionality

    // Testing our controller
    @Test
    void controllerTest() throws Exception {
        assertThat(controller).isNotNull();
    }


    // Testing a get request to a route that doesn't exist
    @Test
    void errorTest() throws Exception {
        // Mock the get request
        mockMvc.perform(MockMvcRequestBuilders.get("/*"))

            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.view().name("errorPage"));

            
        mockMvc.perform(MockMvcRequestBuilders.get("/routeThatDoesNotExist"))

            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.view().name("errorPage"));    
    }

}
