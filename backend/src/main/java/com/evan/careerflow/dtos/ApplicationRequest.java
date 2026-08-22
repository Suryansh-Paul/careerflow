package com.evan.careerflow.dtos;

import com.evan.careerflow.models.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {

    private Integer candidateId;
    private Integer jobId;
    private ApplicationStatus status;
    private String notes;
}