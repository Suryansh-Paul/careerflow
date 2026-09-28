package com.evan.careerflow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfileResponse {
    private Integer id;
    private String name;
    private String email;
    private String role;
    private boolean isOnboarded;
    private String profileImageUrl;
    private String headline;
    private String bio;
    private String location;
    private List<String> skills;
}
