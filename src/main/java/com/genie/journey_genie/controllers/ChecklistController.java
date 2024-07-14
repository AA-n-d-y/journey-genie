package com.genie.journey_genie.controllers;

import org.hibernate.annotations.Check;
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

    @GetMapping("/checklist/{id}")
    public String displayChecklist(Model model, @PathVariable Long id) {
        Route2 routeForAssocChecklist = routeRepository.findById(id).orElse(null);
        model.addAttribute("route", routeForAssocChecklist);
        return "checklist";
    }

    @GetMapping("/makeChecklist/{id}")
    public String makeChecklist(Model model, @PathVariable Long id) {
        Route2 routeForAssocChecklist = routeRepository.findById(id).orElse(null);
        model.addAttribute("route", routeForAssocChecklist);
        return "makeChecklist";
    }

    @PostMapping("/makeChecklist/{id}")
    public String saveChecklist(@RequestParam Map<String, String> newChecklist, @PathVariable Long id) {
        String activity = newChecklist.get("activity");
        Checklist checklist = new Checklist(activity);
        checklist = checklistRepository.save(checklist); // Save the Checklist object

        Route2 routeForAssocChecklist = routeRepository.findById(id).orElse(null);
        routeForAssocChecklist.setChecklist(checklist);

        System.out.println("TEST");
        System.out.println(routeForAssocChecklist.getChecklist().getActivities());
        System.out.println("TEST");

        routeRepository.save(routeForAssocChecklist);

        return "redirect:/checklist/{id}";
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
