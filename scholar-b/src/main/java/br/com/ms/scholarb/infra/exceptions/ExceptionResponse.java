package br.com.ms.scholarb.infra.exceptions;

import br.com.ms.scholarb.domain.model.enums.ErrorCodes;
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
