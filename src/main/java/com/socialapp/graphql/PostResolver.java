package com.socialapp.graphql;

import com.socialapp.entity.Media;
import com.socialapp.entity.Post;
import com.socialapp.entity.User;
import com.socialapp.service.MediaService;
import com.socialapp.service.PostService;
import com.socialapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostResolver {
    private final PostService postService;
    private final UserService userService;
    private final MediaService mediaService;

    @QueryMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @MutationMapping
    public Post createPost(Long userId, String caption, List<String> mediaUrls) {
        User user = userService.save(User.builder().id(userId).build());

        List<Media> mediaList = new ArrayList<>();
        for (String url : mediaUrls) {
            Media media = Media.builder()
                    .url(url)
                    .type(url.endsWith(".mp4") ? "video" : "image")
                    .compressed(true)
                    .build();
            mediaService.save(media);
            mediaList.add(media);
        }
        return postService.createPost(caption, user, mediaList);
    }
}
