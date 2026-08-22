package com.evan.careerflow.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String name;


    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false)
    private String password;


    private boolean enabled = true; // Server controlled


    @Enumerated(EnumType.STRING)
    private Role role;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;



    @OneToMany(mappedBy = "candidate")
    private List<Application> applications;



    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<Resume> resumes;



    @ManyToMany
    @JoinTable(
            name = "user_skills",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="skill_id")
    )
    private List<Skill> skills;



    @PrePersist
    public void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }


    @PreUpdate
    public void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

}