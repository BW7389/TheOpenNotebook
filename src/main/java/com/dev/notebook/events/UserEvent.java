package com.dev.notebook.events;


import com.dev.notebook.enumeration.EventType;
import com.dev.notebook.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class UserEvent {
    private User user;
    private EventType type;
    private Map<?,?> data;
}
