package br.com.ms.authandauto.infra.exceptions.handler;


import br.com.ms.authandauto.infra.exceptions.*;
import br.com.ms.authandauto.domain.enums.ErrorCodes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders
            headers, HttpStatusCode status, WebRequest request) {
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errors = new ArrayList<>();
        fieldErrors.forEach(f -> errors.add(String.format("%s : %s", f.getField(), f.getDefaultMessage())));
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.VALIDATION_FAILED, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders
            headers, HttpStatusCode status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.MALFORMED_JSON, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.USER_NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
    @ExceptionHandler(MicroserviceNotFoundException.class)
    public final ResponseEntity<Object> handleMicroserviceNotFound(MicroserviceNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.MICROSERVICE_NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
    @ExceptionHandler(MicroserviceAlreadyExistsExcept.class)
    public final ResponseEntity<Object> handleMicroserviceAlreadyExists(MicroserviceAlreadyExistsExcept ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.MICROSERVICE_ALREADY_EXISTS, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
    @ExceptionHandler(DuplicatedEmailException.class)
    public final ResponseEntity<Object> handleDuplicatedCpf(DuplicatedEmailException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.DUPLICATED_EMAIL, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
    @ExceptionHandler(MicroserviceAlreadyExistsInUserExcept.class)
    public final ResponseEntity<Object> handleMicroserviceAlreadyExistsInUserExcept(MicroserviceAlreadyExistsInUserExcept ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.MICROSERVICE_ALREADY_EXISTS_IN_USER, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

}
