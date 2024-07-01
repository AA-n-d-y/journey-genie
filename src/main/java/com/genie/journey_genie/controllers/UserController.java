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

@Controller
public class UserController {
    // Creating the repository object
    @Autowired
    private UserRepository repo;
  
    // Get request (displaying registration page)
    @GetMapping("/register")
    public String displayRegistration() {
        return "register";
    }


    // Post request (creating a new user)
    @PostMapping("/register")
    public String registerUser(@RequestParam Map<String, String> newUser, Model model){

        // Creating a new user
        String firstname = newUser.get("firstname");
        String lastname = newUser.get("lastname");
        String username = newUser.get("username");
        String password = newUser.get("password");
        String email = newUser.get("email");
        String type = newUser.get("type");
        repo.save(new User(firstname, lastname, username, password, email, type));

        // Finding all the users and rendering the admin page
        List<User> users = repo.findAll();
        model.addAttribute("users", users);
        return "adminPage";
    }


    // Get request (displaying the admin page)
    @GetMapping("/admin")
    public String getAllUsers(Model model){
        // Finding all the users and rendering the admin page
        List<User> users = repo.findAll();
        model.addAttribute("users", users);
        return "adminPage";
    }

}
