package br.com.ms.d.domain.model.microservice;

import br.com.ms.d.domain.model.userMicroserviceRole.UserMicroserviceRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Microservice {
    private Long id;
    private String name;
    private List<UserMicroserviceRole> userMicroservices;
}
