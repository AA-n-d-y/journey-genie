package com.genie.journey_genie.controllers;

import com.genie.journey_genie.models.Route;
import com.genie.journey_genie.models.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

    // Holding the API key value
    @Value("${GOOGLE_API_KEY}")
    private String GOOGLE_API_KEY;

    @GetMapping("/saved-routes")
    public String viewSavedRoutes(Model model) {
        List<Route> routes = routeRepository.findAll();
        model.addAttribute("routes", routes);
        return "saved-routes";
    }

    @GetMapping("/route-details/{id}")
    public String viewRouteDetails(@PathVariable Long id, Model model) {
        Route route = routeRepository.findById(id).orElse(null);
        model.addAttribute("route", route);
        model.addAttribute("GOOGLE_API_KEY", GOOGLE_API_KEY);
        return "route-details";
    }

    @GetMapping("/delete-route/{id}")
    public RedirectView deleteRoute(@PathVariable Long id) {
        routeRepository.deleteById(id);
        return new RedirectView("/saved-routes");
    }

    @PostMapping("/save-route")
    public String saveRoute(
            Model model,
            @RequestParam String startCoords,
            @RequestParam String endCoords,
            @RequestParam String startPoint,
            @RequestParam String endPoint,
            @RequestParam String travelMode,
            @RequestParam String routeDetails) {

        Route route = new Route(startCoords, endCoords, startPoint, endPoint, travelMode, routeDetails);
        routeRepository.save(route);
        return "redirect:/saved-routes";
    }
}
