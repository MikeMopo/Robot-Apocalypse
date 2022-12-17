package com.arobot.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(value = SurvivorNotFoundException.class)
    public ResponseEntity<String> exception(SurvivorNotFoundException exception) {
        return new ResponseEntity<>("Survivor not found", HttpStatus.NOT_FOUND);
    }

}
