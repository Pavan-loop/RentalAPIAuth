package com.example.RentalManagementApi.errorHandling.globalExceptionHandler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String message;
    private Date errorAt;
}
