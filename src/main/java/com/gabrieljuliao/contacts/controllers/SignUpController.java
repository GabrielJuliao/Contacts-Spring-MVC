package com.gabrieljuliao.contacts.controllers;

import com.gabrieljuliao.contacts.model.SignUpForm;
import com.gabrieljuliao.contacts.model.User;
import com.gabrieljuliao.contacts.model.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/sign_up")
public class SignUpController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SignUpController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping
    public String getSignUpForm(SignUpForm signUpForm) {
        return "sign-up";
    }

    @PostMapping
    public String signUp(@Valid SignUpForm signUpForm, Errors errors){
        if (errors.hasErrors()){
            return "sign-up";
        }
        userRepository.save(signUpForm.toUser(bCryptPasswordEncoder));
        return "redirect:/sign_in";
    }
}
