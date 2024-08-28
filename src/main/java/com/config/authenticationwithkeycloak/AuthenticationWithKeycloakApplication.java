package com.config.authenticationwithkeycloak;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(
        name = "myauth",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                clientCredentials = @OAuthFlow(
                        tokenUrl = "http://localhost:8080/realms/myauth/protocol/openid-connect/token"
                ),
                password = @OAuthFlow(
                        tokenUrl = "http://localhost:8080/realms/myauth/protocol/openid-connect/token"
                )
        )
)
public class AuthenticationWithKeycloakApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationWithKeycloakApplication.class, args);
    }

}
