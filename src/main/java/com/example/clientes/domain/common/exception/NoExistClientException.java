package com.example.clientes.domain.common.exception;

public class NoExistClientException extends RuntimeException{
    public NoExistClientException(String message) {
        super(message);
    }
}
