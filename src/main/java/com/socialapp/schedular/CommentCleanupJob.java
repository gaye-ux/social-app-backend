package com.socialapp.scheduler;

import com.socialapp.entity.Comment;
import com.socialapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentCleanupJob {

    private final CommentService commentService;

    @Scheduled(fixedRate = 3600000) // every hour
    public void cleanExpiredAudioComments() {
        List<Comment> expiredComments = commentService.getExpiredAudioComments();
        for (Comment comment : expiredComments) {
            if ("audio".equals(comment.getType())) {
                commentService.delete(comment);
            }
        }
    }
}
