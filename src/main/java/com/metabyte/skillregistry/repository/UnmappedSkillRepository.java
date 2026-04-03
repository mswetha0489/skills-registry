package com.metabyte.skillregistry.repository;

import com.metabyte.skillregistry.entity.UnmappedSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnmappedSkillRepository extends JpaRepository<UnmappedSkill, Long> {
}
