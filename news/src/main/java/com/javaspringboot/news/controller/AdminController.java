package com.javaspringboot.news.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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

    @GetMapping("/update/{post_id}")
    public ModelAndView addPostAdmin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/addPost.html");
        return modelAndView;
    }

    @GetMapping("/post/list")
    public ModelAndView listPostAdmin(Model model){
        ModelAndView modelAndView = new ModelAndView();
        List<Post> allPost = postService.findAll();
        model.addAttribute("post", allPost);
        modelAndView.setViewName("/listPost.html");
        return modelAndView;
    }

    @PostMapping("/update/{post_id}")
    public ModelAndView updatePostAdmin(@PathVariable String post_id,@ModelAttribute Post post) throws IllegalAccessException{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/addPost.html");
        Long id = Long.valueOf(post_id);
        Post savedPost = postService.updatePostById(id, post);
        return modelAndView;
    }

}
