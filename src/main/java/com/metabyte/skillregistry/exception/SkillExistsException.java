package com.metabyte.skillregistry.exception;


import lombok.Getter;

@Getter
public class SkillExistsException extends RuntimeException {
    private final String existingSkill;

    public SkillExistsException(String message, String existingSkill) {
        super(message);
        this.existingSkill = existingSkill;
    }

}