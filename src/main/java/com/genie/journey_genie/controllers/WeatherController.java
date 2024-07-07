package com.genie.journey_genie.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    @Value("${WEATHER_API_KEY}")
    private String weatherApiKey;

    @GetMapping("/weather")
    public String showWeatherDashboard(Model model) {
        model.addAttribute("weatherApiKey", weatherApiKey);
        return "weather";
    }
}
