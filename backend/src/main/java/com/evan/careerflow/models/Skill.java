package com.evan.careerflow.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Skill {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    private String name;



    private String category;



    @ManyToMany(mappedBy = "skills")
    private List<User> users;


}