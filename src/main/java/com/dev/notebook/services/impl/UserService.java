package com.dev.notebook.services.impl;

import com.dev.notebook.enumeration.Authority;
import com.dev.notebook.enumeration.EventType;
import com.dev.notebook.events.UserEvent;
import com.dev.notebook.exceptions.ApiException;
import com.dev.notebook.models.Confirmation;
import com.dev.notebook.models.Credential;
import com.dev.notebook.models.Role;
import com.dev.notebook.models.User;
import com.dev.notebook.repositories.IConfirmationRepository;
import com.dev.notebook.repositories.ICredentialRepository;
import com.dev.notebook.repositories.IRoleRepository;
import com.dev.notebook.repositories.IUserRepository;
import com.dev.notebook.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.dev.notebook.utils.UserUtils.addUser;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {

    private final IUserRepository iUserRepository;
    private final IRoleRepository iRoleRepository;
    private final ICredentialRepository iCredentialRepository;
    private final IConfirmationRepository iConfirmationRepository;

    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher publisher;

    @Override
    public void createUser(String firstName, String lastName, String email, String password,String city) {
        var user = iUserRepository.save(createNewUser(firstName,lastName,email,city));
        var credential = new Credential(password,user);
        iCredentialRepository.save(credential);
        var confirmation = new Confirmation(user);
        iConfirmationRepository.save(confirmation);

        publisher.publishEvent(new UserEvent(user, EventType.REGISTRATION, Map.of("confirmationKey",confirmation.getConfirmationKey())));

    }

    @Override
    public void verifyAccountConfirmationKey(String confirmationKey) {
        Confirmation confirmation = getUserConfirmation(confirmationKey);
        User user = getUserByEmail(confirmation.getUser().getEmail());
        user.setEnabled(true);
        iUserRepository.save(user);
        iConfirmationRepository.delete(confirmation);


    }

    private User getUserByEmail(String email) {
        var userEmail = iUserRepository.findUserByEmail(email);
        return userEmail.orElseThrow(() -> new ApiException("User not found."));
    }

    private Confirmation getUserConfirmation(String confirmationKey) {
        return iConfirmationRepository.findConfirmationByConfirmationKey(confirmationKey).orElse(null);
    }

    @Override
    public Role getRoleName(String name) {
        var role = iRoleRepository.findRoleByName(name);
        return role.orElseThrow(() -> new ApiException("Role not found"));
    }


    private User createNewUser(String firstName, String lastName, String email,String city) {
        var role = getRoleName(Authority.USER.name());
        return addUser(firstName,lastName,email,city,role);
    }
}
