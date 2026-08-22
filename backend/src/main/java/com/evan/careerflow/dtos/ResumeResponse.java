package com.evan.careerflow.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeResponse {

    private Integer id;
    private String fileName;
    private String fileUrl;
    private Double score;
    private LocalDateTime uploadedAt;
    private Integer userId;
}