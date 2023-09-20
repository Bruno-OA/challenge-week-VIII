package br.com.ms.authandauto.application.dtos;
import br.com.ms.authandauto.domain.enums.Role;
import br.com.ms.authandauto.domain.model.microsservice.Microservice;
import br.com.ms.authandauto.domain.model.user.User;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class UserMicroserviceRoleDTO {
    private Long id;
    private UserDTO user;
    private MicroserviceDTO microservice;
    private Role role;
}

