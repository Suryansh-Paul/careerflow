package com.evan.careerflow.controller;


import com.evan.careerflow.models.User;
import com.evan.careerflow.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){

        return new ResponseEntity<>(
                userService.getAllUsers(),
                HttpStatus.OK
        );
    }



    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable int id){


        User user = userService.getUserById(id);


        if(user != null){

            return new ResponseEntity<>(
                    user,
                    HttpStatus.OK
            );
        }


        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }




    @PostMapping("/user")
    public ResponseEntity<?> createUser(
            @RequestBody User user){


        try{

            User savedUser =
                    userService.createUser(user);


            return new ResponseEntity<>(
                    savedUser,
                    HttpStatus.CREATED
            );


        }catch(Exception e){

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }




    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable int id,
            @RequestBody User user){


        User updatedUser =
                userService.updateUser(id,user);



        if(updatedUser != null){

            return new ResponseEntity<>(
                    updatedUser,
                    HttpStatus.OK
            );
        }


        return new ResponseEntity<>(
                "User not found",
                HttpStatus.BAD_REQUEST
        );
    }





    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable int id){


        User user =
                userService.getUserById(id);


        if(user != null){

            userService.deleteUser(id);


            return new ResponseEntity<>(
                    "User deleted successfully",
                    HttpStatus.OK
            );
        }


        return new ResponseEntity<>(
                "User not found",
                HttpStatus.NOT_FOUND
        );
    }

}