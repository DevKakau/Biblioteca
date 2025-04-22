package com.biblioteca.biblioteca.exception;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter

public class ApiErrorMessage {

    private int status;
    private String error;
    private String message;

    public ApiErrorMessage(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

}
