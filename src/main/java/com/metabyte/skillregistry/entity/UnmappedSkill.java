package com.metabyte.skillregistry.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "unmapped_skills")
public class UnmappedSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String input;
    private String source;

    private LocalDateTime createdAt = LocalDateTime.now();
}
