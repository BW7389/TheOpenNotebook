package com.dev.notebook.events.listeners;


import com.dev.notebook.events.UserEvent;
import com.dev.notebook.services.IEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventListener {
    private final IEmailService iEmailService;


    @EventListener
    public void onUserEvent(UserEvent event){
        switch (event.getType()){
            case REGISTRATION -> iEmailService.sendNewAccountEmail(event.getUser().getFirstName(),event.getUser().getEmail(), (String) event.getData().get("confirmationKey"));
            case RESET_PASSWORD -> iEmailService.sendPasswordResetEmail(event.getUser().getFirstName(),event.getUser().getEmail(), (String) event.getData().get("confirmationKey"));
            default -> {}
        }
    }
}
