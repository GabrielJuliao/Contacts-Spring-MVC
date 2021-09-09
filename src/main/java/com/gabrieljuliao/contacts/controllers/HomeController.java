package com.gabrieljuliao.contacts.controllers;

import com.gabrieljuliao.contacts.model.User;
import com.gabrieljuliao.contacts.model.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller

public class HomeController {
    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @RequestMapping("/")
    public String getHome(Model model) {
        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(value -> model.addAttribute("user", value));
        return "home";
    }
}
