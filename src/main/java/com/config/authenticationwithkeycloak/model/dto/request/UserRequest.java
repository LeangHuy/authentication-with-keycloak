package com.config.authenticationwithkeycloak.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserRequest {
    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    @Pattern(
            regexp = "(?i)^(male|female|other)$",
            message = "Please specify a valid gender (male, female, or other)"
    )
    private String gender;

//    @NotNull
//    @NotBlank
//    private String nationality;

//    @NotNull
//    @NotBlank
//    @Pattern(
//            regexp = "^(\\\\+[0-9]{1,3}[- ]?)?([0-9]{3,4})[- ]?([0-9]{3})[- ]?([0-9]{4})$",
//            message = "Please ensure the phone number is in a valid format"
//    )
//    private String phoneNumber;

//    @NotNull
//    @NotBlank
//    private String address;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "Password must be at least 8 characters long and include both letters and numbers"
    )
    private String password;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "Confirm password must be at least 8 characters long and include both letters and numbers"
    )
    private String confirmPassword;

//    @NotNull
//    @NotBlank
//    @Pattern(regexp = "(\\S+(\\.(?i)(jpg|png|gif|bmp))$)",
//            message = "profile must be contain file extension such as jpg, png, gif and bmp only"
//    )
//    private String profileImage;
}
