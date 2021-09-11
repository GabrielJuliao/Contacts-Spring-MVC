package com.gabrieljuliao.contacts.controllers;

import com.gabrieljuliao.contacts.model.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {
    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String getHome(Model model, Principal principal) {
        model.addAttribute("user", userRepository.findByUsername(principal.getName()));
        return "home";
    }
}
