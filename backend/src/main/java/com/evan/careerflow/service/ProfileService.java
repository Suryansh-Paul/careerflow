package com.evan.careerflow.service;

import com.evan.careerflow.dtos.ProfileResponse;
import com.evan.careerflow.dtos.ProfileUpdateRequest;
import com.evan.careerflow.exceptionhandling.ResourceNotFoundException;
import com.evan.careerflow.models.Skill;
import com.evan.careerflow.models.User;
import com.evan.careerflow.repo.SkillRepo;
import com.evan.careerflow.repo.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    private final UserRepo userRepo;
    private final SkillRepo skillRepo;
    private final String UPLOAD_DIR = "uploads/";

    public ProfileService(UserRepo userRepo, SkillRepo skillRepo) {
        this.userRepo = userRepo;
        this.skillRepo = skillRepo;
    }

    public ProfileResponse getMyProfile(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return mapToResponse(user);
    }

    public ProfileResponse updateProfile(String email, ProfileUpdateRequest request) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setHeadline(request.getHeadline());
        user.setBio(request.getBio());
        user.setLocation(request.getLocation());

        // Fetch and map Skills from DB
        if (request.getSkillIds() != null && !request.getSkillIds().isEmpty()) {
            List<Skill> skills = skillRepo.findAllById(request.getSkillIds());
            user.setSkills(skills);
        }

        user.setOnboarded(true); // Flag as onboarded once they save

        User updatedUser = userRepo.save(user);
        return mapToResponse(updatedUser);
    }

    public ProfileResponse uploadProfilePicture(String email, MultipartFile file) throws IOException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = "";
        if (originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        String newFilename = UUID.randomUUID().toString() + extension;
        Path filePath = uploadPath.resolve(newFilename);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/images/")
                .path(newFilename)
                .toUriString();

        user.setProfileImageUrl(fileDownloadUri);
        User updatedUser = userRepo.save(user);

        return mapToResponse(updatedUser);
    }

    public void deleteMyAccount(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepo.delete(user);
    }

    private ProfileResponse mapToResponse(User user) {
        ProfileResponse response = new ProfileResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        response.setOnboarded(user.isOnboarded());
        response.setProfileImageUrl(user.getProfileImageUrl());
        response.setHeadline(user.getHeadline());
        response.setBio(user.getBio());
        response.setLocation(user.getLocation());

        // Flatten List<Skill> to List<String> for the frontend
        if (user.getSkills() != null) {
            List<String> skillNames = user.getSkills().stream()
                    .map(Skill::getName)
                    .collect(Collectors.toList());
            response.setSkills(skillNames);
        }

        return response;
    }
}