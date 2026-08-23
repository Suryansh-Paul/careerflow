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
public class ResumeRequest {

    @NotBlank(message = "File name cannot be empty")
    private String fileName;

    @NotBlank(message = "File URL cannot be empty")
    private String fileUrl;


    private Double score;

    @NotNull(message = "User ID is required")
    private Integer userId;
}