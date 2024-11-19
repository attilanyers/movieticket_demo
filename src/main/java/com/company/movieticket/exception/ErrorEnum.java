package com.company.movieticket.exception;

import lombok.Getter;

@Getter
public enum ErrorEnum implements ErrorCode {
    GENERAL_ERROR(500, "Internal server error"),
    BAD_REQUEST(400, "Bad request"),
    CONFLICT(409, "Confict"),
    ENTITY_NOT_FOUND(400, "Cannot find entity by id"),
    REQUEST_PARAM_IS_MISSING(400, "Request parameter is missing"),
    VALIDATION_ERROR(400, "Invalid input"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden");

    private int httpStatus;
    private String message;

    ErrorEnum(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public String getCode() {
        return name();
    }
}
