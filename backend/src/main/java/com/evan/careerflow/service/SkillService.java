package com.evan.careerflow.service;


import com.evan.careerflow.models.Skill;
import com.evan.careerflow.repo.SkillRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SkillService {


    private final SkillRepo skillRepo;


    public SkillService(SkillRepo skillRepo) {
        this.skillRepo = skillRepo;
    }


    public List<Skill> getAllSkills(){

        return skillRepo.findAll();

    }


    public Skill getSkillById(int id){

        return skillRepo.findById(id)
                .orElseThrow();

    }


    public Skill createSkill(Skill skill){

        return skillRepo.save(skill);

    }


    public List<Skill> searchSkills(String keyword){

        return skillRepo.searchSkills(keyword);

    }


    public void deleteSkill(int id){

        skillRepo.deleteById(id);

    }

}