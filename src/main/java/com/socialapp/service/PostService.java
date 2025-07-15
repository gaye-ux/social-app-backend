package com.socialapp.service;

import com.socialapp.constants.PostStatus;
import com.socialapp.entity.Media;
import com.socialapp.entity.Post;
import com.socialapp.entity.Users;
import com.socialapp.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(String caption, Users user, List<Media> media) {
        Post post = Post.builder()
                .caption(caption)
                .user(user)
                .media(media)
                .createdAt(LocalDateTime.now())
                .status(PostStatus.pending)
                .build();
        return postRepository.save(post);
    }

    public List<Post> getUserPosts(int userId){
           return postRepository.findPostByUserId(userId).orElseThrow(() -> new RuntimeException("User has NO post"));
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
