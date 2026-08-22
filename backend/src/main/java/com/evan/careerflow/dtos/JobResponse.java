package com.evan.careerflow.dtos;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {

    private Integer id;
    private String title;
    private String description;
    private String location;
    private String jobType;
    private LocalDateTime createdAt;
    private Integer companyId;
}