package com.gabrieljuliao.contacts.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User extends Person{
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
//    @JoinTable( name = "user_contacts",
//            joinColumns = @JoinColumn(name = "userId"),
//            inverseJoinColumns = @JoinColumn(name = "contactId")
//    )
    private Set<Contact> contacts = new HashSet<>();

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
}
