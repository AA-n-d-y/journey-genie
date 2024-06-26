package com.genie.journey_genie.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.genie.journey_genie.model.User;
import com.genie.journey_genie.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository repo;
    
    @PostMapping("/register")
    public String registerUser(@RequestParam Map<String, String> newuser){
        String username = newuser.get("username");
        String password = newuser.get("password");
        //String type = newuser.get("type");
        repo.save(new User(username, password, "user"));
        return "redirect:/";
    }
}
