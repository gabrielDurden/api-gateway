package com.gabo.springbootmicroservice3apigateway.service;

import com.gabo.springbootmicroservice3apigateway.model.User;
import com.gabo.springbootmicroservice3apigateway.security.UserPrincipal;
import com.gabo.springbootmicroservice3apigateway.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User signInAndReturnJWT(User signInRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);

        User sigInUser = userPrincipal.getUser();
        sigInUser.setToken(jwt);

        return sigInUser;

    }

}
