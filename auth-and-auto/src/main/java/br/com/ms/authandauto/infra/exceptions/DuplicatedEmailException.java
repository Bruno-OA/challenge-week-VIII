package br.com.ms.authandauto.infra.exceptions;

public class DuplicatedEmailException extends RuntimeException {

    private ExceptionResponse exceptionResponse;
    public DuplicatedEmailException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
