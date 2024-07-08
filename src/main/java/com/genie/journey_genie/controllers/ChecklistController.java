package com.genie.journey_genie.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChecklistController {
    @GetMapping("/checklist")
    public String displayChecklist() {
        return "checklist";
    }
}
