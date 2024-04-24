package com.javaspringboot.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaspringboot.news.entities.Post;

@Repository
public interface PostRepo extends JpaRepository<Post,Long>{

    

}
