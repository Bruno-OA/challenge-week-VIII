package br.com.ms.authandauto.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidMicroserviceNameException extends RuntimeException{

    private ExceptionResponse exceptionResponse;

    public InvalidMicroserviceNameException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
