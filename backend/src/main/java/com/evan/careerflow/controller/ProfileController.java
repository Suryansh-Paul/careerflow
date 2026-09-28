package com.evan.careerflow.controller;

import com.evan.careerflow.dtos.ProfileResponse;
import com.evan.careerflow.dtos.ProfileUpdateRequest;
import com.evan.careerflow.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/api/profile")
@PreAuthorize("isAuthenticated()")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> getMyProfile(Principal principal) {
        return new ResponseEntity<>(
                profileService.getMyProfile(principal.getName()),
                HttpStatus.OK
        );
    }

    @PutMapping("/me")
    public ResponseEntity<ProfileResponse> updateProfile(
            @RequestBody ProfileUpdateRequest request,
            Principal principal) {
        return new ResponseEntity<>(
                profileService.updateProfile(principal.getName(), request),
                HttpStatus.OK
        );
    }

    @PostMapping("/me/picture")
    public ResponseEntity<?> uploadProfilePicture(
            @RequestParam("file") MultipartFile file,
            Principal principal) {
        try {
            ProfileResponse response = profileService.uploadProfilePicture(principal.getName(), file);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMyAccount(Principal principal) {
        profileService.deleteMyAccount(principal.getName());
        return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
    }
}