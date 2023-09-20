package br.com.ms.authandauto.application.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class MicroserviceDTO {
    private Long id;
    @JsonProperty("name")
    private String name;
}

