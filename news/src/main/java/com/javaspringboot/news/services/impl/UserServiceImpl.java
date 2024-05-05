package com.javaspringboot.news.services.impl;

import com.javaspringboot.news.entities.User;
import com.javaspringboot.news.repository.UserRepo;
import com.javaspringboot.news.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;


    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public User findUserByEmail(String email) {
        Optional<User> find = userRepo.findByEmail(email);
        if (find.isPresent()) {
            return find.get();
        }
        throw new UsernameNotFoundException("User not found with email " + email);
    }


}
