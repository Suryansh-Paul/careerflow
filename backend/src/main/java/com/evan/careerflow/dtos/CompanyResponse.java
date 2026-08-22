package com.evan.careerflow.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {

    private Integer id;

    private String name;

    private String website;

    private String industry;

    private String location;

    private Integer ownerId;
}