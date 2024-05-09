package org.launchcode.controllers;

import org.launchcode.data.EventData;
import org.launchcode.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    return "events/create";
}

//lives at /events/create
@PostMapping("create")
public String createEvent(@ModelAttribute Event newEvent) {
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

}
