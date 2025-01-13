package com.example.RentalManagementApi.errorHandling.globalExceptionHandler;

import com.example.RentalManagementApi.errorHandling.PropertyNotFoundException;
import com.example.RentalManagementApi.errorHandling.TokenNotFoundException;
import com.example.RentalManagementApi.errorHandling.UnitNotFoundException;
import com.example.RentalManagementApi.errorHandling.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnitNotFoundException.class)
    public ResponseEntity<ErrorResponse> unitNotFoundResponse(UnitNotFoundException e) {
        var exception = ErrorResponse.builder()
                .message(e.getMessage())
                .errorAt(new Date())
                .build();
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<ErrorResponse> propertyNotFoundResponse(PropertyNotFoundException e) {
        var exception = ErrorResponse.builder()
                .message(e.getMessage())
                .errorAt(new Date())
                .build();
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundResponse(UserNotFoundException e) {
        var errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .errorAt(new Date())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ErrorResponse> tokenNotFoundResponse(TokenNotFoundException e) {
        var errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .errorAt(new Date())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
