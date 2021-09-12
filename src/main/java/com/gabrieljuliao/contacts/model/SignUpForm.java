package com.gabrieljuliao.contacts.model;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpForm {
    @Email(message = "Not a valid email address")
    @NotBlank(message = "Email must not be blank.")
    private String email;
    @NotBlank(message = "Password must not be blank.")
    @Size(min = 8, max = 20, message = "Passwords should have a minimum of 8 characters and a maximum of 20.")
    private String password;
    @NotBlank(message = "Firstname must not be blank.")
    @Size(min = 2, max = 50, message = "First name should have a minimum of 2 characters and a maximum of 50.")
    private String firstName;
    @NotBlank(message = "Lastname must not be blank.")
    @Size(min = 2, max = 50, message = "Last name should have a minimum of 2 characters and a maximum of 50.")
    private String lastName;

    public User toUser(BCryptPasswordEncoder bCryptPasswordEncoder) {
        // TODO: 9/11/2021 replace username=email for a better solution.
        String username = email;

        return new User(username, bCryptPasswordEncoder.encode(password), firstName, lastName, email, true, "ROLE_USER");
    }
}
