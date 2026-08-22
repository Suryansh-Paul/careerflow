package com.evan.careerflow.dtos;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {

    private String title;
    private String description;
    private String location;
    private String jobType;
    private Integer companyId;
}