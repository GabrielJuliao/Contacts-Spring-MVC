package com.gabrieljuliao.contacts.controllers;

import com.gabrieljuliao.contacts.model.SignUpForm;
import com.gabrieljuliao.contacts.model.User;
import com.gabrieljuliao.contacts.model.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getSignUpForm() {
        return "sign-up";
    }

    //todo:
    // validate user
    @PostMapping
    public String signUp(SignUpForm signUpForm){
        userRepository.save(signUpForm.toUser(bCryptPasswordEncoder));
        return "redirect:/sign_in";
    }
}
