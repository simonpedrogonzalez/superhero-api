package com.example.demo.exceptions;

import com.example.demo.exceptions.models.Problem;

public class NotFoundException extends APIException {

    public NotFoundException(Problem problem) {
        super(problem);
    }
}