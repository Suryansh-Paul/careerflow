package com.evan.careerflow.dtos;

import com.evan.careerflow.models.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {

    @NotNull(message = "Candidate ID is required")
    private Integer candidateId;

    @NotNull(message = "Job ID is required")
    private Integer jobId;

    @NotNull(message = "Application status is required")
    private ApplicationStatus status;

    // Notes are usually optional, so we do not add @NotNull or @NotBlank here
    private String notes;
}