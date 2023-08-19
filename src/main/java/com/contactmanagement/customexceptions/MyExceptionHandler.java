package com.contactmanagement.customexceptions;

import com.contactmanagement.entity.ExceptionResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = ContactNotFound.class)
    public ResponseEntity<ExceptionResponse> handleContactNotFound(ContactNotFound contactNotFound, WebRequest webRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError(contactNotFound.getMessage());
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND.name());
        exceptionResponse.setTimestamp(new Date().toString());
        exceptionResponse.setPath(webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex, WebRequest webRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError(ex.getMessage());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
        exceptionResponse.setTimestamp(new Date().toString());
        exceptionResponse.setPath(webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        exceptionResponse.setError(errors.toString());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
        exceptionResponse.setTimestamp(new Date().toString());
        exceptionResponse.setPath(webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
//        List<String> errors = new ArrayList<>();
//        ex.getBindingResult().getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        exceptionResponse.setError(ex.getConstraintViolations().toString());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
        exceptionResponse.setTimestamp(new Date().toString());
        exceptionResponse.setPath(webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }



}
