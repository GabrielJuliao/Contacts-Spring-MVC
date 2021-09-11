package com.gabrieljuliao.contacts.controllers;

import com.gabrieljuliao.contacts.model.User;
import com.gabrieljuliao.contacts.model.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/account")
@SessionAttributes("user")
public class AccountController {
    private final UserRepository userRepository;

    public AccountController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getAccount() {
        return "account";
    }

    @PostMapping
    public String setAccount(User user, String firstName, String lastName, String email) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userRepository.save(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteAccount(User user) {
        userRepository.deleteById(user.getUserId());
        return "redirect:/logout";
    }
}
