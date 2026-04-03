package com.metabyte.skillregistry.exception;

import lombok.Getter;

public class SkillNotFoundException extends  RuntimeException{

    public SkillNotFoundException(String message) {
        super(message);
    }

}

