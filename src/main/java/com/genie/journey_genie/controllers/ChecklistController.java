package com.genie.journey_genie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.genie.journey_genie.models.Checklist;
import com.genie.journey_genie.models.ChecklistRepository;
import com.genie.journey_genie.models.Route2;
import com.genie.journey_genie.models.Route2Repository;
import com.genie.journey_genie.models.User;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class ChecklistController {
    // @Autowired
    // private RouteController routeController;
    @Autowired
    private Route2Repository routeRepository;
    @Autowired
    private ChecklistRepository checklistRepository;


    // Function for login checks
    private boolean isUserLoggedIn(HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        return user != null;
    }


    // Get request (getting the checklist page)
    @GetMapping("/makeChecklist/checklist/{id}")
    public String displayChecklist(Model model, @PathVariable Long id, HttpServletResponse response, HttpSession session) {
        // If not logged in
        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return "loginPage";
        }

        // Else
        Route2 routeForAssocChecklist = routeRepository.findById(id).orElse(null);


        // If checklist is null
        if (routeForAssocChecklist.getChecklist() == null) {
            model.addAttribute("checklistID", id);
            response.setStatus(200);
            return "checklist";
        }

        // Else
        model.addAttribute("checklistID", id);
        model.addAttribute("activities", routeForAssocChecklist.getChecklist().getActivities());
        model.addAttribute("places", routeForAssocChecklist.getChecklist().getPlaces());
        response.setStatus(200);
        return "checklist";
    }


    // Get request (getting the Add Items to Checklist page)
    @GetMapping("/makeChecklist/{id}")
    public String makeChecklist(Model model, @PathVariable Long id, HttpServletResponse response, HttpSession session) {
        // If not logged in
        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return "loginPage";
        }

        // Else
        Route2 routeForAssocChecklist = routeRepository.findById(id).orElse(null);
        model.addAttribute("route", routeForAssocChecklist);
        response.setStatus(200);
        return "makeChecklist";
    }


    // Post request (creating a checklist/adding activities to the checklist) 
    @PostMapping("/makeChecklist/{id}")
    public String addToChecklist(@RequestParam Map<String, String> newChecklist, @PathVariable Long id, HttpServletResponse response, HttpSession session) {
        // If not logged in
        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return "loginPage";
        }

        // Else
        Route2 route = routeRepository.findById(id).orElse(null);

        // If checklist isn't null
        if (route.getChecklist() != null) {
            route.getChecklist().getActivities().add(newChecklist.get("activity"));
            checklistRepository.save(route.getChecklist()); // Save the Checklist object
            route.setChecklist(route.getChecklist());
            routeRepository.save(route); // Save the route
        }
        // Else create a new checklist
        else {
            route.setChecklist(new Checklist()); // Create a new checklist
            route.getChecklist().getActivities().add(newChecklist.get("activity"));
            checklistRepository.save(route.getChecklist()); // Save the Checklist object
            route.setChecklist(route.getChecklist());
            routeRepository.save(route); // Save the route
        }
        response.setStatus(201);
        return "redirect:/makeChecklist/" + id;
    }


    // Post request (adding a place to the checklist)
    @PostMapping("/addPlace/{id}")
    public String addPlaceToChecklist(@RequestParam Map<String, String> place, @PathVariable Long id, HttpServletResponse response, HttpSession session) {
        // If not logged in
        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return "loginPage";
        }

        // Else
        Route2 route = routeRepository.findById(id).orElse(null);

        // If checklist isn't null
        if (route.getChecklist() != null) {
            route.getChecklist().getPlaces().add(place.get("placeTitle"));
            checklistRepository.save(route.getChecklist()); // Save the Checklist object
            route.setChecklist(route.getChecklist());
            routeRepository.save(route); // Save the route
        }
        // Else create a new checklist
        else {
            route.setChecklist(new Checklist()); // Create a new checklist
            route.getChecklist().getPlaces().add(place.get("placeTitle"));
            checklistRepository.save(route.getChecklist()); // Save the Checklist object
            route.setChecklist(route.getChecklist());
            routeRepository.save(route); // Save the route
        }
        
        response.setStatus(201);
        return "redirect:/route-details/" + id;
    }
 
    
    // Post request (deleting an activity from the checklist)
    @PostMapping("/deleteActivity/{id}")
    public String deleteActivity(@RequestParam Map<String, String> activity, @PathVariable Long id, HttpServletResponse response, HttpSession session) {
        // If not logged in
        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return "loginPage";
        }

        // Else
        Route2 route = routeRepository.findById(id).orElse(null); // Finding the route
        route.getChecklist().getActivities().remove(activity.get("activityTitle")); // Removing the activity
        checklistRepository.save(route.getChecklist()); // Saving the checklist
        route.setChecklist(route.getChecklist());
        routeRepository.save(route); // Saving the route
        response.setStatus(200);
        return "redirect:/makeChecklist/checklist/" + id;
    }


    // Post request (deleting a place from the checklist)
    @PostMapping("/deletePlace/{id}")
    public String deletePlace(@RequestParam Map<String, String> place, @PathVariable Long id, HttpServletResponse response, HttpSession session) {
        // If not logged in
        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return "loginPage";
        }

        // Else
        Route2 route = routeRepository.findById(id).orElse(null); // Finding the route
        route.getChecklist().getPlaces().remove(place.get("placeTitle")); // Removing the place
        checklistRepository.save(route.getChecklist()); // Saving the checklist
        route.setChecklist(route.getChecklist());
        routeRepository.save(route); // Saving the route
        response.setStatus(200);
        return "redirect:/makeChecklist/checklist/" + id;
    }
    
}
