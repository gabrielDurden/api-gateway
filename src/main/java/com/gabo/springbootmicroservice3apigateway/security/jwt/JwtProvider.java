package com.gabo.springbootmicroservice3apigateway.security.jwt;

import com.gabo.springbootmicroservice3apigateway.model.User;
import com.gabo.springbootmicroservice3apigateway.security.UserPrincipal;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface JwtProvider {
    String generateToken(UserPrincipal auth);

    String generateToken(User user);

    Authentication getAuthentication(HttpServletRequest request);

    boolean isTokenValid(HttpServletRequest request);
}
