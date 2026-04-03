package com.metabyte.skillregistry.exception;

public class AliasExistsException extends RuntimeException {
    private String existingSkill;

    public AliasExistsException(String message, String existingSkill) {
        super(message);
        this.existingSkill = existingSkill;
    }

    public String getExistingSkill() {
        return existingSkill;
    }
}
