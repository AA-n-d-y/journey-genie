package com.genie.journey_genie.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import com.genie.journey_genie.models.User;

@Controller
public class BudgetController {

    @Value("${OPENCURRENCY_API_KEY}")
    private String openCurrencyApiKey;

    private boolean isUserLoggedIn(HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        return user != null;
    }

    @GetMapping("/track-budget")
    public String showBudgetPage(Model model, HttpSession session) {
        if (!isUserLoggedIn(session)) {
            return "redirect:/login";
        }
        model.addAttribute("OPENCURRENCY_API_KEY", openCurrencyApiKey);
        return "track-budget";
    }
}
