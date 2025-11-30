package com.example.ProectPract.Repository;

import com.example.ProectPract.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
