package com.company.movieticket.exception;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDto {
    private String code;
    private String message;
    private List<ValidationError> validationErrors;
    private String stackTrace;
}
