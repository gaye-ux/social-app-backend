package com.socialapp.service;

import com.socialapp.entity.Media;
import com.socialapp.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;

    public Media save(Media media) {
        return mediaRepository.save(media);
    }
}