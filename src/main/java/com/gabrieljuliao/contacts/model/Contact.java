package com.gabrieljuliao.contacts.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Contact extends Person {
    private String title;
    @ManyToOne
    private User user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
