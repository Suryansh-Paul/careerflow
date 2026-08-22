package com.evan.careerflow.dtos;

import com.evan.careerflow.models.InterviewStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterviewRequest {

    private String round;
    private LocalDateTime interviewDate;
    private String mode;
    private String feedback;
    private InterviewStatus status;
    private Integer applicationId;
}