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
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    // @PostMapping("/signup")
    // public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
    //     return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    // }

    // @PostMapping("/signin")
    // public ResponseEntity<JwtAuthenticationResponse> sigin(@RequestBody SignInRequest signInRequest) {
    //     return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    // }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute SignUpRequest signUpRequest) {
        ModelAndView modelAndView = new ModelAndView();
        User signup = authenticationService.signUp(signUpRequest);
        if(signup == null){
            modelAndView.setViewName("/404.html");
        }
        modelAndView.setViewName("/login.html");
        return modelAndView;
    }
    @PostMapping("/signin")
    public ModelAndView signin(@ModelAttribute SignInRequest signInRequest) {
        ModelAndView modelAndView = new ModelAndView();
        
        System.err.println("email " + signInRequest.getEmail() + "  " + signInRequest.getPassword());
        JwtAuthenticationResponse res;
        try {
            res = authenticationService.signIn(signInRequest);
            modelAndView.setViewName("redirect:/api/auth/home");
        } catch (IllegalArgumentException e) {
            System.err.println("lpasidjas");
            modelAndView.setViewName("redirect:/home/404");
        }
        
        return modelAndView;
    }


    @RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login.html");
        return modelAndView;
    }
}
