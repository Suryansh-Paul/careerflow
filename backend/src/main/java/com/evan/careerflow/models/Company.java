package com.evan.careerflow.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false)
    private String name;


    private String website;


    private String industry;


    private String location;



    @OneToOne
    @JoinColumn(name = "owner_id")
    private User owner;



    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL)
    private List<Job> jobs;


}
