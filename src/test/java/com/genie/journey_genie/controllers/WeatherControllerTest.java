package com.genie.journey_genie.controllers;

import com.genie.journey_genie.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WeatherControllerTest {

    @Mock
    private HttpSession session;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Model model;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowWeatherDashboard_UserNotLoggedIn() {
        // Mock the session to return null for sessionUser
        when(session.getAttribute("sessionUser")).thenReturn(null);

        // Call the controller method
        String viewName = weatherController.showWeatherDashboard(model, response, request, session);

        // Verify the view name
        assertEquals("loginPage", viewName);

        // Verify the response status
        verify(response).setStatus(401);
    }

}
