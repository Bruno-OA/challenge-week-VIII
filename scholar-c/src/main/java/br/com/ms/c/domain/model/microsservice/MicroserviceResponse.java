package br.com.ms.c.domain.model.microsservice;

import br.com.ms.c.domain.model.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class MicroserviceResponse {
    private Long id;
    private String name;
    private List<UserResponse> users;
}
