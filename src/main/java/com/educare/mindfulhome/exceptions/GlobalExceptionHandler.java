package com.educare.mindfulhome.exceptions;

import java.util.Date;
import javax.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDetails> handleValidationExceptions(MethodArgumentNotValidException exception, WebRequest webRequest){

    StringBuilder errors = new StringBuilder();
    exception.getBindingResult().getAllErrors().forEach(violation ->
        errors.append(" Field " + ((FieldError) violation).getField() + " throws an error: "+  violation.getDefaultMessage() + System.lineSeparator()));

    ErrorDetails errorDetails = new ErrorDetails(new Date(), errors.toString(),
        webRequest.getDescription(false));

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorDetails> handleValidationException(ValidationException exception,  WebRequest webRequest){

    ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
        webRequest.getDescription(false));

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }
}
