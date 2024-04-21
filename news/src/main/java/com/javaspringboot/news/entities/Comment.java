package com.javaspringboot.news.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long comment_id;

    @Column
    private String message;

    @Column
    private String status;

    @Column
    private LocalDateTime created_at;

    @Column
    private LocalDateTime updated_at;

    @Column
    private Long created_by;

    @Column
    private Long post_id;

    @ManyToOne
    @JoinColumn(name = "post_id",insertable = false,updatable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "created_by",insertable = false,updatable = false)
    private User user_created;

    @ManyToOne
    @JoinColumn(name = "updated_by",insertable = false,updatable = false)
    private User user_updated;
}
