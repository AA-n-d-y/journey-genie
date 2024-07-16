// Java file for testing the user controller

package com.genie.journey_genie.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;

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


    /// Testing our functionality

    // Testing our controller
    @Test
    void controllerTest() throws Exception {
        assertThat(controller).isNotNull();
    }


    // Getting the registration page when logged out
    @Test
    void registrationPageTest() throws Exception {

    }

    // Getting the registration page when logged in as a user
    @Test
    void registrationPageUTest() throws Exception {

    }

    // Getting the registration page when logged in as an admin
    @Test
    void registrationPageATest() throws Exception {

    }


    // Testing creating a new user
    @Test
    void registrationTest() throws Exception {

    }

    // Testing creating a duplicate user
    @Test
    void registrationDuplicateTest() throws Exception {

    }

}
