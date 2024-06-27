package com.gabo.springbootmicroservice3apigateway.service;

import com.gabo.springbootmicroservice3apigateway.model.Role;
import com.gabo.springbootmicroservice3apigateway.model.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findByUserEmail(String email);

    void changeRole(Role newRole, String username);

    User findByUsernameReturnToken(String username);
}
