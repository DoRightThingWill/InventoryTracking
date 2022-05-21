package com.shopify.InventoryTrack.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice
{
  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<Object> handleNotFoundException(ObjectNotFoundException e) {
    var headers = new HttpHeaders();
    headers.setContentType(APPLICATION_PROBLEM_JSON);
    return new ResponseEntity<>(
        e.getMessage(),
        headers, NOT_FOUND);
  }
}
