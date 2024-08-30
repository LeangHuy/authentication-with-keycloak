package com.config.authenticationwithkeycloak.service;

import com.config.authenticationwithkeycloak.model.entity.User;

public interface UserService {
    User getCurrentUser(String id);
}
