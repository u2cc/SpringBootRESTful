package com.rest.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class DiecastCarExceptionController {

    @ExceptionHandler(value = DiecastCarNotFoundException.class)
    public ResponseEntity<Object> exception(DiecastCarNotFoundException exception) {
        return new ResponseEntity<>("Diecast car not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {JsonPatchException.class , JsonProcessingException.class})
    public ResponseEntity<Object> handleJsonExceptions(Exception exception) {
        StringBuilder sb = new StringBuilder();
        sb.append("Internal Server Error: ");
        sb.append(exception.getMessage());
        sb.append(".");
        return new ResponseEntity<>(sb.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value={DiecastCarAlreadyExistsException.class})
    public ResponseEntity<Object> handleDieCastCarAlreadyExistsException(DiecastCarAlreadyExistsException exception){
        return new ResponseEntity<>("Diecast car already exists.", HttpStatus.CONFLICT);
    }

}
