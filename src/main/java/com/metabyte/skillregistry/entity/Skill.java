package com.metabyte.skillregistry.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "skills", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Data
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

}
