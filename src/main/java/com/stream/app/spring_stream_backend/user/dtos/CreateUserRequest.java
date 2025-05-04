package com.stream.app.spring_stream_backend.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest{
        @NotNull
        private String username;
        @NotNull
        @Email
        private String email;
        @NotNull
        private String password;
}
