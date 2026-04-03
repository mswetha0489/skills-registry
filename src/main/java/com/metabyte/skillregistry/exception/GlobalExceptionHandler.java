package com.metabyte.skillregistry.exception;

import com.metabyte.skillregistry.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    ErrorResponse error = new ErrorResponse();
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(MethodArgumentNotValidException ex, HttpServletRequest req) {
        error.setPath(req.getRequestURI());
        String msg = "validation exception";
        if (ex.getBindingResult().getFieldError() != null) {
            msg = ex.getBindingResult().getFieldError().getDefaultMessage();
        }
        error.setMessage(msg);
        error.setExistingSkill("");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AliasExistsException.class)
    public ResponseEntity<?> handleAlias(AliasExistsException ex, HttpServletRequest req) {
        error.setPath(req.getRequestURI());
        error.setMessage(ex.getMessage());
        error.setExistingSkill(ex.getExistingSkill());
        error.setStatus(HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SkillExistsException.class)
    public ResponseEntity<?> handleSkill(SkillExistsException ex, HttpServletRequest req) {
        error.setPath(req.getRequestURI());
        error.setMessage(ex.getMessage());
        error.setExistingSkill(ex.getExistingSkill());
        error.setStatus(HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SkillNotFoundException.class)
    public ResponseEntity<?> handleSkillNotFound(SkillNotFoundException ex, HttpServletRequest req) {
        error.setPath(req.getRequestURI());
        error.setMessage(ex.getMessage());
        error.setExistingSkill("");
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex, HttpServletRequest req) {
        error.setPath(req.getRequestURI());
        error.setMessage("Something went wrong");
        error.setExistingSkill("");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
