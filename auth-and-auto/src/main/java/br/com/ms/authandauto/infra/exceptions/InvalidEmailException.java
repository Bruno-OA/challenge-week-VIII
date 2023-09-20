package br.com.ms.authandauto.infra.exceptions;

public class InvalidEmailException extends RuntimeException {

    private static final long serialVersionUID = -1641049136523197632L;
    private ExceptionResponse exceptionResponse;

    public InvalidEmailException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
