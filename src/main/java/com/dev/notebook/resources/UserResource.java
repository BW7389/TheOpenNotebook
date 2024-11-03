package com.dev.notebook.resources;

import com.dev.notebook.domain.Response;
import com.dev.notebook.dtorequest.RegisterUserRequest;
import com.dev.notebook.services.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.dev.notebook.constants.Constant.ACCOUNT_CREATED_INFO;
import static com.dev.notebook.constants.Constant.ACCOUNT_VERIFY_INO;
import static com.dev.notebook.utils.RequestUtils.getResponse;
import static java.util.Collections.emptyMap;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserResource {
    private final IUserService iUserService;

    @PostMapping("/register")
    public ResponseEntity<Response> saveUser(@RequestBody @Valid RegisterUserRequest userRequest, HttpServletRequest request){
        iUserService.createUser(
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getCity());
        return ResponseEntity.created(getUri()).body(getResponse(request,emptyMap(),ACCOUNT_CREATED_INFO,CREATED));
    }

    @GetMapping("/account/verify")
    public ResponseEntity<Response> verifyAccountConfirmationKey(@RequestParam("confirmationKey") String confirmationKey, HttpServletRequest request ){
        iUserService.verifyAccountConfirmationKey(confirmationKey);
        return ResponseEntity.ok().body(getResponse(request,emptyMap(),ACCOUNT_VERIFY_INO,OK));
    }
    private URI getUri() {
        return URI.create("");
    }
}
