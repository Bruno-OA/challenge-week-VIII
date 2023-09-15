package br.com.ms.authandauto.domain.model.user.Reponse;

import br.com.ms.authandauto.domain.model.microsservice.Response.MicroserviceResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMicroserviceResponse {
    private String name;
    private String email;
    private List<MicroserviceResponse> microservices;
}
