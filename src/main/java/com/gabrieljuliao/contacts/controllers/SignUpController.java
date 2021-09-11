package com.gabrieljuliao.contacts.controllers;

import com.gabrieljuliao.contacts.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign_up")
public class SignUpController {
    @GetMapping
    public String getSignUpForm() {
        return "sign-up";
    }
    @PostMapping
    public String signUp(User user){
        return "redirect:/";
    }
}
