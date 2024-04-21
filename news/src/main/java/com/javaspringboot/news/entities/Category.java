package com.javaspringboot.news.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long category_id;

    @Column
    private String category_name;

    @Column
    private String slug;

    @Column
    private LocalDateTime created_at;

    @Column
    private LocalDateTime updated_at;

    @Column
    private Long created_by;

    @Column
    private Long updated_by;

    @ManyToOne
    @JoinColumn(name = "created_by",insertable = false,updatable = false)
    private User user_created;

    @ManyToOne
    @JoinColumn(name = "updated_by",insertable = false,updatable = false)
    private User user_updated;

    @OneToMany(mappedBy = "category")
    private List<Post> post;
}
