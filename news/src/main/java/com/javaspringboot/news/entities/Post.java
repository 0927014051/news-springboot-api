package com.javaspringboot.news.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long post_id;

    @Column
    private String image;

    @Column
    private String title;

    @Column
    private String status;

    @Column
    private String slug;

    @Column
    private String post_data;

    @Column
    private String tags;

    @Column
    private int views;

    @Column
    private String author;

    @Column
    private LocalDateTime created_at;

    @Column
    private LocalDateTime updated_at;

    @Column
    private Long created_by;

    @Column
    private Long updated_by;

    @Column
    private Long category_id;

    @ManyToOne
    @JoinColumn(name = "created_by",insertable = false,updatable = false)
    private User user_created;

    @ManyToOne
    @JoinColumn(name = "updated_by",insertable = false,updatable = false)
    private User user_updated;

    @OneToMany(mappedBy = "post")
    private List<Comment> comment;

    @ManyToOne
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private Category category;
}
