package com.gabo.springbootmicroservice3apigateway.service;

import com.gabo.springbootmicroservice3apigateway.model.Role;
import com.gabo.springbootmicroservice3apigateway.model.User;
import com.gabo.springbootmicroservice3apigateway.repository.UserRepository;
import com.gabo.springbootmicroservice3apigateway.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User saveUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setFechaCreacion(LocalDateTime.now());
        User userCreated = userRepository.save(user);

        String jwt = jwtProvider.generateToken(userCreated);
        userCreated.setToken(jwt);

        return userCreated;

    }

    @Override
    public Optional<User> findByUsername(String username){ return userRepository.findByUsername(username); }

    @Override
    public Optional<User> findByUserEmail(String email){ return userRepository.findByEmail(email); }

    @Transactional
    @Override
    public void changeRole(Role newRole, String username){ userRepository.updateUserRole(username, newRole); }

    @Override
    public User findByUsernameReturnToken(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe:" + username));

        String jwt = jwtProvider.generateToken(user);
        user.setToken(jwt);
        return user;
    }

}
