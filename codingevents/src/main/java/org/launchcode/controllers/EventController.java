package org.launchcode.controllers;

import jakarta.validation.Valid;
import org.launchcode.data.EventData;
import org.launchcode.models.Event;
import org.launchcode.models.EventType;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

@GetMapping
    public String displayAllEvents(Model model) {
//    events.put("Menteaship","A fun meetup for connecting with mentors");
//    events.put("Code With Pride","A fun meetup sponsored by LaunchCode");
//    events.put("Javascripty", "An imaginary meetup for Javascript developers");

    model.addAttribute("title", "All Events");

    model.addAttribute("events", EventData.getAll());

    return "events/index";
}

//lives at /events/create
@GetMapping("create")
public String renderCreateEventForm(Model model) {
    model.addAttribute("title", "Create Event");
    model.addAttribute(new Event());
    model.addAttribute("types", EventType.values());
    return "events/create";
}

//lives at /events/create
@PostMapping("create")
public String createEvent(@ModelAttribute @Valid Event newEvent,
                          Errors errors, Model model) {
    if (errors.hasErrors()) {
        model.addAttribute("title", "Create Event");
        return "events/create";

    }
    EventData.add(newEvent);
    return "redirect:/events";
}

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";

    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

    if (eventIds != null) {
        for (int id : eventIds) {
            EventData.remove(id);
        }
    }
    return "redirect:/events";
    }

//    @GetMapping
//    public String displayEditForm(Model model, @PathVariable int eventId) {
//        //controller code will go here
//
//    }
//
//    @PostMapping
//    public String processEditForm(int eventId, String name, String description) {
//        /controller code will go here
//    }

}
