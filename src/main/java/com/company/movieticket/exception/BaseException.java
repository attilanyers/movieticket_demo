package com.company.movieticket.exception;

import java.util.List;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final ErrorCode errorCode;
    private List<ValidationError> validationErrors;

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, Exception e) {
        super(e);
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, String message, Exception e) {
        super(message, e);
        this.errorCode = errorCode;
    }

    public BaseException(List<ValidationError> validationErrors) {
        this(ErrorEnum.VALIDATION_ERROR);
        this.validationErrors = validationErrors;
    }
}
