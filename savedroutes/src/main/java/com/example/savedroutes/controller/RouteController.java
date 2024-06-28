package com.example.savedroutes.controller;

import com.example.savedroutes.model.Route;
import com.example.savedroutes.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

    @GetMapping("/saved-routes")
    public String viewSavedRoutes(Model model) {
        List<Route> routes = routeRepository.findAll();
        model.addAttribute("routes", routes);
        return "saved-routes";
    }

    @GetMapping("/route-details/{id}")
    public String viewRouteDetails(@PathVariable Long id, Model model) {
        Route route = routeRepository.findById(id).orElse(null);

        if (route.getStartPoint().contains("#")) {
            int index = route.getStartPoint().indexOf("#");
            StringBuilder startPoint = new StringBuilder(route.getStartPoint());
            startPoint = startPoint.delete(index, index+6);
            route.setStartPoint(startPoint.toString());
        }   
        route.setStartPoint(route.getStartPoint().replace(", ", "+"));
        route.setStartPoint(route.getStartPoint().replace(" ", "+"));

        if (route.getEndPoint().contains("#")) {
            int index = route.getEndPoint().indexOf("#");
            StringBuilder endPoint = new StringBuilder(route.getEndPoint());
            endPoint = endPoint.delete(index, index+6);
            route.setEndPoint(endPoint.toString());
        }
        route.setEndPoint(route.getEndPoint().replace(", ", "+"));
        route.setEndPoint(route.getEndPoint().replace(" ", "+"));

        model.addAttribute("route", route);
        return "route-details";
    }

    @PostMapping("/save-route")
    public String saveRoute(
            @RequestParam String startPoint,
            @RequestParam String endPoint,
            @RequestParam String travelMode,
            @RequestParam String routeDetails) {
        Route route = new Route(startPoint, endPoint, travelMode, routeDetails);
        routeRepository.save(route);
        return "redirect:/saved-routes";
    }
}
