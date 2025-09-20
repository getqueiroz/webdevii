package com.demo.demo.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message == null ? "Registro não encontrado" : message);
    }
}
