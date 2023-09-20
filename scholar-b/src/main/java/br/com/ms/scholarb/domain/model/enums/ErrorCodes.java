package br.com.ms.scholarb.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {

    INTERNAL_SERVER_ERROR("Internal server error"),
    INVALID_REQUEST("Invalid request"),
    VALIDATION_FAILED("Validation failed"),
    USER_NOT_FOUND("User not found"),
    MICROSERVICE_NOT_FOUND("Microservice not found"),
    BAD_REQUEST("Bad Request"),
    MALFORMED_JSON("Malformed JSON"),
    RESOURCE_NOT_FOUND("Resource not found"),
    USER_MICROSERVICE_ROLE_NOT_FOUND("UserMicroserviceRole not found");
    private final String message;

    public String getMessage() {
    }
}