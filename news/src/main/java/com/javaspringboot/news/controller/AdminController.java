package com.javaspringboot.news.controller;

import com.javaspringboot.news.dto.JwtAuthenticationResponse;
import com.javaspringboot.news.entities.Post;
import com.javaspringboot.news.entities.User;
import com.javaspringboot.news.services.JWTService;
import com.javaspringboot.news.services.PostService;
import com.javaspringboot.news.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private final JWTService jwtService;
    @Autowired
    private final PostService postService;
    @Autowired
    private final UserService userService;

    private final JwtAuthenticationResponse jwtAuthenticationResponse;

    @GetMapping("")
    public ResponseEntity<String> sayHello() {

        return ResponseEntity.ok("Hi Admin");
    }

    @GetMapping("/update/{post_id}")
    public ModelAndView addPostAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/addPost.html");
        return modelAndView;
    }

    @GetMapping("/post/list")
    public ModelAndView listPostAdmin(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        List<Post> allPost = postService.findAll();
        model.addAttribute("post", allPost);
        modelAndView.setViewName("/listPost.html");
        return modelAndView;
    }

    @PostMapping("/update/{post_id}")
    public ModelAndView updatePostAdmin(@PathVariable String post_id, @ModelAttribute Post post) throws IllegalAccessException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/addPost.html");
        Long id = Long.valueOf(post_id);
        Post savedPost = postService.updatePostById(id, post);
        return modelAndView;
    }

    @PostMapping("/add/post")
    public ModelAndView addPostAdmin(@ModelAttribute Post post) {
        if (jwtAuthenticationResponse.getToken() != null) {
            String email = jwtService.extractUserName(jwtAuthenticationResponse.getToken());
            User user = userService.findUserByEmail(email);
            Post create = postService.createPost(post, user.getUser_id());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/addPost.html");
        return modelAndView;
    }

}
