package com.evan.careerflow.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Job {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String title;


    @Column(length = 2000)
    private String description;


    private String location;


    private String jobType;



    private LocalDateTime createdAt;



    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;



    @OneToMany(mappedBy = "job")
    private List<Application> applications;



    @PrePersist
    public void create(){

        createdAt = LocalDateTime.now();

    }

}

