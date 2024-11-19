package com.company.movieticket.exception;

public interface ErrorCode {
    String getCode();

    int getHttpStatus();

    String getMessage();
}
