package com.config.authenticationwithkeycloak.service;

import com.config.authenticationwithkeycloak.model.dto.request.UserRequest;
import com.config.authenticationwithkeycloak.model.entity.User;

public interface AuthenticationService {

    User register(UserRequest userRequest) throws Exception;
}
