package com.evan.careerflow.dtos;

import com.evan.careerflow.models.InterviewStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Interview round cannot be empty")
    private String round;

    @NotNull(message = "Interview date is required")
    private LocalDateTime interviewDate;

    @NotBlank(message = "Interview mode cannot be empty")
    private String mode;

    // Feedback is optional when creating an interview
    private String feedback;

    @NotNull(message = "Interview status is required")
    private InterviewStatus status;

    @NotNull(message = "Application ID is required")
    private Integer applicationId;
}