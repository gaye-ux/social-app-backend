package com.socialapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String phoneNo;

    private String password;

    private boolean canUpload = false;

    private String role;      // USER, ADMIN

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    private String profile_photo_url;

    private String cover_photo_url;
}

