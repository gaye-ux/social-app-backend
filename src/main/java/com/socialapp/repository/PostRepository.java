package com.socialapp.repository;

import com.socialapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface PostRepository extends JpaRepository<Post, Long> {
    public Optional<List<Post>> findPostByUserId(int userId);
}