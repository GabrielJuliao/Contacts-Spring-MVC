package com.gabrieljuliao.contacts.controllers;

import com.gabrieljuliao.contacts.model.Contact;
import com.gabrieljuliao.contacts.model.ContactRepository;
import com.gabrieljuliao.contacts.model.User;
import com.gabrieljuliao.contacts.model.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping("/contact")
public class ContactController {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    public ContactController(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute("user")
    public User getUser(Principal principal) {
        return userRepository.findByUsername(principal.getName());
    }

    @GetMapping()
    public String getContactById(@ModelAttribute("user") User user, Model model, @RequestParam("id") Long id) {
        Set<Contact> contactList = user.getContacts();
        for (Contact contact : contactList) {
            if (Objects.equals(contact.getId(), id)) {
                model.addAttribute("contact", contact);
                return "contact";
            }
        }
        return "home";
    }

    @PostMapping
    public String createOrUpdateContact(@ModelAttribute("user") User user, Contact contact) {
        contact.setUser(user);
        contactRepository.save(contact);
        return "redirect:/";
    }
    @GetMapping("/create")
    public String getCreateForm(){
        return "add-contact";
    }

    @PostMapping("/remove")
    public String deleteContact(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        Set<Contact> contactList = user.getContacts();
        for (Contact contact : contactList) {
            if (Objects.equals(contact.getId(), id)) {
                contactRepository.deleteById(id);
            }
        }
        return "redirect:/";
    }

}
