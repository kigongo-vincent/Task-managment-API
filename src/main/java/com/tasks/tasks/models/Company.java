package com.tasks.tasks.models;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;

public record Company(
    @Id int id,
    @NotBlank String name,
    Status status,
    int owner
) {
    
}
