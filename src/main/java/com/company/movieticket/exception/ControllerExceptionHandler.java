package com.company.movieticket.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorDto> handleBaseException(BaseException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        LOGGER.error(errorCode.getCode() + ": " + ex.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(
                        ErrorDto.builder()
                                .code(errorCode.getCode())
                                .message(ex.getMessage())
                                .stackTrace(Arrays.toString(ex.getStackTrace()))
                                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        LOGGER.error("Validation failed", ex);

        return ErrorDto.builder()
                .code(ErrorEnum.VALIDATION_ERROR.getCode())
                .message(ErrorEnum.VALIDATION_ERROR.getMessage())
                .validationErrors(getFieldErrors(ex))
                .stackTrace(ex.getMessage())
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto requestNotReadableException(HttpMessageNotReadableException ex) {
        LOGGER.error("Message is not readable: ", ex);

        return ErrorDto.builder()
                .code(ErrorEnum.VALIDATION_ERROR.getCode())
                .message(ErrorEnum.VALIDATION_ERROR.getMessage())
                .stackTrace(ex.getMessage())
                .build();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto missingRequestParameterException(MissingServletRequestParameterException ex) {

        return ErrorDto.builder()
                .code(ErrorEnum.REQUEST_PARAM_IS_MISSING.getCode())
                .message(ErrorEnum.REQUEST_PARAM_IS_MISSING.getMessage())
                .stackTrace(ex.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto generalException(Exception ex) {
        LOGGER.error("General exception", ex);
        return ErrorDto.builder()
                .code(ErrorEnum.GENERAL_ERROR.getCode())
                .message(ex.getClass() + ": " + ex.getMessage())
                .stackTrace(Arrays.toString(ex.getStackTrace()))
                .build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto invalidRequest(HttpRequestMethodNotSupportedException ex) {
        LOGGER.error("Bad request", ex);
        return ErrorDto.builder()
                .code(ErrorEnum.BAD_REQUEST.getCode())
                .message(ex.getClass() + ": " + ex.getMessage())
                .stackTrace(Arrays.toString(ex.getStackTrace()))
                .build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorDto invalidRequest(DataIntegrityViolationException ex) {
        String message = "Constraint violation exception";
        LOGGER.error(message, ex);
        return ErrorDto.builder()
                .code(ErrorEnum.CONFLICT.getCode())
                .message(message)
                .stackTrace(Arrays.toString(ex.getStackTrace()))
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorDto accessDenied(AccessDeniedException ex) {
        LOGGER.error("Access denied: " + ex.getMessage());
        return ErrorDto.builder()
                .code(ErrorEnum.FORBIDDEN.getCode())
                .message(ex.getMessage())
                .stackTrace(Arrays.toString(ex.getStackTrace()))
                .build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorDto badCredentials(BadCredentialsException ex) {
        LOGGER.error("Bad credentials: " + ex.getMessage());
        return ErrorDto.builder()
                .code(ErrorEnum.UNAUTHORIZED.getCode())
                .message(ex.getMessage())
                .stackTrace(Arrays.toString(ex.getStackTrace()))
                .build();
    }

    private List<ValidationError> getFieldErrors(MethodArgumentNotValidException e) {
        return e.getFieldErrors().stream()
                .map(t -> new ValidationError(t.getField(), null, t.getDefaultMessage()))
                .collect(Collectors.toList());
    }
}
