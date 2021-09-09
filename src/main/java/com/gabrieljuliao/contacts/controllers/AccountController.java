package com.gabrieljuliao.contacts.controllers;

import com.gabrieljuliao.contacts.model.User;
import com.gabrieljuliao.contacts.model.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final UserRepository userRepository;

    public AccountController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getAccount(Model model) {
        //replace by authenticated principal
        Optional<User> contact = userRepository.findById(1L);
        contact.ifPresent(value -> model.addAttribute("user", value));
        return "account";
    }

    @PostMapping
    public String setAccount(User user){
        return "redirect:/account";
    }
    @PostMapping("/delete")
    public String deleteAccount(){
        //replace by authenticated principal
        userRepository.deleteById(1L);
        return "redirect:/";
    }
}
