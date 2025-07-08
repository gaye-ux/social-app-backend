package com.socialapp.service;

import com.socialapp.entity.Media;
import com.socialapp.entity.Post;
import com.socialapp.entity.User;
import com.socialapp.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post createPost(String caption, User user, List<Media> media) {
        Post post = Post.builder()
                .caption(caption)
                .user(user)
                .media(media)
                .createdAt(LocalDateTime.now())
                .build();
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
