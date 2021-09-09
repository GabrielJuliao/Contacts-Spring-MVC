package com.gabrieljuliao.contacts.controllers;

import com.gabrieljuliao.contacts.model.Contact;
import com.gabrieljuliao.contacts.model.ContactRepository;
import com.gabrieljuliao.contacts.model.User;
import com.gabrieljuliao.contacts.model.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/contact")
public class ContactController {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    public ContactController(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String getContactById(Model model, @RequestParam("id") Long id) {
        //replace by authenticated principal
        Optional<Contact> contact = contactRepository.findById(id);
        contact.ifPresent(value -> model.addAttribute("contact", value));
        return "contact";
    }

    @PostMapping()
    public String createContact(Contact contact) {
        //replace by authenticated principal
        contact.setUser(userRepository.findById(1L).get());
        contactRepository.save(contact);
        return "redirect:/";
    }

    //validate contact
    @GetMapping("/create")
    public String getContactForm(Model model){
        //replace by authenticated principal
        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(value -> model.addAttribute("user", value));
        return "add-contact";
    }

    @PostMapping("/remove")
    public String deleteContact(@RequestParam("id") Long id) {
        contactRepository.deleteById(id);
        return "redirect:/";
    }

}
