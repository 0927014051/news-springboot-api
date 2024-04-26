package com.javaspringboot.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaspringboot.news.entities.Post;

@Repository
public interface PostRepo extends JpaRepository<Post,Long>{

    @Query(value = "SELECT * FROM Post WHERE slug = ?1",nativeQuery =  true)
    Post findPostBySlug(String slug);

    @Query(value = "SELECT p FROM Post p WHERE p.created_at <= CURRENT_TIMESTAMP ORDER BY p.created_at DESC LIMIT 9")
    List<Post> findLatestPosts();

}
