// Java file for the logins controller

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

    
    // Get request (displaying registration page)
    @GetMapping("register")
    public String displayRegistration() {
        // NEED TO ADD THE PAGE

        return " ";
    }

    
    // Get request (displaying the login page)
    @GetMapping("/home")
    public String displayUserPageG(){
        return "loginPage";
    }
    

    // Post request (displaying page after attempting to log in)
    @PostMapping("/home")
    public String displayUserPage(@RequestParam Map<String, String> loginForm, Model model) {
        try {
            // Getting the user
            List<User> user = repo.findByUsernameAndPassword(loginForm.get("username"), loginForm.get("password"));

            // If the user exists
            if (user != null) {
                model.addAttribute("user", user.get(0));
                return "userPage";
            }

            // Else
            else {
                return "loginPage";
            }
        }
        catch(Exception obj) {
            return "loginPage";
        }
    }
    

    // Get request (displaying login page after logging out)
    @GetMapping("/logout")
    public String displayLogout() {
        // DO SOMETHING HERE WHEN LOGGING OUT


        return "loginPage";
    }



}
