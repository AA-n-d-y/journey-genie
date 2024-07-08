package com.genie.journey_genie.controllers;

import com.genie.journey_genie.services.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatGPTController {

    @Autowired
    private ChatGPTService chatGPTService;

    @GetMapping("/chat")
    public String showChatPage() {
        return "chat";
    }

    @PostMapping("/chat")
    public String getChatResponse(@RequestParam("prompt") String prompt, Model model) {
        String response = chatGPTService.getChatGPTResponse(prompt);
        model.addAttribute("prompt", prompt);
        model.addAttribute("response", response);
        return "chat";
    }
}
