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
                .orElse(null);

    }





    public Skill createSkill(Skill skill){

        return skillRepo.save(skill);

    }





    public Skill updateSkill(int id, Skill skill){


        Skill existingSkill =
                skillRepo.findById(id)
                        .orElse(null);



        if(existingSkill != null){


            existingSkill.setName(
                    skill.getName()
            );


            existingSkill.setCategory(
                    skill.getCategory()
            );


            return skillRepo.save(existingSkill);

        }


        return null;

    }





    public void deleteSkill(int id){

        skillRepo.deleteById(id);

    }

}