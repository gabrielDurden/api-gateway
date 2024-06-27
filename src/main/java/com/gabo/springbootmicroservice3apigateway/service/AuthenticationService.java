package com.gabo.springbootmicroservice3apigateway.service;

import com.gabo.springbootmicroservice3apigateway.model.User;

public interface AuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
