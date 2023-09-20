package br.com.ms.e.infra.exceptions;

import br.com.ms.e.domain.model.enums.ErrorCodes;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ExceptionResponse implements Serializable {

    private final String code;
    private final String message;
    public ExceptionResponse(final ErrorCodes errorCode, String details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
    }

    public ExceptionResponse(ErrorCodes errorCode, List<String> details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
    }
}
