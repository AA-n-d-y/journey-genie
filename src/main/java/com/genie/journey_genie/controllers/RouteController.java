package com.genie.journey_genie.controllers;

import com.genie.journey_genie.models.Route;
import com.genie.journey_genie.models.RouteRepository;
import com.genie.journey_genie.models.Note;
import com.genie.journey_genie.models.NoteRepository;
import com.genie.journey_genie.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.factory.annotation.Value;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

    private boolean isUserLoggedIn(HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        return user != null;
    }

    @GetMapping("/saved-routes")
    public String viewSavedRoutes(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return "loginPage";
        }
        List<Route> routes = routeRepository.findAll();
        model.addAttribute("routes", routes);
        return "saved-routes";
    }

    @GetMapping("/route-details/{id}")
    @Transactional(readOnly = true)
    public String viewRouteDetails(@PathVariable Long id, Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return "loginPage";
        }
        Route route = routeRepository.findById(id).orElse(null);
        List<Note> notes = noteRepository.findByRouteId(id);
        model.addAttribute("route", route);
        model.addAttribute("notes", notes);
        model.addAttribute("API_KEY", API_KEY);
        return "route-details";
    }

    @GetMapping("/delete-route/{id}")
    @Transactional
    public RedirectView deleteRoute(@PathVariable Long id, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return new RedirectView("/loginPage");
        }
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
            @RequestParam String routeDetails,
            HttpServletResponse response, HttpServletRequest request, HttpSession session) {

        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return "loginPage";
        }

        Route route = new Route(startCoords, endCoords, startPoint, endPoint, travelMode, routeDetails);
        routeRepository.save(route);
        return "redirect:/saved-routes";
    }

    @GetMapping("/add-note/{routeId}")
    public String addNotePage(@PathVariable Long routeId, Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return "loginPage";
        }
        model.addAttribute("routeId", routeId);
        return "add_note";
    }

    @PostMapping("/save-note")
    @Transactional
    public RedirectView saveNote(
            @RequestParam Long routeId,
            @RequestParam String noteHeadline,
            @RequestParam String noteContent,
            HttpServletResponse response, HttpServletRequest request, HttpSession session) {

        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return new RedirectView("/loginPage");
        }

        Route route = routeRepository.findById(routeId).orElse(null);
        if (route != null) {
            Note note = new Note();
            note.setRoute(route);
            note.setContent("<h2>" + noteHeadline + "</h2><p>" + noteContent + "</p>");
            noteRepository.save(note);
        }
        return new RedirectView("/route-details/" + routeId);
    }

    @GetMapping("/edit-note/{noteId}")
    public String editNotePage(@PathVariable Long noteId, Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return "loginPage";
        }
        Note note = noteRepository.findById(noteId).orElse(null);
        model.addAttribute("note", note);
        return "edit_note";
    }

    @PostMapping("/update-note")
    @Transactional
    public RedirectView updateNote(
            @RequestParam Long noteId,
            @RequestParam String noteHeadline,
            @RequestParam String noteContent,
            HttpServletResponse response, HttpServletRequest request, HttpSession session) {

        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return new RedirectView("/loginPage");
        }

        Note note = noteRepository.findById(noteId).orElse(null);
        if (note != null) {
            note.setContent("<h2>" + noteHeadline + "</h2><p>" + noteContent + "</p>");
            noteRepository.save(note);
        }
        return new RedirectView("/route-details/" + note.getRoute().getId());
    }

    @GetMapping("/delete-note/{noteId}")
    @Transactional
    public RedirectView deleteNote(@PathVariable Long noteId, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return new RedirectView("/loginPage");
        }
        Note note = noteRepository.findById(noteId).orElse(null);
        Long routeId = note.getRoute().getId();
        noteRepository.deleteById(noteId);
        return new RedirectView("/route-details/" + routeId);
    }
}
