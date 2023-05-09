package com.application.socialnetwork.service.impl;

import com.application.socialnetwork.config.JwtUtils;
import com.application.socialnetwork.dto.AuthenticationRequest;
import com.application.socialnetwork.dto.AuthenticationResponse;
import com.application.socialnetwork.dto.UserDto;
import com.application.socialnetwork.entity.Role;
import com.application.socialnetwork.entity.User;
import com.application.socialnetwork.exceptions.ObjectsValidator;
import com.application.socialnetwork.repository.UserRepository;
import com.application.socialnetwork.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final ObjectsValidator<UserDto> validator;

    @Override
    @Transactional
    public AuthenticationResponse register(UserDto userDto) {
        validator.validate(userDto);
        User user = UserDto.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        var savedUser = userRepository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId());
        claims.put("fullname", savedUser.getFirstname() + " " + savedUser.getLastname());

        String token = jwtUtils.generateToken(savedUser, claims);
        return AuthenticationResponse.builder().token(token).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        final User user = userRepository.findByEmail(request.getEmail()).get();

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("fullname", user.getFirstname() + " " + user.getLastname());

        final String token = jwtUtils.generateToken(user, claims);
        return AuthenticationResponse.builder().token(token).build();
    }
}
