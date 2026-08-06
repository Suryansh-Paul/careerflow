package com.evan.careerflow.repo;


import com.evan.careerflow.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SkillRepo extends JpaRepository<Skill, Integer> {


    @Query("""
            SELECT s FROM Skill s
            WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    List<Skill> searchSkills(String keyword);


}