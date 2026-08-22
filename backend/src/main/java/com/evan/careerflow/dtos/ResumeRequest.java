package com.evan.careerflow.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeRequest {

    private String fileName;
    private String fileUrl;
    private Double score;
    private Integer userId;
}