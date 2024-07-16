// Java file for the users controller

package com.genie.journey_genie.controllers;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.genie.journey_genie.models.User;
import com.genie.journey_genie.models.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    // Creating the repository object
    @Autowired
    private UserRepository repo;
  
    // Get request (displaying registration page)
    @GetMapping("/register")
    public String displayRegistration(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        // Finding the session
        User user = (User) session.getAttribute("sessionUser");

        // If user is null, return the registration page
        if (user == null) {
            response.setStatus(200);
            return "register";
        }

        // Else return the home page
        else if (user.getType().toLowerCase().equals("admin")) {
            // Finding all the users and rendering the admin page
            List<User> users = repo.findAll();
            model.addAttribute("users", users);
            response.setStatus(401);
            return "adminPage";
        }
        else {
            model.addAttribute("user", user);
            response.setStatus(401);
            return "userPage";
        }
        
    }


    // Post request (creating a new user)
    @PostMapping("/register")
    public String registerUser(@RequestParam Map<String, String> newUser, Model model, HttpServletResponse response){
        // NEED TO ACCOUNT FOR A DUPLICATE USER

        // Creating a new user
        String firstname = newUser.get("firstname");
        String lastname = newUser.get("lastname");
        String username = newUser.get("username");
        String password = newUser.get("password");
        String email = newUser.get("email");
        String type = newUser.get("type");

        // If the user exists already
        if (repo.findByUsername(username).size() > 0) {
            response.setStatus(409);
            return "userExists";
        }
        // Else
        else {
            repo.save(new User(firstname, lastname, username, password, email, type));
            response.setStatus(201);
            return "loginPage";
        }
    }

}
