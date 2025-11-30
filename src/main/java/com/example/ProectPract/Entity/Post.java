package com.example.ProectPract.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Double locationLat;

    @Column(nullable = false)
    private Double locationLng;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ElementCollection
    @CollectionTable(name = "post_photos", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "photo_url")
    private List<String> photoUrls = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
