package com.genie.journey_genie.controllers;

import com.genie.journey_genie.models.Route;
import com.genie.journey_genie.models.RouteRepository;
import com.genie.journey_genie.models.Note;
import com.genie.journey_genie.models.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private NoteRepository noteRepository;

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
    @Transactional(readOnly = true)
    public String viewRouteDetails(@PathVariable Long id, Model model) {
        Route route = routeRepository.findById(id).orElse(null);
        List<Note> notes = noteRepository.findByRouteId(id);
        model.addAttribute("route", route);
        model.addAttribute("notes", notes);
        model.addAttribute("API_KEY", API_KEY);
        return "route-details";
    }

    @GetMapping("/delete-route/{id}")
    @Transactional
    public RedirectView deleteRoute(@PathVariable Long id) {
        routeRepository.deleteById(id);
        return new RedirectView("/saved-routes");
    }

    @PostMapping("/save-route")
    @Transactional
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

    @GetMapping("/add-note/{routeId}")
    public String addNotePage(@PathVariable Long routeId, Model model) {
        model.addAttribute("routeId", routeId);
        return "add_note";
    }

    @PostMapping("/save-note")
    @Transactional
    public RedirectView saveNote(
            @RequestParam Long routeId,
            @RequestParam String noteHeadline,
            @RequestParam String noteContent) {

        Route route = routeRepository.findById(routeId).orElse(null);
        if (route != null) {
            Note note = new Note();
            note.setRoute(route);
            note.setContent("<h2>" + noteHeadline + "</h2><p>" + noteContent + "</p>");
            noteRepository.save(note);
        }
        return new RedirectView("/route-details/" + routeId);
    }
}
