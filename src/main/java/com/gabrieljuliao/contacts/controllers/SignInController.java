package com.gabrieljuliao.contacts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {
    @GetMapping("sign_in")
    public String getSignInForm(){
        return "sign-in";
    }
}
