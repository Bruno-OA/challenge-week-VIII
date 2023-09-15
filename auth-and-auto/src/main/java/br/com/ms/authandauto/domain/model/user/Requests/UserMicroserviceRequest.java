package br.com.ms.authandauto.domain.model.user.Requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserMicroserviceRequest {
    private String emailUser;
    private String nameMicroservice;
}
