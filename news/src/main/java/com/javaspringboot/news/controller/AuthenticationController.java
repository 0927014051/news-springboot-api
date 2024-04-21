package com.javaspringboot.news.controller;


import com.javaspringboot.news.dto.JwtAuthenticationResponse;
import com.javaspringboot.news.dto.RefreshTokenRequest;
import com.javaspringboot.news.dto.SignInRequest;
import com.javaspringboot.news.dto.SignUpRequest;
import com.javaspringboot.news.entities.User;
import com.javaspringboot.news.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> sigin(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @GetMapping("/get")
    public ResponseEntity<SignUpRequest> testApi() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*"); // Allow requests from all origins
        SignUpRequest sign = new SignUpRequest();
        sign.setEmail("user@gmail.com");
        sign.setPassword("user");
        sign.setFirstname("user");
        sign.setLastname("user");
        return ResponseEntity.ok().headers(headers).body(sign);
    }

}
