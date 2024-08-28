package com.config.authenticationwithkeycloak.service.serviceImpl;

import com.config.authenticationwithkeycloak.exception.BadRequestException;
import com.config.authenticationwithkeycloak.exception.ConflictException;
import com.config.authenticationwithkeycloak.model.dto.request.UserRequest;
import com.config.authenticationwithkeycloak.model.entity.User;
import com.config.authenticationwithkeycloak.service.AuthenticationService;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
//@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final Keycloak keycloak;
    private final ModelMapper modelMapper;

    //use this can't use with @AllArgsConstructor
    @Value("${keycloak.realm}")
    private String realm;

    public AuthenticationServiceImpl(Keycloak keycloak, ModelMapper modelMapper) {
        this.keycloak = keycloak;
        this.modelMapper = modelMapper;
    }

    @Override
    public User register(UserRequest userRequest) throws Exception {
        if (!userRequest.getConfirmPassword().equals(userRequest.getPassword())) {
            throw new BadRequestException("Your confirm password does not match with your password");
        }
        UserRepresentation representation = prepareUserRepresentation(userRequest, preparePasswordRepresentation(userRequest.getPassword()));
        UsersResource usersResource = keycloak.realm(realm).users();
        Response response = usersResource.create(representation);
        if (response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
            throw new ConflictException("This email is already registered");
        }
        UserRepresentation userRepresentation = usersResource.get(CreatedResponseUtil.getCreatedId(response)).toRepresentation();
        User user = modelMapper.map(userRepresentation, User.class);
        user.setGender(userRepresentation.getAttributes().get("gender").getFirst());
        return user;
    }

    private UserRepresentation prepareUserRepresentation(UserRequest userRequest, CredentialRepresentation credentialRepresentation) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(userRequest.getEmail());
        userRepresentation.setEmail(userRequest.getEmail());
        userRepresentation.setFirstName(userRequest.getFirstName());
        userRepresentation.setLastName(userRequest.getLastName());
        userRepresentation.singleAttribute("gender", userRequest.getGender());
//        userRepresentation.singleAttribute("nationality", userRequest.getNationality());
//        userRepresentation.singleAttribute("phoneNumber", userRequest.getPhoneNumber());
//        userRepresentation.singleAttribute("address", userRequest.getAddress());
//        userRepresentation.singleAttribute("profileImage", userRequest.getProfileImage());
        userRepresentation.singleAttribute("isForgot", String.valueOf(false));
        userRepresentation.singleAttribute("issueAt", String.valueOf(LocalDateTime.now()));
        userRepresentation.singleAttribute("expiration", String.valueOf(LocalDateTime.now().plusMinutes(2L)));
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
        userRepresentation.setEnabled(false);
        return userRepresentation;
    }

    private CredentialRepresentation preparePasswordRepresentation(String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);
        return credentialRepresentation;
    }
}
