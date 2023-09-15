package br.com.ms.authandauto.application.dtos;

import br.com.ms.authandauto.domain.model.userMicroserviceRole.UserMicroserviceRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private List<UserMicroserviceRole> userMicroservices = new ArrayList<>();

}
