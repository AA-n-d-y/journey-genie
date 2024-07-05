package com.genie.journey_genie.controllers;

// import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {
    // Holding the API key value
    // @Value("${API_KEY}")
    // private String API_KEY;

    @GetMapping("/map")
    public String showMap(Model model) {
        // model.addAttribute("API_KEY", API_KEY);
        return "index";
    }
}
