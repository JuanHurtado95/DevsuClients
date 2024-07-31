package com.example.clientes.infraestructure.exception;

import com.example.clientes.domain.common.exception.UnregisteredCustomereException;
import com.example.clientes.domain.common.exception.CustomerIsAlreadyRegisterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(UnregisteredCustomereException.class)
    public ResponseEntity<String> handleClienteInexistenteException(UnregisteredCustomereException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerIsAlreadyRegisterException.class)
    public ResponseEntity<String> handleClienteYaRegistradoException(CustomerIsAlreadyRegisterException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Ocurrió un error inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
