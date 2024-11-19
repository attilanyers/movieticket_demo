package com.company.movieticket.exception;

public record ValidationError(String field, String code, String message) {}
