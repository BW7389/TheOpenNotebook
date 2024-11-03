package com.dev.notebook.dtorequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.dev.notebook.constants.Constant.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterUserRequest {
    @NotEmpty(message = FIRST_NAME_REQUIRED)
    private String firstName;

    @NotEmpty(message = LAST_NAME_REQUIRED)
    private String lastName;

    @NotEmpty(message = EMAIL_REQUIRED)
    @Email(message = EMAIL_INVALID)
    private String email;

    @NotEmpty(message = PASSWORD_REQUIRED)
    @Size(min = 8, message = PASSWORD_MIN_LENGTH)
    private String password;

    @NotEmpty(message = CITY_REQUIRED)
    private String city;
}
