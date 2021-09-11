package com.gabrieljuliao.contacts.controllers;

import com.gabrieljuliao.contacts.model.User;
import com.gabrieljuliao.contacts.model.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final UserRepository userRepository;

    public AccountController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @ModelAttribute("user")
    public User getUser(Principal principal) {
        return userRepository.findByUsername(principal.getName());
    }

    @GetMapping
    public String getAccount() {
        return "account";
    }

    @PostMapping
    public String setAccount(@ModelAttribute("user") User user, String firstName, String lastName, String email) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userRepository.save(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteAccount(@ModelAttribute("user") User user) {
        userRepository.deleteById(user.getId());
        return "redirect:/";
    }
}
