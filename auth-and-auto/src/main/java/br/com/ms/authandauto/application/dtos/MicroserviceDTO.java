package br.com.ms.authandauto.application.dtos;


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

