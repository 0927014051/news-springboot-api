package com.javaspringboot.news.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.javaspringboot.news.entities.User;

public interface UserService {

    UserDetailsService userDetailsService();
}
