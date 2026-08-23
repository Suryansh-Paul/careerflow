package com.evan.careerflow.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {

    @NotBlank(message = "Job title cannot be empty")
    private String title;

    @NotBlank(message = "Job description cannot be empty")
    private String description;

    @NotBlank(message = "Location cannot be empty")
    private String location;

    @NotBlank(message = "Job type cannot be empty")
    private String jobType;

    @NotNull(message = "Company ID is required")
    private Integer companyId;
}