package br.com.ms.authandauto.infra.exceptions;

import br.com.ms.authandauto.domain.enums.ErrorCodes;
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
