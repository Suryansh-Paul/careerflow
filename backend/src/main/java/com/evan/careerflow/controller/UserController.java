package com.evan.careerflow.controller;

import com.evan.careerflow.dtos.UserRequest;
import com.evan.careerflow.dtos.UserResponse;
import com.evan.careerflow.service.UserService;
import jakarta.validation.Valid;
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


    // GET all users
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return new ResponseEntity<>(
                userService.getAllUsers(),
                HttpStatus.OK
        );
    }


    // GET user by ID
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable int id) {

        UserResponse user = userService.getUserById(id);

        if (user != null) {

            return new ResponseEntity<>(
                    user,
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }


    // POST user
    @PostMapping("/user")
    public ResponseEntity<?> createUser( @Valid
            @RequestBody UserRequest request) {

        try {

            UserResponse savedUser =
                    userService.createUser(request);

            return new ResponseEntity<>(
                    savedUser,
                    HttpStatus.CREATED
            );

        } catch (Exception e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    // PUT user
    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable int id, @Valid
            @RequestBody UserRequest request) {

        UserResponse updatedUser =
                userService.updateUser(id, request);

        if (updatedUser != null) {

            return new ResponseEntity<>(
                    updatedUser,
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "User not found",
                HttpStatus.NOT_FOUND
        );
    }


    // DELETE user
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable int id) {

        boolean deleted =
                userService.deleteUser(id);

        if (deleted) {

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