// Java file for the users controller

package com.genie.journey_genie.controllers;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.genie.journey_genie.models.Preferences;
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
  
    private boolean isUserLoggedIn(HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        return user != null;
    }

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
            return "preferences";
        }
    }

    @PostMapping("/save-preferences/{id}")
    public void savePreferences(@RequestParam Map<String, String> newPreferences, @PathVariable int id, HttpServletResponse response) {
        int duration = Integer.parseInt(newPreferences.get("duration"));
        boolean tolls = Boolean.parseBoolean(newPreferences.get("tolls"));
        String location = newPreferences.get("location");
        float range = Float.parseFloat(newPreferences.get("range"));
        String interests = newPreferences.get("interests");
        Preferences preferences = new Preferences(duration, tolls, location, range, interests);

        System.out.println(preferences.getDuration());
        System.out.println(preferences.allowTolls());
        System.out.println(preferences.getLocation());
        System.out.println(preferences.getRange());
        System.out.println(preferences.getInterests());

        User user = repo.findByUserID(id);
        user.setPreferences(preferences);
        repo.save(user);
        response.setStatus(201);
    }

    @GetMapping("/preferences/{id}")
    public String preferences(Model model, @PathVariable int id, HttpSession session, HttpServletResponse response) {
        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return "loginPage";
        }
        User user = repo.findByUserID(id);
        model.addAttribute("user", user);
        return "preferences";
    }

    
}
