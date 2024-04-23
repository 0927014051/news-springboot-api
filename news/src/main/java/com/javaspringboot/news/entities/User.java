package com.javaspringboot.news.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long user_id;

    @Column
    private String email;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private String password;

    @Column
    private String image;

    @Column
    private Role role;

    @Column
    private String token;

    @Column
    private String refresh_token;

    @Column
    private LocalDateTime created_at;

    @Column
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "user_created")
    private List<Post> post_created;

    @OneToMany(mappedBy = "user_updated")
    private  List<Post> post_updated;

    @OneToMany(mappedBy = "user_created")
    private  List<Comment> comment_created;

    @OneToMany(mappedBy = "user_updated")
    private  List<Comment> comment_updated;

    @OneToMany(mappedBy = "user_created")
    private  List<Category> category_created;

    @OneToMany(mappedBy = "user_updated")
    private  List<Category> category_updated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
