package com.config.authenticationwithkeycloak.controller;

import com.config.authenticationwithkeycloak.model.entity.User;
import com.config.authenticationwithkeycloak.service.UserService;
import com.config.authenticationwithkeycloak.utils.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/users")
@SecurityRequirement(name = "myauth")
public class UserController {
    private final UserService userService;
    @GetMapping
    @Operation(summary = "Get current user")
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        User user = userService.getCurrentUser(principal.getName());

        APIResponse<User> response = APIResponse.<User>builder()
                .message("Get current user successfully.")
                .payload(user)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
