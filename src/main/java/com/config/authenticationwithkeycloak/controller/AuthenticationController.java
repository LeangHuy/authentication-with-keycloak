package com.config.authenticationwithkeycloak.controller;

import com.config.authenticationwithkeycloak.model.dto.request.UserRequest;
import com.config.authenticationwithkeycloak.model.entity.User;
import com.config.authenticationwithkeycloak.service.AuthenticationService;
import com.config.authenticationwithkeycloak.utils.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/authentications")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary = "Register with your information")
    public ResponseEntity<?> register(@RequestBody @Valid UserRequest userRequest) throws Exception {
        User user = authenticationService.register(userRequest);
        APIResponse<User> response = APIResponse.<User>builder()
                .message("Register has been successfully.")
                .payload(user)
                .status(HttpStatus.CREATED)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
