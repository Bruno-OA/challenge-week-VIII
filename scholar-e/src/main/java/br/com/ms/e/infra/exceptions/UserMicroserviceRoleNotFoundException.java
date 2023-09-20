package br.com.ms.e.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserMicroserviceRoleNotFoundException extends RuntimeException{

    private ExceptionResponse exceptionResponse;

    public UserMicroserviceRoleNotFoundException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
