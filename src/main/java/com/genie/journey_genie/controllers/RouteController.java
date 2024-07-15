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

import java.util.Arrays;
import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

    // Holding the API key value
    @Value("${API_KEY}")
    private String API_KEY;

    @GetMapping("/saved-routes")
    public String viewSavedRoutes(Model model) {
        List<Route> routes = routeRepository.findAll();
        model.addAttribute("routes", routes);
        return "saved-routes";
    }

    @GetMapping("/route-details/{id}")
    public String viewRouteDetails(@PathVariable Long id, Model model) {
        Route route = routeRepository.findById(id).orElse(null);
        if (route != null && route.getCoords().length > 2) {
            String[] subArr = Arrays.copyOfRange(route.getCoords(), 1 , route.getCoords().length-2);
            model.addAttribute("subArr", subArr);
        }
        model.addAttribute("route", route);
        model.addAttribute("API_KEY", API_KEY);
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
            @RequestParam String[] coords,
            @RequestParam String[] points,
            @RequestParam String travelMode) {

        Route route = new Route(coords, points, travelMode);
        routeRepository.save(route);
        return "redirect:/saved-routes";
    }
}
