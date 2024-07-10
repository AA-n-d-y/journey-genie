package com.genie.journey_genie.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.genie.journey_genie.models.*;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class MapController {
    // Holding the API key value
    @Value("${GOOGLE_API_KEY}")
    private String GOOGLE_API_KEY;

    @GetMapping("/map")
    public String showMap(Model model, HttpServletResponse response, HttpSession session) {
        // Finding the session
        User user = (User) session.getAttribute("sessionUser");

        // If it does not exist, redirect them to the login page
        if (user == null) {
            response.setStatus(401);
            return "loginPage";
        }
        
        // Else
        model.addAttribute("GOOGLE_API_KEY", GOOGLE_API_KEY);
        response.setStatus(200);
        return "index";
    }
}
