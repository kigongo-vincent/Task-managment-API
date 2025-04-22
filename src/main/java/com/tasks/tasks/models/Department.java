package com.tasks.tasks.models;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;

public record Department(
    @Id int id,
    @NotBlank String name,
    int head,
    int company
) {

}
