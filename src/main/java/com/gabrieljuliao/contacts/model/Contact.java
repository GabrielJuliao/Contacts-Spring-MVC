package com.gabrieljuliao.contacts.model;

import javax.persistence.Entity;

@Entity
public class Contact extends Person {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
