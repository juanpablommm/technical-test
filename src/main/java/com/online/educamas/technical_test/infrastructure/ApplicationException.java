package com.online.educamas.technical_test.infrastructure;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApplicationException extends RuntimeException {

    private String message;
    private HttpStatus statusCode;

    public ApplicationException(String message, HttpStatus statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }
}
