package com.javaspringboot.news.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javaspringboot.news.entities.Post;
import com.javaspringboot.news.services.PostService;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hi Admin");
    }

    @GetMapping("/{post_id}/update")
    public ModelAndView addPostAdmin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/addPost.html");
        return modelAndView;
    }

    @PostMapping("/{post_id}/update")
    public ModelAndView updatePostAdmin(@PathVariable Long post_id,@ModelAttribute Post post) throws IllegalAccessException{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/addPost.html");
        Post savedPost = postService.updatePostById(post_id, post);
        System.err.println("success sdadad");
        return modelAndView;
    }

}
