package br.com.ms.authandauto.domain.model.user.Reponse;

import br.com.ms.authandauto.domain.model.microsservice.Microservice;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserMicroserviceResponse {
    private String name;
    private String email;
    private List<Microservice> microservices;
}
