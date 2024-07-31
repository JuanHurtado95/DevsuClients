package com.example.clientes.domain.common.exception;

public class AccountNoFoundException extends RuntimeException {
    public AccountNoFoundException(String message) {
        super(message);
    }
}
