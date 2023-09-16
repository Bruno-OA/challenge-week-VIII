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
    private String password;
    @JsonIgnore
    private List<UserMicroserviceRole> userMicroservices = new ArrayList<>();

    public UserDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
