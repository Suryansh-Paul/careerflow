package com.evan.careerflow.service;

import com.evan.careerflow.dtos.SkillRequest;
import com.evan.careerflow.dtos.SkillResponse;
import com.evan.careerflow.models.Skill;
import com.evan.careerflow.repo.SkillRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {

    private final SkillRepo skillRepo;

    public SkillService(SkillRepo skillRepo) {
        this.skillRepo = skillRepo;
    }

    public List<SkillResponse> getAllSkills(){
        return skillRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public SkillResponse getSkillById(int id){
        Skill skill = skillRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found with ID: " + id));
        return mapToResponse(skill);
    }

    public SkillResponse createSkill(SkillRequest request){
        Skill skill = new Skill();
        skill.setName(request.getName());
        skill.setCategory(request.getCategory());

        Skill savedSkill = skillRepo.save(skill);
        return mapToResponse(savedSkill);
    }

    public SkillResponse updateSkill(int id, SkillRequest request){
        Skill existingSkill = skillRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found with ID: " + id));

        existingSkill.setName(request.getName());
        existingSkill.setCategory(request.getCategory());

        Skill updatedSkill = skillRepo.save(existingSkill);
        return mapToResponse(updatedSkill);
    }

    public void deleteSkill(int id){
        if (!skillRepo.existsById(id)) {
            throw new EntityNotFoundException("Skill not found with ID: " + id);
        }
        skillRepo.deleteById(id);
    }

    private SkillResponse mapToResponse(Skill skill) {
        SkillResponse response = new SkillResponse();
        response.setId(skill.getId());
        response.setName(skill.getName());
        response.setCategory(skill.getCategory());
        return response;
    }
}