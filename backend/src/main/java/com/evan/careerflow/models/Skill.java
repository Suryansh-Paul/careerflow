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
public class Skill {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    private String name;



    private String category;



    @ManyToMany(mappedBy = "skills")
    private List<User> users;


}