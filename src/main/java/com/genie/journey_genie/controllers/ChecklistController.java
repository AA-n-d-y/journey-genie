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

//import com.genie.journey_genie.models.Checklist;
import java.util.List;
import java.util.Map;

@Controller
public class ChecklistController {
    // @Autowired
    // private RouteController routeController;
    @Autowired
    private Route2Repository routeRepository;
    @Autowired
    private ChecklistRepository checklistRepository;

    @GetMapping("/makeChecklist/checklist/{id}")
    public String displayChecklist(Model model, @PathVariable Long id) {
        Route2 routeForAssocChecklist = routeRepository.findById(id).orElse(null);
        List<String> activities = routeForAssocChecklist.getChecklist().getActivities();
        model.addAttribute("act", activities);
        return "checklist";
    }

    @GetMapping("/makeChecklist/{id}")
    public String makeChecklist(Model model, @PathVariable Long id) {
        Route2 routeForAssocChecklist = routeRepository.findById(id).orElse(null);
        model.addAttribute("route", routeForAssocChecklist);
        return "makeChecklist";
    }

    @PostMapping("/makeChecklist/{id}")
    public String addToChecklist(@RequestParam Map<String, String> newChecklist, @PathVariable Long id) {
        String activity = newChecklist.get("activity");
        Route2 routeForAssocChecklist = routeRepository.findById(id).orElse(null);
        
        if(routeForAssocChecklist.getChecklist() == null){
            routeForAssocChecklist.setChecklist(new Checklist());
        }
        Checklist checklist = routeForAssocChecklist.getChecklist(); // Get the Checklist object from the Route object
        checklist.getActivities().add(activity); // Add the activity to the Checklist object
        checklist = checklistRepository.save(checklist); // Save the Checklist object

        
        routeForAssocChecklist.setChecklist(checklist);


        routeRepository.save(routeForAssocChecklist);

        return "redirect:/makeChecklist/" + id;
    }

    // @GetMapping("/viewChecklist/{id}")
    // public String viewChecklist(@PathVariable Long id){
    //     RouteController routeController = new RouteController();
    //     List<Route> routes = routeController.getRouteRepository().findAll();
        
    //     for (Route route : routes) {
    //         if(route.getId() == id){
    //             Route routeForAssocChecklist = route;
    //             return "redirect:/checklist";
    //         }
    //     }
    //     return "";
    // }
}
