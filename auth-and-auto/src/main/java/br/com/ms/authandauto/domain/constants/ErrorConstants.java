package br.com.ms.authandauto.domain.constants;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PRIVATE)
public class ErrorConstants {

    public static final String USER_NOT_FOUND = "[MS-AUTH-AND-AUTO] User Not Found";
    public static final String USER_ALREADY_EXISTS = "[MS-AUTH-AND-AUTO] User Already Exists";
    public static final String MICROSERVICE_NOT_FOUND = "[MS-AUTH-AND-AUTO] Microservice Not Found";
    public static final String MICROSERVICE_ALREADY_EXISTS = "[MS-AUTH-AND-AUTO] Microservice Already Exists";
}
