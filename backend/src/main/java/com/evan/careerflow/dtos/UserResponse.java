package com.evan.careerflow.dtos;

import com.evan.careerflow.models.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Integer id;

    private String name;

    private String email;

    private boolean enabled;

    private Role role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
