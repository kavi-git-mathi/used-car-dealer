package com.usedcardealer.used_car_dealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Used Car Dealer");
        model.addAttribute("message", "Welcome to our Used Car Dealership!");
        return "home";
    }
}