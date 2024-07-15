// Java file for error controller

package com.genie.journey_genie.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;


@Controller 
public class ErrorsController {

    // Get request (displaying an error page if the endpoint does not exist)
    @GetMapping("/*")    
    public String displayError(HttpServletResponse response) {
        response.setStatus(404);
        return "errorPage";
    }

}