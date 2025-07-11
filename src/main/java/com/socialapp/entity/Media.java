package com.socialapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String type; // image, video, audio
    private boolean compressed;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Post post;
}


