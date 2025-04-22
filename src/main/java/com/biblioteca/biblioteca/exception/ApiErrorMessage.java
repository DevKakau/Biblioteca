package com.biblioteca.biblioteca.exception;
import org.springframework.http.HttpStatus;


public class ApiErrorMessage {

    private HttpStatus status;
    private String error;
    private String message;

    public ApiErrorMessage(HttpStatus status, String error, String message){
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public ApiErrorMessage(int value, String notFound, String message, String requestURI) {
    }
}
