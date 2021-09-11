package com.gabrieljuliao.contacts.controllers;

import com.gabrieljuliao.contacts.model.Contact;
import com.gabrieljuliao.contacts.model.ContactRepository;
import com.gabrieljuliao.contacts.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping("/contact")
@SessionAttributes("user")
public class ContactController {
    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public String getContactById(User user, Model model, @RequestParam("id") Long id) {
        Set<Contact> contactList = user.getContacts();
        for (Contact contact : contactList) {
            if (Objects.equals(contact.getContactId(), id)) {
                model.addAttribute("contact", contact);
                return "contact";
            }
        }
        return "home";
    }

    //careful, DO NOT NAME A FIELD WITH THE SAME NAME FOR DATA BINDING, spring doesn't know which one is which.
    @PostMapping
    public String createOrUpdateContact(Contact contact, User user) {
        contact.setUser(user);
        Set<Contact> contactList = user.getContacts();
        contactList.removeIf(contactIn -> Objects.equals(contact.getContactId(), contactIn.getContactId()));
        contactList.add(contact);
        user.setContacts(contactList);
        contactRepository.save(contact);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String getCreateForm() {
        return "add-contact";
    }

    @PostMapping("/remove")
    public String deleteContact(User user, @RequestParam("contactId") Long id) {
        Set<Contact> contactList = user.getContacts();

        if (contactList.removeIf(contactIn -> Objects.equals(id, contactIn.getContactId()))) {
            contactRepository.deleteById(id);
        }

        return "redirect:/";
    }

}
