package com.socialapp.graphql;

import com.socialapp.entity.Media;
import com.socialapp.entity.Post;
import com.socialapp.entity.Users;
import com.socialapp.repository.UserRepository;
import com.socialapp.service.MediaService;
import com.socialapp.service.PostService;
import com.socialapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostResolver {
    private final PostService postService;

    private final UserService userService;

    private final MediaService mediaService;

    private final UserRepository userRepository;

    @QueryMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @MutationMapping
    public Post createPost(@Argument Long userId, @Argument String caption, @Argument List<String> mediaUrls) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Creating Post Not Found"));

        Post post = postService.createPost(caption, user);

        List<Media> mediaList = new ArrayList<>();
        if (mediaUrls != null) {
            for (String url : mediaUrls) {
                Media media = Media.builder()
                        .url(url)
                        .type(url.endsWith(".mp4") ? "video" : "image")
                        .compressed(true)
                        .post(post)
                        .build();
                mediaService.save(media);
                mediaList.add(media);
            }
        }

        post.setMedia(mediaList);

        return post;
    }

    @QueryMapping
    public List<Post> getUserPosts(@Argument int userId){
        return postService.getUserPosts(userId);
    }
}
