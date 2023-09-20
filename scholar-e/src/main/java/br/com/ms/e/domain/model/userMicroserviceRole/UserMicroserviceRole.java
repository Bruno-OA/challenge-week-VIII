package br.com.ms.e.domain.model.userMicroserviceRole;


import br.com.ms.e.domain.model.microsservice.Microservice;
import br.com.ms.e.domain.model.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.management.relation.Role;

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
