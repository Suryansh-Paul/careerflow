package com.evan.careerflow.dtos;

import com.evan.careerflow.models.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponse {

    private Integer id;
    private Integer candidateId;
    private Integer jobId;
    private ApplicationStatus status;
    private LocalDateTime appliedAt;
    private String notes;
}