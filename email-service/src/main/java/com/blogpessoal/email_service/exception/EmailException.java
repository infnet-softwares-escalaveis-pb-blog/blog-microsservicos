package com.blogpessoal.email_service.exception;

public class EmailException extends RuntimeException {

    // Construtor com apenas a mensagem
    public EmailException(String message) {
        super(message);
    }

    // Construtor com mensagem e causa (erro original)
    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
