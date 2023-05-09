package com.application.socialnetwork.service;

import com.application.socialnetwork.dto.AuthenticationRequest;
import com.application.socialnetwork.dto.AuthenticationResponse;
import com.application.socialnetwork.dto.UserDto;

public interface AuthService {

    AuthenticationResponse register(UserDto userDto);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
