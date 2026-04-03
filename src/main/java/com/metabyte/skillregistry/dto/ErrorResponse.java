package com.metabyte.skillregistry.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
    private LocalDateTime timestamp= LocalDateTime.now();
    private String existingSkill;
    private String path;

}