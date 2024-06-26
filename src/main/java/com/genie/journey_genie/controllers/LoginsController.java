package com.genie.journey_genie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.genie.journey_genie.models.*;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;
import org.springframework.ui.Model;

import java.util.InputMismatchException;
import java.util.List;


@Controller
public class LoginsController {
    // Creating the repository object
    @Autowired
    private UserRepository repo;

    // Get request (displaying login page)
    @GetMapping("/login")
    public String displayLogin() {
        return "loginPage";
    }

    // Get request (displaying user page)
    @GetMapping("/home")
    public String displayUserPage(@RequestParam Map<String, String> data, Model model) {
        User user = new User("Bob", "John", "bobJohn", "hello");
        model.addAttribute("user", user);
        return "userPage";
    }
    
}
