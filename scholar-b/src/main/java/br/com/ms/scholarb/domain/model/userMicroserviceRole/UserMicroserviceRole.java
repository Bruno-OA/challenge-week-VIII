package br.com.ms.scholarb.domain.model.userMicroserviceRole;

import br.com.ms.a.domain.model.microsservice.Microservice;
import br.com.ms.a.domain.model.user.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMicroserviceRole {

    private Long id;

    private User user;

    private Microservice microservice;

    private String role;
}
