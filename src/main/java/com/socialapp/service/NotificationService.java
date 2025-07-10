package com.socialapp.service;

import com.socialapp.entity.Notification;
import com.socialapp.entity.Users;
import com.socialapp.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public Notification notifyUser(Users user, String message) {
        Notification notification = Notification.builder()
                .user(user)
                .message(message)
                .seen(false)
                .createdAt(LocalDateTime.now())
                .build();
        return notificationRepository.save(notification);
    }

    public List<Notification> getByUserId(Long userId) {
        return notificationRepository.findByUser_Id(userId);
    }
}
