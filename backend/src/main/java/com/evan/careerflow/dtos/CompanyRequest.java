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
public class CompanyRequest {

    @NotBlank(message = "Company name cannot be empty")
    private String name;

    @NotBlank(message = "Website URL cannot be empty")
    private String website;

    @NotBlank(message = "Industry cannot be empty")
    private String industry;

    @NotBlank(message = "Location cannot be empty")
    private String location;

    @NotNull(message = "Owner ID is required")
    private Integer ownerId;
}