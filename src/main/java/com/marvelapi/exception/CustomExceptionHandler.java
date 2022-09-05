package com.marvelapi.exception;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = FeignException.NotFound.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<String> notFoundException(FeignException.NotFound exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>("Character not found", NOT_FOUND);
    }

    @ExceptionHandler(value = FeignException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> genericFeignException(FeignException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>("Error when trying to communicate with Marvel API", INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BadRequest.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<String> badRequest(BadRequest exception) {
        return new ResponseEntity<>(exception.getMessage(), BAD_REQUEST);
    }
}
