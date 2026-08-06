package com.evan.careerflow.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interview {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    private String round;



    private LocalDateTime interviewDate;



    private String mode;



    private String feedback;



    @Enumerated(EnumType.STRING)
    private InterviewStatus status;



    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;


}