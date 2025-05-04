package com.stream.app.spring_stream_backend.user;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name",unique = true, nullable = false)
    @Nonnull
    private String username;
    @Column(unique = true, nullable = false)
    @Nonnull
    private String email;
//    private String password;
    @Column(nullable = true)
    @Nullable
    private String bio;
    @Column(nullable = true)
    @Nullable
    private String imageUrl;

}
