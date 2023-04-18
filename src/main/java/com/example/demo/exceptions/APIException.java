package com.example.demo.exceptions;

import com.example.demo.exceptions.models.Problem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class APIException extends RuntimeException {
    private Problem problem;
}
