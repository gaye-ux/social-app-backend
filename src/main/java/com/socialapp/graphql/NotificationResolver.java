package com.socialapp.graphql;

import com.socialapp.entity.Notification;
import com.socialapp.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotificationResolver {

    private final NotificationService notificationService;

    @QueryMapping
    public List<Notification> getNotifications(Long userId) {
        return notificationService.getByUserId(userId);
    }
}
