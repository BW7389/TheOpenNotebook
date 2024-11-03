package com.dev.notebook.utils;

import com.dev.notebook.models.Role;
import com.dev.notebook.models.User;

public class UserUtils {
    public static User addUser(String firstName, String lastName, String email,String city, Role role){
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .city(city)
                .role(role)
                .build();
    }
}
