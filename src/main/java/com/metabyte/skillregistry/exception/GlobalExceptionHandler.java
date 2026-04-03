package com.metabyte.skillregistry.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    Map<String, Object> error = new HashMap<>();
    LocalDateTime dateTime= LocalDateTime.now();
    @ExceptionHandler(AliasExistsException.class)
    public ResponseEntity<?> handleAlias(AliasExistsException ex, HttpServletRequest req) {

        error.put("timestamp",dateTime );
        error.put("status", 409);
        error.put("message", ex.getMessage());
        error.put("path", req.getRequestURI());
        error.put("existingSkill", ex.getExistingSkill());
        return ResponseEntity.status(409).body(error);
    }
    @ExceptionHandler(SkillExistsException.class)
    public ResponseEntity<?> handleSkill(SkillExistsException ex, HttpServletRequest req) {
        error.put("timestamp", dateTime);
        error.put("status", 409);
        error.put("message", ex.getMessage());
        error.put("path", req.getRequestURI());
        error.put("existingSkill", ex.getExistingSkill());
        return ResponseEntity.status(409).body(error);
    }
    @ExceptionHandler(SkillNotFoundException.class)
    public ResponseEntity<?> handleSkillNotFound(SkillNotFoundException ex, HttpServletRequest req) {
        error.put("timestamp", dateTime);
        error.put("status", 404);
        error.put("message", ex.getMessage());
        error.put("path", req.getRequestURI());
        error.put("existingSkill", "");
        return ResponseEntity.status(409).body(error);
    }

}
