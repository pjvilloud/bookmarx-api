package me.bookmarx.api.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public RestError handleNotFoundError(EntityNotFoundException entityNotFoundException){
        return new RestError("Impossible de trouver l'élément demandé !");
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public RestError handleEmptyResultDataAccessException(EmptyResultDataAccessException emptyResultDataAccessException){
        return new RestError(emptyResultDataAccessException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public RestError handleNotFoundError(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException){
        return new RestError(methodArgumentTypeMismatchException.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public RestError handleIllegalArgumentException(IllegalArgumentException illegalArgumentException){
        return new RestError(illegalArgumentException.getMessage());
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateKeyException.class)
    public RestError handleDuplicateKeyException(DuplicateKeyException illegalArgumentException){
        return new RestError(illegalArgumentException.getMessage());
    }
}
