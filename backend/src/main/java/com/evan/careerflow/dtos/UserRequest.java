package com.evan.careerflow.dtos;

import com.evan.careerflow.models.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String name;

    private String email;

    private String password;

    private Role role;
}