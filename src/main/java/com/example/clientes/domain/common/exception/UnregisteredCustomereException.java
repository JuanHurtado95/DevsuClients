package com.example.clientes.domain.common.exception;

public class UnregisteredCustomereException extends RuntimeException{
    public UnregisteredCustomereException(String message) {
        super(message);
    }
}
