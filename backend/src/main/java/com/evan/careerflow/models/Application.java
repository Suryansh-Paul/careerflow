package com.evan.careerflow.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "application") // Standardizing singular table name
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Application {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private User candidate;



    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;



    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;



    private LocalDateTime appliedAt;



    private String notes;



    @OneToMany(mappedBy = "application",
            cascade = CascadeType.ALL)
    private List<Interview> interviews;



    @PrePersist
    public void create(){

        appliedAt = LocalDateTime.now();

    }

}