package com.educare.mindfulhome.exceptions;

import java.util.Date;
import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDetails> handleValidationExceptions(MethodArgumentNotValidException exception, WebRequest webRequest){

    StringBuilder errors = new StringBuilder();
    exception.getBindingResult().getAllErrors().forEach(violation ->
        errors.append(" Field " + ((FieldError) violation).getField() + " throws an error: "+  violation.getDefaultMessage() + System.lineSeparator()));

    ErrorDetails errorDetails = new ErrorDetails(new Date(), errors.toString(),
        webRequest.getDescription(false));
    ResponseEntity response = new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    log.error(exception.getMessage(), exception);
    log.error("GlobalExceptionHandler: " + response.toString());
    return response;
  }

  @ExceptionHandler({ValidationException.class, IllegalArgumentException.class})
  public ResponseEntity<ErrorDetails> handleValidationException(RuntimeException exception,  WebRequest webRequest){

    ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
        webRequest.getDescription(false));

    ResponseEntity response = new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    log.error(exception.getMessage(), exception);
    log.error("GlobalExceptionHandler: " + response.toString());
    return response;
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleEntityNotFoundException(EntityNotFoundException exception,  WebRequest webRequest){

    ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
            webRequest.getDescription(false));

    ResponseEntity response = new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    log.error(exception.getMessage(), exception);
    log.error("GlobalExceptionHandler: " + response.toString());
    return response;
  }

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<ErrorDetails> handleInternalServerError(NullPointerException exception,  WebRequest webRequest){

    ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
            webRequest.getDescription(false));

    ResponseEntity response = new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    log.error(exception.getMessage(), exception);
    log.error("GlobalExceptionHandler: " + response.toString());
    return response;
  }
}
