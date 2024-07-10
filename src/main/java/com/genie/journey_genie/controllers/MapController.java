package com.genie.journey_genie.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.genie.journey_genie.models.User;

@Controller
public class MapController {
    // Holding the API key value
    @Value("${API_KEY}")
    private String API_KEY;

    private boolean isUserLoggedIn(HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        return user != null;
    }

    @GetMapping("/map")
    public String showMap(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return "loginPage";
        }
        model.addAttribute("API_KEY", API_KEY);
        return "index";
    }
}
