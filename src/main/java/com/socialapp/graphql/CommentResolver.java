package com.socialapp.graphql;

import com.socialapp.entity.Comment;
import com.socialapp.entity.Post;
import com.socialapp.entity.User;
import com.socialapp.service.CommentService;
import com.socialapp.service.PostService;
import com.socialapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class CommentResolver {
    private final CommentService commentService;
    private final UserService userService;
    private final PostService postService;

    @MutationMapping
    public Comment addComment(Long postId, Long userId, String content, String type) {
        Post post = Post.builder().id(postId).build();
        User user = User.builder().id(userId).build();

        Comment comment = Comment.builder()
                .post(post)
                .user(user)
                .content(content)
                .type(type)
                .createdAt(LocalDateTime.now())
                .expiresAt("audio".equals(type) ? LocalDateTime.now().plusHours(48) : null)
                .build();

        return commentService.save(comment);
    }
}
