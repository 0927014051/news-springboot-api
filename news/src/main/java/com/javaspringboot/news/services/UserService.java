package com.javaspringboot.news.services;

import com.javaspringboot.news.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();

    User findUserByEmail(String email);
}
