package com.example.demo.exceptions.handlers;

import com.example.demo.exceptions.APIException;
import com.example.demo.exceptions.models.APIExceptionResponse;
import com.example.demo.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalHandler {

    @Value("${environment:prod}")
    private String environment;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<APIExceptionResponse> notFoundExceptionHandler(NotFoundException exception) {
        return register(new APIExceptionResponse(HttpStatus.NOT_FOUND, exception));
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIExceptionResponse> apiExceptionHandler(APIException exception) {
        return register(new APIExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIExceptionResponse> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return register(new APIExceptionResponse(HttpStatus.BAD_REQUEST, exception, errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIExceptionResponse> exceptionHandler(Exception exception) {
        return register(new APIExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception));
    }

    private ResponseEntity<APIExceptionResponse> register(
            APIExceptionResponse exceptionResponse
    ) {
        logException(exceptionResponse);
        if (!environment.equals("dev"))
            exceptionResponse.setDebugMessage(null);
        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
    }

    private void logException(APIExceptionResponse apiExceptionResponse) {
        log.error("Exception caught:");
        log.error("Status: " + apiExceptionResponse.getStatus());
        log.error("Title: " + apiExceptionResponse.getTitle());
        log.error("Message: " + apiExceptionResponse.getMessage());
        log.error("Stack Trace: " + apiExceptionResponse.getDebugMessage());
    }
}