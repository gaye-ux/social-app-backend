package com.socialapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    private boolean canUpload = false;
    private String role;      // USER, ADMIN

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}

