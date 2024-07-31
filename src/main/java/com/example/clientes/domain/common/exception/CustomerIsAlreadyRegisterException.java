package com.example.clientes.domain.common.exception;

public class CustomerIsAlreadyRegisterException extends RuntimeException{
    public CustomerIsAlreadyRegisterException(String message) {
        super(message);
    }
}
