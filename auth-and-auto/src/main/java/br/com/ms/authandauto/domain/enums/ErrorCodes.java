package br.com.ms.authandauto.domain.enums;

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
    DUPLICATED_EMAIL("Duplicated Email"),
    INVALID_EMAIL("Invalid Email"),
    MICROSERVICE_ALREADY_EXISTS_IN_USER("Microservice already exists in the user"),
    MICROSERVICE_ALREADY_EXISTS("Microservice already exists"),
    USER_MICROSERVICE_ROLE_NOT_FOUND("UserMicroserviceRole not found"),
    INVALID_MICROSERVICE_NAME("Invalid microservice name");


    private final String message;

}