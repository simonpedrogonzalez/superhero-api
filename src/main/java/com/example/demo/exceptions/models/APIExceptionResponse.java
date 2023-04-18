package com.example.demo.exceptions.models;


import com.example.demo.exceptions.APIException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.StringWriter;

@Getter
@Builder
@AllArgsConstructor
@Setter
public class APIExceptionResponse {
    private HttpStatus status;
    private String title;
    private String message;
    private String timestamp;
    private String debugMessage;

    public APIExceptionResponse(HttpStatus status, APIException exception) {
        this.status = status;
        this.title = exception.getProblem().getTitle();
        this.message = exception.getProblem().getMessage();
        this.timestamp = String.valueOf(System.currentTimeMillis());
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new java.io.PrintWriter(sw));
        this.debugMessage = sw.toString();
    }

    public APIExceptionResponse(HttpStatus status, Exception exception) {
        this.status = status;
        this.title = "UNMAPPED_EXCEPTION";
        this.message = exception.getMessage();
        this.timestamp = String.valueOf(System.currentTimeMillis());
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new java.io.PrintWriter(sw));
        this.debugMessage = sw.toString();
    }
}
