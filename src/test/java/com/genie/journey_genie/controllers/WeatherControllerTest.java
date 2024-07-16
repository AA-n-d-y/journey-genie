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

    @Test
    public void testShowWeatherDashboard_UserLoggedIn() throws Exception {
        // Mock the session to return a user for sessionUser
        User user = new User("John", "Doe", "johndoe", "password", "johndoe@example.com", "user");
        when(session.getAttribute("sessionUser")).thenReturn(user);

        // Use reflection to set the private weatherApiKey field
        String weatherApiKey = "mockApiKey";
        Field weatherApiKeyField = WeatherController.class.getDeclaredField("weatherApiKey");
        weatherApiKeyField.setAccessible(true);
        weatherApiKeyField.set(weatherController, weatherApiKey);

        // Call the controller method
        String viewName = weatherController.showWeatherDashboard(model, response, request, session);

        // Verify the view name
        assertEquals("weather", viewName);

        // Verify the model attributes
        verify(model).addAttribute("weatherApiKey", weatherApiKey);

        // Verify the response status is not set to 401
        verify(response, never()).setStatus(401);
    }
}
