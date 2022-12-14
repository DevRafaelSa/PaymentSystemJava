package com.rafael.paymentspringjpa.controllers.exceptions;

//nela vamos dar o tratamento manual das excecoes

import com.rafael.paymentspringjpa.services.exceptions.DataBaseException;
import com.rafael.paymentspringjpa.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice // ele vai interceptar as excecoes que acontecerem para que este objeto possa conceder um possivel tratamento
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) // com a anotacao eu to dizendo que esse metodo vai interceptar qualquer excecao do tipo ResourceNouFound e fazer o tratamento abaixo
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataBaseException.class) // com a anotacao eu to dizendo que esse metodo vai interceptar qualquer excecao do tipo ResourceNouFound e fazer o tratamento abaixo
    public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest request) {
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


}
