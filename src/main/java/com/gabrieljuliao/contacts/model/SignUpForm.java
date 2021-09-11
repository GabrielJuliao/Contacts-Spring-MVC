package com.gabrieljuliao.contacts.model;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class SignUpForm {
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public User toUser(BCryptPasswordEncoder bCryptPasswordEncoder) {
        // TODO: 9/11/2021 replace username=email for a better solution.
        String username = email;

        return new User(username, bCryptPasswordEncoder.encode(password), firstName, lastName, email, true, "ROLE_USER");
    }
}
