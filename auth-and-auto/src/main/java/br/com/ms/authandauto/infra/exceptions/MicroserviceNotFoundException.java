package br.com.ms.authandauto.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MicroserviceNotFoundException extends RuntimeException{

    private ExceptionResponse exceptionResponse;

    public MicroserviceNotFoundException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
