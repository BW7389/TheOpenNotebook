package com.dev.notebook.services;

import com.dev.notebook.models.Role;

public interface IUserService{
    void createUser(String firstName, String lastName, String email, String password,String city);
    Role getRoleName(String name);

    void verifyAccountConfirmationKey(String confirmationKey);

}
