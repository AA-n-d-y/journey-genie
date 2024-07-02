package com.genie.journey_genie.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @GetMapping("/map")
    public String showMap(Model model) {
        return "index";
    }
}
