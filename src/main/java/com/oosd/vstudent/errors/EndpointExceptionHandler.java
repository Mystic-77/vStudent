package com.oosd.vstudent.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EndpointExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(InvalidEndpointException exception)
    {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        System.out.println(errorResponse);
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception)
    {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.I_AM_A_TEAPOT.value());
        errorResponse.setMessage(exception.getMessage());
        System.out.println(errorResponse);
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.I_AM_A_TEAPOT);
    }

}