package com.evan.careerflow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfileUpdateRequest {
    private String headline;
    private String bio;
    private String location;
    private List<Integer> skillIds;
}