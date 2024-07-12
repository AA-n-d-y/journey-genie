package com.genie.journey_genie.controllers;

import com.genie.journey_genie.models.Route2;
import com.genie.journey_genie.models.Route2Repository;
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
    private Route2Repository route2Repository;

    @Autowired
    private NoteRepository noteRepository;

    @Value("${GOOGLE_API_KEY}")
    private String GOOGLE_API_KEY;

    private boolean isUserLoggedIn(HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        return user != null;
    }

    private User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute("sessionUser");
    }

    @GetMapping("/saved-routes")
    public String viewSavedRoutes(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return "loginPage";
        }
        User user = getLoggedInUser(session);
        List<Route2> routes = route2Repository.findByUser(user);
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
        Route2 route = route2Repository.findById(id).orElse(null);
        List<Note> notes = noteRepository.findByRouteId(id);
        model.addAttribute("route", route);
        model.addAttribute("notes", notes);
        model.addAttribute("GOOGLE_API_KEY", GOOGLE_API_KEY);
        return "route-details";
    }

    @GetMapping("/delete-route/{id}")
    @Transactional
    public RedirectView deleteRoute(@PathVariable Long id, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return new RedirectView("/loginPage");
        }
        route2Repository.deleteById(id);
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
            HttpServletResponse response, HttpServletRequest request, HttpSession session) {

        if (!isUserLoggedIn(session)) {
            response.setStatus(401); // Unauthorized
            return "loginPage";
        }

        User user = getLoggedInUser(session);
        Route2 route = new Route2(startCoords, endCoords, startPoint, endPoint, travelMode, user);
        route2Repository.save(route);
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

        Route2 route = route2Repository.findById(routeId).orElse(null);
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
