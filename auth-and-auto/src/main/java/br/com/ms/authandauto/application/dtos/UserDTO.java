package br.com.ms.authandauto.application.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class UserDTO {
    private Long id;
    private String nome;
    private String email;
    @JsonIgnore
    private String senha;
}
