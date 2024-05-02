package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

@GetMapping
    public String events(Model model) {
    List<String> list = new ArrayList<>();
    list.add("Festival of Nations");
    list.add("PrideFest");
    list.add("Japanese Festival");

    model.addAttribute("events", list);
    return "events/index";
}

//lives at /events/create
@GetMapping("create")
public String renderCreateEventForm() {
    return "events/create";
}

}
