package com.evan.careerflow.controller;

import com.evan.careerflow.dtos.SkillRequest;
import com.evan.careerflow.dtos.SkillResponse;
import com.evan.careerflow.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/skills")
    public ResponseEntity<List<SkillResponse>> getAllSkills(){
        return new ResponseEntity<>(
                skillService.getAllSkills(),
                HttpStatus.OK
        );
    }

    @GetMapping("/skill/{id}")
    public ResponseEntity<SkillResponse> getSkillById(@PathVariable int id){
        return new ResponseEntity<>(
                skillService.getSkillById(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/skill")
    public ResponseEntity<SkillResponse> createSkill(@Valid @RequestBody SkillRequest request){
        SkillResponse savedSkill = skillService.createSkill(request);
        return new ResponseEntity<>(
                savedSkill,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/skill/{id}")
    public ResponseEntity<SkillResponse> updateSkill(@PathVariable int id, @Valid @RequestBody SkillRequest request){
        SkillResponse updatedSkill = skillService.updateSkill(id, request);
        return new ResponseEntity<>(
                updatedSkill,
                HttpStatus.OK
        );
    }

    @GetMapping("/test")
    public String test(){
        return "Skill controller working";
    }

    @DeleteMapping("/skill/{id}")
    public ResponseEntity<String> deleteSkill(@PathVariable int id){
        skillService.deleteSkill(id);
        return new ResponseEntity<>(
                "Skill deleted successfully",
                HttpStatus.OK
        );
    }
}