package com.config.authenticationwithkeycloak.service.serviceImpl;

import com.config.authenticationwithkeycloak.model.entity.User;
import com.config.authenticationwithkeycloak.service.UserService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private final Keycloak keycloak;
    private final ModelMapper modelMapper;

    @Value("${keycloak.realm}")
    private String realm;

    public UserServiceImpl(Keycloak keycloak, ModelMapper modelMapper) {
        this.keycloak = keycloak;
        this.modelMapper = modelMapper;
    }
    @Override
    public User getCurrentUser(String id) {
        UserRepresentation userRepresentation = keycloak.realm(realm).users().get(id).toRepresentation();
        return getUser(userRepresentation);
    }


    private User getUser(UserRepresentation userRepresentation) {
        User user = modelMapper.map(userRepresentation, User.class);
        user.setGender(userRepresentation.getAttributes().get("gender").get(0));
//        user.setNationality(userRepresentation.getAttributes().get("nationality").get(0));
//        user.setPhoneNumber(userRepresentation.getAttributes().get("phoneNumber").get(0));
//        user.setAddress(userRepresentation.getAttributes().get("address").get(0));
//        user.setProfileImage(userRepresentation.getAttributes().get("profileImage").get(0));
        return user;
    }

}
