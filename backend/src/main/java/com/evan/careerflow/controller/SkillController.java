package com.evan.careerflow.controller;


import com.evan.careerflow.models.Skill;
import com.evan.careerflow.service.SkillService;
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
    public ResponseEntity<List<Skill>> getAllSkills(){


        return new ResponseEntity<>(
                skillService.getAllSkills(),
                HttpStatus.OK
        );
    }




    @GetMapping("/skill/{id}")
    public ResponseEntity<Skill> getSkillById(
            @PathVariable int id){


        Skill skill =
                skillService.getSkillById(id);



        if(skill != null){

            return new ResponseEntity<>(
                    skill,
                    HttpStatus.OK
            );
        }



        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }





    @PostMapping("/skill")
    public ResponseEntity<?> createSkill(
            @RequestBody Skill skill){


        try{


            Skill savedSkill =
                    skillService.createSkill(skill);



            return new ResponseEntity<>(
                    savedSkill,
                    HttpStatus.CREATED
            );



        }catch(Exception e){


            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }





    @PutMapping("/skill/{id}")
    public ResponseEntity<?> updateSkill(
            @PathVariable int id,
            @RequestBody Skill skill){



        Skill updatedSkill =
                skillService.updateSkill(id,skill);



        if(updatedSkill != null){

            return new ResponseEntity<>(
                    updatedSkill,
                    HttpStatus.OK
            );
        }



        return new ResponseEntity<>(
                "Skill not found",
                HttpStatus.BAD_REQUEST
        );

    }

    @GetMapping("/test")
    public String test(){

        return "Skill controller working";
    }



    @DeleteMapping("/skill/{id}")
    public ResponseEntity<String> deleteSkill(
            @PathVariable int id){



        Skill skill =
                skillService.getSkillById(id);



        if(skill != null){


            skillService.deleteSkill(id);



            return new ResponseEntity<>(
                    "Skill deleted successfully",
                    HttpStatus.OK
            );
        }



        return new ResponseEntity<>(
                "Skill not found",
                HttpStatus.NOT_FOUND
        );

    }

}
