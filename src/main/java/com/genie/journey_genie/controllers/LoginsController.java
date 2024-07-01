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
    public String displayLogin(HttpServletResponse response) {
        response.setStatus(200);
        return "loginPage";
    }

    
    // Get request (displaying the user page)
    @GetMapping("/home")
    public String displayUserPageG(HttpServletResponse response){
        // DO SOMETHING HERE FOR LOGINS
        response.setStatus(200);
        return "loginPage";
    }
    

    // Post request (displaying the user page after attempting to log in)
    @PostMapping("/home")
    public String displayUserPage(@RequestParam Map<String, String> loginForm, Model model, HttpServletResponse response) {
        try {
            // Getting the user
            List<User> user = repo.findByUsernameAndPassword(loginForm.get("username"), loginForm.get("password"));

            // If the user exists
            if (user != null) {
                model.addAttribute("user", user.get(0));
                response.setStatus(200);
                return "userPage";
            }

            // Else
            else {
                response.setStatus(401);
                return "loginPage";
            }
        }
        catch(Exception obj) {
            response.setStatus(401);
            return "loginPage";
        }
    }
    

    // Get request (displaying login page after logging out)
    @GetMapping("/logout")
    public String displayLogout(HttpServletResponse response) {
        // DO SOMETHING HERE WHEN LOGGING OUT
        response.setStatus(200);

        return "loginPage";
    }

}
