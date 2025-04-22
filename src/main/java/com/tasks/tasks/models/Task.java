package com.tasks.tasks.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

// import jakarta.validation.constraints.NotBlank;

@Table("base_task")
public record Task(
    @Id
    int id,
    String title,
    String email,
    String name,
    String description,
    boolean isDrafted,
    @Column("author") int user,
    int duration,
    LocalDateTime createdAt
) {
    
}
