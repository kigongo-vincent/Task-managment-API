package com.tasks.tasks.models;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;

public record User(
    int id,
    String name,
    @Email
    String email,
    UserRole role,
    boolean isActive,
    LocalDateTime createdAt 
) {
    
}
