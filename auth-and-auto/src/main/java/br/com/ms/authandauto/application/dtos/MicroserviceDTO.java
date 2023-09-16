package br.com.ms.authandauto.application.dtos;
import br.com.ms.authandauto.domain.model.Enum.Role;
import lombok.*;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class MicroserviceDTO {
    private Long id;
    private String name;
}

