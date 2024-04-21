package com.javaspringboot.news.services;

import com.javaspringboot.news.dto.JwtAuthenticationResponse;
import com.javaspringboot.news.dto.RefreshTokenRequest;
import com.javaspringboot.news.dto.SignInRequest;
import com.javaspringboot.news.dto.SignUpRequest;
import com.javaspringboot.news.entities.User;

public interface AuthenticationService {

    User signUp(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
