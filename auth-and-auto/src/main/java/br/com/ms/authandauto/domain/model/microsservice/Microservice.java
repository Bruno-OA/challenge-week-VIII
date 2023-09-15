package br.com.ms.authandauto.domain.model.microsservice;

import br.com.ms.authandauto.domain.model.Enum.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "microservices")
@EqualsAndHashCode(of = "id")
public class Microservice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}

