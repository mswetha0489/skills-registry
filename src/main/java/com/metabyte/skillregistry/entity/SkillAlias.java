package com.metabyte.skillregistry.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "skill_alias", uniqueConstraints = @UniqueConstraint(columnNames = "alias"))
public class SkillAlias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    private String alias;

    private LocalDateTime createdAt = LocalDateTime.now();
}
