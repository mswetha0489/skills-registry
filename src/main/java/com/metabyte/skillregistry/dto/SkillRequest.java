package com.metabyte.skillregistry.dto;

import jakarta.validation.constraints.NotBlank;

public class SkillRequest {
    @NotBlank
    public String name;
    public String category;
}
