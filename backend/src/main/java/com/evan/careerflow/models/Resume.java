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
public class Resume {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    private String fileName;



    private String fileUrl;



    private Double score;



    private LocalDateTime uploadedAt;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    @PrePersist
    public void create(){

        uploadedAt = LocalDateTime.now();

    }

}