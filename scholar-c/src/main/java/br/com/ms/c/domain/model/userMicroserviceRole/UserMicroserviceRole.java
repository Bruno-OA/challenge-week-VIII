package br.com.ms.c.domain.model.userMicroserviceRole;


import br.com.ms.c.domain.model.microsservice.Microservice;
import br.com.ms.c.domain.model.user.User;
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
